package io;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

import static java.nio.channels.FileChannel.MapMode.READ_ONLY;

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

    /**
     * Direct Buffer：如果我们看Buffer的方法定义，你会发现它定义了isDirect()方法，
     * 返回当前Buffer是否是Direct类型。这是因为Java提供了堆内和堆外（Direct）Buffer，
     * 我们可以以它的allocate或者allocateDirect方法直接创建。
     *
     * MappedByteBuffer：它将文件按照指定大小直接映射为内存区域，当程序访问这个内存区域
     * 时将直接操作这块儿文件数据，省去了将数据从内核空间向用户空间传输的损耗。我们可以使用
     * FileChannel.map创建MappedByteBuffer，它本质上也是种Direct Buffer。
     *
     * 垃圾回收机制：Cleaner（一个内部实现）和幻象引用（PhantomReference）机制，其本身
     * 不是public类型，内部实现了一个Deallocator负责销毁的逻辑。对它的销毁往往要拖到full
     * GC的时候，所以使用不当很容易导致OutOfMemoryError。
     *
     * @throws IOException
     */
    private static void directBuffer() throws IOException {
        FileChannel channel = new FileOutputStream("").getChannel();
        channel.map(READ_ONLY, 0, 100);
    }
}
