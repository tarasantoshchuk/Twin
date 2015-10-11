package com.tarasantoshchuk.twin.files.picking;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NonRecursiveFilePicker implements FilePicker {
    private File mTargetFolder;

    public NonRecursiveFilePicker(File targetFolder) {
        mTargetFolder = targetFolder;
    }

    @Override
    public List<File> getFiles() {
        File[] folderEntries = mTargetFolder.listFiles();

        List<File> folderFiles = new ArrayList<>();

        for(File entry: folderEntries) {
            if(entry.isFile()) {
                folderFiles.add(entry);
            }
        }

        return folderFiles;
    }
}
