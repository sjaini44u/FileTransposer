package com.hsbc.exam;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class FileWriter {

    private final  RandomAccessFile rcWriter;
    public FileWriter(RandomAccessFile filer){
        this.rcWriter = filer;
    }

    public boolean writeLine(String line) throws IOException {
        this.rcWriter.writeBytes(new StringBuilder(line.replace("\r\n", "\n")).reverse().toString());
        return true;
    }
}
