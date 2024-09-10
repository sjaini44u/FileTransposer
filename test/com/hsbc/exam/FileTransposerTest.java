package com.hsbc.exam;

import com.sun.org.apache.xpath.internal.Arg;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Any;
import org.mockito.internal.verification.Times;

import java.io.IOException;
import java.io.RandomAccessFile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FileTransposerTest {

    @Test
    public void testFileTransposer() throws IOException {
        RandomAccessFile randomAccessFile =
                Mockito.mock(RandomAccessFile.class, Mockito.withSettings().stubOnly());
        Mockito.doReturn(100l).when(randomAccessFile).length();
        Mockito.doNothing().when(randomAccessFile).read(any());

        FileTransposer fileTransposer = new FileTransposer(randomAccessFile);

        FileWriter writer = mock(FileWriter.class);
        when(writer.writeLine(any())).thenReturn(true);

        FileTransposeManager manager = new FileTransposeManager(fileTransposer, writer);
        manager.transpose();

        ArgumentCaptor<Long> c = ArgumentCaptor.forClass(Long.class);
        verify(randomAccessFile, times(1)).seek(c.capture());
        System.out.println(c.getValue());
    }

    @Test
    public void testFileTransposerForDoubleOfByteArrayLength() throws IOException {
        RandomAccessFile randomAccessFile =
                Mockito.mock(RandomAccessFile.class, Mockito.withSettings().stubOnly());
        Mockito.doReturn(200l).when(randomAccessFile).length();
        Mockito.doNothing().when(randomAccessFile).read(any());

        FileTransposer fileTransposer = new FileTransposer(randomAccessFile);

        FileWriter writer = mock(FileWriter.class);
        when(writer.writeLine(any())).thenReturn(true);

        FileTransposeManager manager = new FileTransposeManager(fileTransposer, writer);
        manager.transpose();

        ArgumentCaptor<Long> c = ArgumentCaptor.forClass(Long.class);
        verify(randomAccessFile, times(2)).seek(c.capture());

        Assert.assertEquals((long) 101, (long) c.getAllValues().get(0));
        Assert.assertEquals((long) 0, (long) c.getAllValues().get(0));
    }

    @Test
    public void testFileTransposerForBiggerLength() throws IOException {
        RandomAccessFile randomAccessFile =
                Mockito.mock(RandomAccessFile.class, Mockito.withSettings().stubOnly());
        Mockito.doReturn(5000l).when(randomAccessFile).length();
        Mockito.doNothing().when(randomAccessFile).read(any());

        FileTransposer fileTransposer = new FileTransposer(randomAccessFile);

        FileWriter writer = mock(FileWriter.class);
        when(writer.writeLine(any())).thenReturn(true);

        FileTransposeManager manager = new FileTransposeManager(fileTransposer, writer);
        manager.transpose();

        ArgumentCaptor<Long> c = ArgumentCaptor.forClass(Long.class);
        verify(randomAccessFile, times(50)).seek(c.capture());
    }
}
