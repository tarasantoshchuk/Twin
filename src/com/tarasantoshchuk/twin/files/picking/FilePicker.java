package com.tarasantoshchuk.twin.files.picking;

import com.tarasantoshchuk.twin.input.Flag.Matcher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class FilePicker {
    private List<Matcher> mMatchers;
    private File mRootFolder;

    protected FilePicker(File rootFolder, List<Matcher> matchers) {
        mRootFolder = rootFolder;
        mMatchers = matchers;
    }

    public final List<File> getFiles() {
        List<File> allFiles = getAllFiles();
        List<File> matchingFiles = new ArrayList<>();

        for(File file: allFiles) {
            if(file.isFile() && isFileMatch(file)) {
                matchingFiles.add(file);
            }
        }

        return matchingFiles;
    }

    protected abstract List<File> getAllFiles();

    protected File getRootFolder() {
        return mRootFolder;
    }

    private boolean isFileMatch(File file) {
        boolean result = true;

        for(Matcher matcher: mMatchers) {
            if(!matcher.isMatch(file)) {
                result = false;
                break;
            }
        }

        return result;
    }

}
