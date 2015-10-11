package com.tarasantoshchuk.twin.input;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class InputArguments {
    private File mTargetFolder;
    private List<FlagWithArguments> mFlags = new ArrayList<>();

    public File getTargetFolder() {
        return mTargetFolder;
    }

    public boolean hasFlag(Flag flag) {
        boolean flagFound = false;

        for(FlagWithArguments flagWithArgs: mFlags) {
            if(flagWithArgs.getFlag() == flag) {
                flagFound = true;
                break;
            }
        }

        return flagFound;
    }

    public List<FlagWithArguments> getFlags() {
        return mFlags;
    }

    void setTargetFolder(File pathToFolder) {
        mTargetFolder = pathToFolder;
    }

    void addFlag(Flag flag, List<String> flagArguments) {
        mFlags.add(new FlagWithArguments(flag, flagArguments));
    }

    public static class FlagWithArguments {
        private Flag mFlag;
        private List<String> mArguments;

        public FlagWithArguments(Flag flag, List<String> arguments) {
            mFlag = flag;
            mArguments = arguments;
        }

        public Flag getFlag() {
            return mFlag;
        }

        public List<String> getArguments() {
            return mArguments;
        }
    }
}
