package com.tarasantoshchuk.twin.input;

import java.io.File;

public class InputArguments {
    private File mTargetFolder;

    public File getTargetFolder() {
        return mTargetFolder;
    }

    void setTargetFolder(File pathToFolder) {
        mTargetFolder = pathToFolder;
    }
}
