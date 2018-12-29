package win.sinno.common.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import org.junit.Test;

/**
 * win.sinno.common.util.FileDelTest
 *
 * @author chenlizhong@qipeng.com
 * @date 2018/10/17
 */
public class FileDelTest {

  @Test
  public void testF() throws IOException {
    File file = new File("/Users/chenlizhong/logs/delTest.txt");

    FileChannel fc = new RandomAccessFile(file, "rw").getChannel();
    MappedByteBuffer mappedByteBuffer = fc.map(MapMode.READ_WRITE, 0, file.length());

    ByteBuffer byteBuffer = mappedByteBuffer.slice();

    int len = byteBuffer.limit();

    byte[] bs = new byte[len];

    byteBuffer.get(bs);
    System.out.println(new String(bs));

    boolean ret = file.delete();

    System.out.println("del ret:" + ret);

    String hello = "hello";

    byte[] bytes = hello.getBytes();
    ByteBuffer bbf = mappedByteBuffer.slice();
    bbf.put(bytes);

    mappedByteBuffer.force();

    mappedByteBuffer.position(0);

    byteBuffer = mappedByteBuffer.slice();

    bs = new byte[len];

    byteBuffer.get(bs);

    System.out.println(new String(bs));
  }

}
