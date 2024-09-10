package com.hsbc.exam;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        FileTransposeWorker worker = new FileTransposeWorker();
        try {
            worker.transpose("ABC.txt", "CBA.txt");
        }
        catch (FileNotFoundException e){
            System.out.println("Please Note , file doesnt exist " +  e);
        }
        catch (IOException ioex){
            System.out.println("Please check, IO Exception " +  ioex);
        }
    }
}