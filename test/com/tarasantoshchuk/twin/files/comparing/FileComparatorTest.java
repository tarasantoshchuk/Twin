package com.tarasantoshchuk.twin.files.comparing;

import com.tarasantoshchuk.twin.files.picking.MockFile;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FileComparatorTest {
    private static String FILE_NAME = "file";
    private static Long FILE_SIZE_1 = (long) 1;
    private static Long FILE_SIZE_2 = (long) 2;
    private static Long FILE_SIZE_3 = (long) 3;

    private FileComparator mFileComparator = new FileComparator();

    @Test
    public void testSortFilesBySize() {
        File file1 = new MockFile(FILE_NAME, FILE_SIZE_1, false);
        File file2 = new MockFile(FILE_NAME, FILE_SIZE_1, false);
        File file3 = new MockFile(FILE_NAME, FILE_SIZE_2, false);
        File file4 = new MockFile(FILE_NAME, FILE_SIZE_2, false);
        File file5 = new MockFile(FILE_NAME, FILE_SIZE_3, false);

        List<File> expectedFilesWithSize1 = Arrays.asList(file1, file2);
        List<File> expectedFilesWithSize2 = Arrays.asList(file3, file4);
        List<File> expectedFilesWithSize3 = Collections.singletonList(file5);

        List<File> files = Arrays.asList(file1, file2, file3, file4, file5);

        Map<Long, List<File>> actualSortedFiles = mFileComparator.sortFilesBySize(files);

        assertEquals(expectedFilesWithSize1, actualSortedFiles.get(FILE_SIZE_1));
        assertEquals(expectedFilesWithSize2, actualSortedFiles.get(FILE_SIZE_2));
        assertEquals(expectedFilesWithSize3, actualSortedFiles.get(FILE_SIZE_3));
    }
}