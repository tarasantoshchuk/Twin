package com.tarasantoshchuk.twin.files.picking;

import com.tarasantoshchuk.twin.input.Flag;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class RecursiveFilePicker extends FilePicker {
    /**
     * Constructor, assumes that rootFolder is indeed folder (#File.listFiles() is not null)
     * @param rootFolder - root folder to look for matching files
     * @param matchers - list of matchers that set file constraints
     */
    protected RecursiveFilePicker(File rootFolder, List<Flag.Matcher> matchers) {
        super(rootFolder, matchers);
    }

    @Override
    protected List<File> getAllFiles() {
        File rootFolder = getRootFolder();
        return getFolderFilesRecursively(rootFolder);
    }

    private List<File> getFolderFilesRecursively(File folder) {
        List<File> result = new ArrayList<>();

        File[] folderEntries = folder.listFiles();

        for(File folderEntry: folderEntries) {
            if(folderEntry.isFile()) {
                result.add(folderEntry);
            } else if(folderEntry.isDirectory()) {
                result.addAll(getFolderFilesRecursively(folderEntry));
            }
        }

        return result;
    }
}
