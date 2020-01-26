package kriuchkov.maksim;

public class Spiral {

    int[][] makeSpiral(int n) {
        int[][] result = new int[n][n];
        int[] dir = {1, 0};
        int x = -1;
        int y = 0;
        int i = 1;
        int nextCellValue;
        while (i <= n * n) {
            try {
                nextCellValue = result[y + dir[1]][x + dir[0]];
            } catch (ArrayIndexOutOfBoundsException e) {
                rotateDir(dir);
                continue;
            }
            if (nextCellValue == 0) {
                x += dir[0];
                y += dir[1];
                result[y][x] = i++;
            } else {
                rotateDir(dir);
            }
        }

        return result;
    }

    private void rotateDir(int[] dir) {
        if(dir[0] == 1) {
            dir[0] = 0;
            dir[1] = 1;
        } else if (dir[0] == -1) {
            dir[0] = 0;
            dir[1] = -1;
        } else if (dir[1] == 1) {
            dir[0] = -1;
            dir[1] = 0;
        } else {
            dir[0] = 1;
            dir[1] = 0;
        }
    }

}
