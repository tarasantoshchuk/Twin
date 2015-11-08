package com.tarasantoshchuk.twin.files;

import java.io.*;

public class FileInfo {
    private File mFile;
    private BufferedInputStream mInputStream;

    public FileInfo(File file) throws FileNotFoundException {
        mInputStream = new BufferedInputStream(new FileInputStream(file));
        mFile = file;
    }

    public File getFile() {
        return mFile;
    }

    public int read() throws IOException {
        return mInputStream.read();
    }

    public long skip(long n) throws IOException {
        return mInputStream.skip(n);
    }

    public void resetStream() throws IOException {
        mInputStream.close();

        mInputStream = new BufferedInputStream(new FileInputStream(mFile));
    }
}
