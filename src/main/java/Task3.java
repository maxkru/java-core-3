import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Task3 {
    private final static int PAGE_SIZE = 1800;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Page?: ");
        int pageNumber = scanner.nextInt();
        printPage(new File("files/task3.txt"), pageNumber);
    }

    public static void printPage(File file, int pageNumber) {
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            byte[] page = new byte[PAGE_SIZE];
            raf.seek(PAGE_SIZE * (pageNumber - 1));
            int nOfRead = raf.read(page);
            if(nOfRead > 0)
                System.out.println(new String(page, 0, nOfRead));
            else
                System.out.println("No such page.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
