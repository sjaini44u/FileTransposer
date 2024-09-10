package com.hsbc.exam;

import javax.naming.ldap.PagedResultsControl;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileTransposer {

    public static final int READ_BYTE_SIZE = 100;
    private final RandomAccessFile randomAccessFile;
    //this can be persisted as well for restart
    //in that case , while writing also , we need to take care of existing file
    private long prevPosition =0 ;

    public FileTransposer(RandomAccessFile  randomAccessFile){
        this.randomAccessFile = randomAccessFile;
    }

    public String transposeFile() throws IOException {
        if(prevPosition < 0){
            return "";
        }
        if (prevPosition == 0) {
            prevPosition = randomAccessFile.length();
        }

        byte[] data;
        //now we want to read fixed Size byte  bytes
        if (prevPosition < READ_BYTE_SIZE) {
            data = new byte[(int) prevPosition];
            randomAccessFile.seek(0);
            randomAccessFile.read(data);
            prevPosition = -1;
        } else {
            data = new byte[READ_BYTE_SIZE];
            randomAccessFile.seek(prevPosition - READ_BYTE_SIZE);
            randomAccessFile.read(data);
            prevPosition = prevPosition - READ_BYTE_SIZE;
        }
        //we can return byte[] as well and ask the writer to reverse the byte array
        //to avoid too much GC + String memory utilization
        return new String(data);
    }



}
