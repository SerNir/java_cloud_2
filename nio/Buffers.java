package nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.RandomAccess;

public class Buffers {
    public static void main(String[] args) throws Exception{
        ByteBuffer buffer = ByteBuffer.allocate(5);

        RandomAccessFile raf = new RandomAccessFile("clientDir/2.txt","rw");
        FileChannel fileChannel = raf.getChannel();

        for (int i = 0; i < 3; i++) {
            byte b = (byte)('a' + i);
            buffer.put(b);
        }


        buffer.flip();

        for (int i = 0; i < 3; i++) {
            System.out.println((char)buffer.get());
        }

        buffer.clear();

        while(true){
            int read = fileChannel.read(buffer);
            if(read <= 0){
                break;
            }

            buffer.flip();
            while(buffer.hasRemaining()){
                System.out.print((char)buffer.get());
            }
            buffer.clear();
        }
    }
}