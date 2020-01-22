import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

public class ExtractAllAfterFourTests {
//    IntArrayMethods intArrayMethods;

    // нечего инициализировать, статические методы
/*    @Before
    public void init() {

    }*/

/*    @Parameterized.Parameters
    public static Collection<Object[][]> data() {
        return Arrays.asList(new Object[][][] {
                // initial, result
                {{1,2,3,4,5},{5}},
                {{4,-100,0,1},{-100,0,1}},
                {{-51,10,1,4,1,10},{1,10}},
                {{0,0,0,4,4,4,0},{4,4,0}}
        });
    }

    int[] initial;
    int[] result;

    public ExtractAllAfterFourTests(int[] initial, int[] result) {
        this.initial = initial;
        this.result = result;
    }

    @Test
    public void testMass() {
        Assert.assertArrayEquals(result, IntArrayMethods.extractAllAfterFour(initial));
    }
    */

    @Test(expected = RuntimeException.class)
    public void testNoFour() {
        IntArrayMethods.extractAllAfterFour(new int[]{1, 2, 3});
    }

    @Test
    public void testSingleFour() {
        Assert.assertArrayEquals(new int[0], IntArrayMethods.extractAllAfterFour(new int[]{4}));
    }

    @Test
    public void testNormal1() {
        Assert.assertArrayEquals(new int[]{5}, IntArrayMethods.extractAllAfterFour(new int[]{1,2,3,4,5}));
    }

    @Test
    public void testNormal2() {
        Assert.assertArrayEquals(new int[]{-100,0,1}, IntArrayMethods.extractAllAfterFour(new int[]{4,-100,0,1}));
    }

}
