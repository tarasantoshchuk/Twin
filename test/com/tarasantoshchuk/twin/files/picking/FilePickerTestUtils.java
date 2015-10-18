package com.tarasantoshchuk.twin.files.picking;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilePickerTestUtils {
    private static final long FILE_SIZE = 1;

    private static MockFile sRootFolder;

    private static List<File> sAllFilesRecursive;
    private static List<File> sAllFilesNonRecursive;

    static {
        MockFile file1 = new MockFile("file1", FILE_SIZE, false);
        MockFile subfolder1 = new MockFile("subfolder1", FILE_SIZE, true);
        subfolder1.addFile(file1);

        MockFile file2 = new MockFile("file2", FILE_SIZE, false);
        MockFile subfolder2 = new MockFile("subfolder2", FILE_SIZE, true);
        subfolder2.addFile(file2);

        MockFile file3 = new MockFile("file3", FILE_SIZE, false);
        MockFile file4 = new MockFile("file4", FILE_SIZE, false);

        sRootFolder = new MockFile("folder", FILE_SIZE, true);

        sRootFolder.addFile(subfolder1);
        sRootFolder.addFile(subfolder2);
        sRootFolder.addFile(file3);
        sRootFolder.addFile(file4);

        sAllFilesNonRecursive = Arrays.asList((File)file3, file4);
        sAllFilesRecursive = Arrays.asList((File)file1, file2, file3, file4);

    }

    public static File getRootFolder() {
        return sRootFolder;
    }

    public static List<File> getAllFilesRecursive() {
        return sAllFilesRecursive;
    }

    public static List<File> getAllFilesNonRecursive() {
        return sAllFilesNonRecursive;
    }
}
