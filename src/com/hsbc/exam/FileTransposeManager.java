package com.hsbc.exam;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;

public class FileTransposeManager {

    private final FileTransposer transposer;
    private final FileWriter writer;

    public FileTransposeManager(FileTransposer transposer, FileWriter writer){
        this.transposer = transposer;
        this.writer = writer;
    }

    public void transpose() throws IOException {
        String transposedLine = "";
        do {
            transposedLine = transposer.transposeFile();
            if(!transposedLine.equals(""))
                writer.writeLine(transposedLine);
        } while(!transposedLine.equals(""));
    }
}
