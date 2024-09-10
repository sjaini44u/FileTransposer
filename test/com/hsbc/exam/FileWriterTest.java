package com.hsbc.exam;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.RandomAccessFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class FileWriterTest {


    @Test
    public void testFileWriterIsRevertingString() throws IOException {
        RandomAccessFile randomAccessFile =
                Mockito.mock(RandomAccessFile.class, Mockito.withSettings().stubOnly());

        String testString = "abcd efgh";

        FileWriter writer = new FileWriter(randomAccessFile);

        ArgumentCaptor<String> c = ArgumentCaptor.forClass(String.class);

        writer.writeLine(testString);
        verify(randomAccessFile, times(1)).writeBytes(c.capture());

        Assert.assertEquals("Values Not Matching" , "hgfe dcba", c.getValue());
    }

}
