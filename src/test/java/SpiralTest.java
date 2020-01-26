import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SpiralTest {
    Spiral spiral;

    @Before
    public void init() {
        spiral = new Spiral();
    }


    @Test
    public void testNIs1() {
        Assert.assertArrayEquals(new int[][]{{1}}, spiral.makeSpiral(1));
    }

    @Test
    public void testNIs2() {
        Assert.assertArrayEquals(new int[][]{{1, 2}, {4, 3}}, spiral.makeSpiral(2));
    }

    @Test
    public void testNIs4() {
        Assert.assertArrayEquals(new int[][]{
                {1, 2, 3, 4},
                {12,13,14,5},
                {11,16,15,6},
                {10,9, 8, 7}
        }, spiral.makeSpiral(4));
    }

    @Test
    public void testNIs5() {
        Assert.assertArrayEquals(new int[][]{
                {1, 2, 3, 4, 5},
                {16,17,18,19,6},
                {15,24,25,20,7},
                {14,23,22,21,8},
                {13,12,11,10,9}
        }, spiral.makeSpiral(5));
    }

}
