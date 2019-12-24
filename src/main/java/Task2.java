import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Task2 {

    public static void main(String[] args) throws IOException {
        ArrayList<InputStream> al = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            al.add(new FileInputStream("files/" + i + ".txt"));
        }
        try (SequenceInputStream sequenceInputStream = new SequenceInputStream(Collections.enumeration(al))) {
            File outFile = new File("files/task2.txt");
            outFile.createNewFile();
            try (FileOutputStream fileOutputStream = new FileOutputStream(outFile)) {
                byte[] input = new byte[128];
                int nOfChars;
                try {
                    while ((nOfChars = sequenceInputStream.read(input)) != -1) {
                        fileOutputStream.write(input, 0, nOfChars);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
