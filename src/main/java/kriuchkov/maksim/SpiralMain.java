package kriuchkov.maksim;

public class SpiralMain {
    public static void main(String[] args) {
        int n = 4;
        int[][] spiral = new Spiral().makeSpiral(n);
        int maxL = Integer.toString(n * n).length();
        String format = "%" + (maxL + 1) + "d";
        for (int i = 0; i < spiral.length; i++) {
            for (int j = 0; j < spiral[i].length; j++) {
                System.out.printf(format, spiral[i][j]);
            }
            System.out.println();
        }

    }
}
