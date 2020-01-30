import kriuchkov.maksim.Company;
import kriuchkov.maksim.CompanyTree;

import java.io.*;

public class CompanyMain {
    public static void main(String[] args) throws IOException {
        CompanyTree tree = new CompanyTree();

        File file = new File("files/tree_data.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        reader.readLine();
        String line;
        while((line = reader.readLine()) != null && !line.isEmpty()) {
            int[] ids = parseLine(line);
            tree.add(new Company(ids[0], ids[1]));
        }
        tree.print();
    }

    private static int[] parseLine(String line) {
        String[] ints = line.split(" {2}", 2);
        int[] result = new int[ints.length];
        for (int i = 0; i < ints.length; i++) {
            result[i] = Integer.parseInt(ints[i]);
        }
        return result;
    }
}
