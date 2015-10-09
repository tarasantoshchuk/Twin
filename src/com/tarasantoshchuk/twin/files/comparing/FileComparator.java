package com.tarasantoshchuk.twin.files.comparing;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileComparator {
    public List<List<File>> getDuplicates(List<File> filesToCompare) {
        List<List<File>> duplicates = new ArrayList<List<File>>();
        duplicates.add(filesToCompare);
        return duplicates;
    }
}
