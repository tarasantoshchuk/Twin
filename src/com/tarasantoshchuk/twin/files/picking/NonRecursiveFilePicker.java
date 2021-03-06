package com.tarasantoshchuk.twin.files.picking;

import com.tarasantoshchuk.twin.input.Flag;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NonRecursiveFilePicker extends FilePicker {
    /**
     * Constructor, assumes that rootFolder is indeed folder (#File.listFiles() is not null)
     * @param rootFolder - root folder to look for matching files
     * @param matchers - list of matchers that set file constraints
     */
    protected NonRecursiveFilePicker(File rootFolder, List<Flag.Matcher> matchers) {
        super(rootFolder, matchers);
    }

    @Override
    protected List<File> getAllFiles() {
        List<File> result = new ArrayList<>();

        File[] rootFolderFiles = getRootFolder().listFiles();

        for(File file: rootFolderFiles) {
            if(file.isFile()) {
                result.add(file);
            }
        }

        return result;
    }
}
