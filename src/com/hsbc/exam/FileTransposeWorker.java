package com.hsbc.exam;

import java.io.IOException;
import java.io.RandomAccessFile;

public class FileTransposeWorker{


    public void transpose(String sourceFile, String destFile) throws IOException {
        RandomAccessFile reader = new RandomAccessFile(sourceFile, "r");
        RandomAccessFile rcwriter = new RandomAccessFile(destFile, "rw");

        FileTransposer fileTransposer = new FileTransposer(reader);
        FileWriter writer = new FileWriter(rcwriter);

        FileTransposeManager manager = new FileTransposeManager(fileTransposer, writer);
        manager.transpose();
    }

}
