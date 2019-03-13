package io;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class FileCopy {

    public static void main(String[] args) throws IOException {
        File source = new File("d:/idea服务器.txt");
        File dest = new File("d:/copy.md");

//        FileCopy.copyWithStream(source, dest);
        copyWithChannel(source, dest);
    }

    private static void copyWithStream(File source, File dest) throws IOException {
        InputStream in = new FileInputStream(source);
        OutputStream out = new FileOutputStream(dest);

        byte[] buffer = new byte[1024];
        int length;

        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }

        // 关闭资源
        in.close();
        out.close();
    }

    private static void copyWithChannel(File source, File dest) throws IOException {
        FileChannel sourceChannel = new FileInputStream(source).getChannel();
        FileChannel destChannel = new FileOutputStream(dest).getChannel();

        long count = sourceChannel.size();
        while (count > 0) {
            long transferred = sourceChannel.transferTo(sourceChannel.position(), count, destChannel);
            sourceChannel.position(sourceChannel.position() + transferred);
            count -= transferred;
        }

        sourceChannel.close();
        destChannel.close();
    }

    private static void copyWithUtils() throws IOException {
        OutputStream out = new FileOutputStream("readme.txt");
        Files.copy(new File("readme.md").toPath(), out);
    }
}
