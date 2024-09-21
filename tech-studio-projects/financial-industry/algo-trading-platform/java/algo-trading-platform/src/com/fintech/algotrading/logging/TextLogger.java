package com.fintech.algotrading.logging;

public class TextLogger {

    private String directory;
    private String filename;
    private String fileExtension;

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String dir) {
        this.directory = dir;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String file) {
        this.filename = file;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String ext) {
        this.fileExtension = ext;
    }
}
