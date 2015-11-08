package com.tarasantoshchuk.twin.files.comparing;

import com.tarasantoshchuk.twin.files.FileInfo;
import com.tarasantoshchuk.twin.util.ErrorHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class FileComparator {
    private static final long CHUNK_SIZE = 1024;
    private static final long SKIP_SIZE = 5 * 1024;

    public List<List<File>> getDuplicates(List<File> filesToCompare) {
        Map<Long, List<File>> filesBySize = sortFilesBySize(filesToCompare);
        List<List<File>> result = new ArrayList<>();

        for(List<File> filesWithSameSize: filesBySize.values()) {
            if(filesWithSameSize.size() > 1) {
                result.addAll(getDuplicatesFromSameSizeFiles(filesWithSameSize));
            }
        }

        return result;
    }

    //package-protected for testing
    Map<Long, List<File>> sortFilesBySize(List<File> files) {
        Map<Long, List<File>> result = new HashMap<>();

        for(File file: files) {
            long fileSize = file.length();
            List<File> filesWithSameSize = result.get(fileSize);

            if(filesWithSameSize == null) {
                result.put(fileSize, new ArrayList<File>());
            }

            result.get(fileSize).add(file);
        }

        return result;
    }

    private List<List<File>> getDuplicatesFromSameSizeFiles(List<File> filesWithSameSize) {
        List<FileInfo> files = new ArrayList<>();

        for(File file: filesWithSameSize) {
            try {
                files.add(new FileInfo(file));
            } catch (FileNotFoundException e) {
                ErrorHelper.fileNotFound(file);
            }
        }

        long currentFilesSize = filesWithSameSize.get(0).length();

        FilePartComparator chunkComparator = getChunkComparator(currentFilesSize);

        List<List<FileInfo>> byChunkDuplicates = chunkComparator.compareByChunk(files);

        FilePartComparator fullFileComparator = getFullFileComparator(currentFilesSize);

        List<List<FileInfo>> duplicates = new ArrayList<>();

        for(List<FileInfo> chunkDuplicatesGroup: byChunkDuplicates) {
            duplicates.addAll(fullFileComparator.compareByChunk(chunkDuplicatesGroup));
        }

        return toListOfFileLists(duplicates);
    }

    private List<List<File>> toListOfFileLists(List<List<FileInfo>> listOfFileInfoLists) {
        List<List<File>> result = new ArrayList<>();

        for(List<FileInfo> fileInfoList: listOfFileInfoLists) {
            List<File> files = new ArrayList<>();
            for(FileInfo fileInfo: fileInfoList) {
                files.add(fileInfo.getFile());
            }
            result.add(files);
        }

        return result;
    }

    private FilePartComparator getChunkComparator(long fileSize) {
        FilePartComparator comparator;
        if(fileSize < CHUNK_SIZE) {
            comparator = new FilePartComparator(0, fileSize);
        } else if (fileSize < CHUNK_SIZE + SKIP_SIZE) {
            comparator = new FilePartComparator(fileSize - CHUNK_SIZE, CHUNK_SIZE);
        } else {
            comparator = new FilePartComparator(SKIP_SIZE, CHUNK_SIZE);
        }

        return comparator;
    }

    private FilePartComparator getFullFileComparator(long fileSize) {
        return new FilePartComparator(0, fileSize);
    }

    private class FilePartComparator {
        private final long mBytesToSkip;
        private final long mBytesToCompare;

        //specified in Java documentation
        private static final long SIZE_OF_INT = 32;

        public FilePartComparator(long bytesToSkip, long bytesToCompare) {
            mBytesToSkip = bytesToSkip;
            mBytesToCompare = bytesToCompare;
        }

        public List<List<FileInfo>> compareByChunk(List<FileInfo> files) {
            for(FileInfo fileInfo: files) {
                try {
                    fileInfo.resetStream();
                    fileInfo.skip(mBytesToSkip);
                } catch (IOException e) {
                    ErrorHelper.ioExceptionReadingFile(fileInfo.getFile());
                    files.remove(fileInfo);
                }
            }

            List<List<FileInfo>> result = new ArrayList<>();
            result.add(files);

            long intsToCompare = mBytesToCompare / SIZE_OF_INT;

            for(int i = 0; i < intsToCompare; i++) {
                result = compareByNextInt(result);

                removeUniqueFiles(result);

                if(result.isEmpty()) {
                    break;
                }
            }

            return result;
        }

        private List<List<FileInfo>> compareByNextInt(List<List<FileInfo>> files) {
            List<List<FileInfo>> newPossiblyDuplicatedFiles = new ArrayList<>();

            for(List<FileInfo> possibleDuplicates: files) {
                Map<Integer, List<FileInfo>> partition = new HashMap<>();

                for(FileInfo file: possibleDuplicates) {
                    try {
                        List<FileInfo> currentFileGroup = partition.get(file.read());

                        if (currentFileGroup == null) {
                            currentFileGroup = new LinkedList<>();
                            partition.put(file.read(), currentFileGroup);
                        }

                        currentFileGroup.add(file);
                    } catch (IOException e) {
                        ErrorHelper.ioExceptionReadingFile(file.getFile());
                        possibleDuplicates.remove(file);
                    }
                }

                newPossiblyDuplicatedFiles.addAll(partition.values());
            }

            return newPossiblyDuplicatedFiles;
        }

        private void removeUniqueFiles(List<List<FileInfo>> files) {
            for(List<FileInfo> possibleDuplicates: files) {
                if(possibleDuplicates.size() == 1) {
                    files.remove(possibleDuplicates);
                }
            }
        }
    }
}
