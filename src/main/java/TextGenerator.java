import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class TextGenerator {
    public static void main(String[] args) throws IOException {
        generateTextFile(new File("files/task3.txt"), 20000000);
    }

    public static void generateTextFile(File file, long size) throws IOException {
        int blockSize = 1024;
        byte[] block = new byte[blockSize];

        file.createNewFile();

        Random random = new Random();
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            for (long rem = size; rem > 0; rem -= blockSize) {
                for (int j = 0; j < blockSize; j++) {
                    block[j] = (byte) ('A' + random.nextInt('z' - 'A'));
                }
                fileOutputStream.write(block, 0, rem > blockSize ? blockSize : (int) rem);
            }
        }
    }
}
