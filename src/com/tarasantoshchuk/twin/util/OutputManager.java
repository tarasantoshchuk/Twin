package com.tarasantoshchuk.twin.util;

import java.io.File;
import java.util.List;

public class OutputManager {
    public static void printResult(List<List<File>> duplicates) {
        int groupCounter = 0;
        for(List<File> duplicatedGroup: duplicates) {
            groupCounter++;

            System.out.println("Group of duplicated files #" + groupCounter);
            for(File file: duplicatedGroup) {
                System.out.println(file.getPath());
            }
        }
    }
}
