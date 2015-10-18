package com.tarasantoshchuk.twin.files.picking;


import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MockFile extends File {
    private boolean mIsDirectory;
    private List<MockFile> mFiles;
    private String mFileName;
    private long mLength;

    public MockFile(String fileName, long length, boolean isDirectory) {
        super(fileName);
        mIsDirectory = isDirectory;
        mFileName = fileName;
        mLength = length;

        if(isDirectory) {
            mFiles = new ArrayList<>();
        }
    }

    public void addFile(MockFile file) {
        if(mIsDirectory) {
            mFiles.add(file);
        }
    }

    @Override
    public long length() {
        return mLength;
    }

    @Override
    public boolean isFile() {
        return !mIsDirectory;
    }

    @Override
    public boolean isDirectory() {
        return mIsDirectory;
    }

    @Override
    public File[] listFiles() {
        return mFiles.toArray(new File[mFiles.size()]);
    }

    @Override
    @NotNull
    public String getName() {
        return mFileName;
    }
}
