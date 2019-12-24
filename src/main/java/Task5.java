import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Task5 {
    public static void main(String[] args) {
        printFileInReverse(new File("files/task3.txt"));
    }

    public static void printFileInReverse(File file) {
        int blockSize = 1024;
        byte[] block = new byte[blockSize];

        try(RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            long startingPoint = raf.length() / blockSize * blockSize;
            int nOfRead;
            while (startingPoint >= 0) {
                raf.seek(startingPoint);
                nOfRead = raf.read(block);
                for (int i = nOfRead - 1; i >= 0; i--) {
                    System.out.print((char)block[i]);
                }
                startingPoint -= blockSize;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
