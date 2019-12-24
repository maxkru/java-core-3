import java.io.*;
import java.nio.charset.StandardCharsets;

public class Task1 {
    public static void main(String[] args) {
        FileInputStream inputStream = null;

        try {
            byte[] arrByte = new byte[128];
            inputStream = new FileInputStream("files/12345.txt");
            int numSymbols;
            while ((numSymbols = inputStream.read(arrByte)) != -1) {
                System.out.print(new String(arrByte, 0, numSymbols, StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
