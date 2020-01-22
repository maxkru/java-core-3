import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class CheckIfOnlyOnesAndFoursInArrayTests {

    @Test
    public void testOnlyFours() {
        Assert.assertTrue(IntArrayMethods.checkIfOnlyOnesAndFoursInArray(new int[]{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4}));
    }

    @Test
    public void testOnlyOnes() {
        Assert.assertTrue(IntArrayMethods.checkIfOnlyOnesAndFoursInArray(new int[]{1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void testEmpty() {
        Assert.assertTrue(IntArrayMethods.checkIfOnlyOnesAndFoursInArray(new int[0]));
    }

    @Test(expected = NullPointerException.class)
    public void testNull() {
        IntArrayMethods.checkIfOnlyOnesAndFoursInArray(null);
    }

    @Test
    public void testFalse() {
        Assert.assertFalse(IntArrayMethods.checkIfOnlyOnesAndFoursInArray(new int[]{0, 1, 4, 1, 4, 4, 1}));
    }

    @Test
    public void testMaxValue() {
        Assert.assertFalse(IntArrayMethods.checkIfOnlyOnesAndFoursInArray(new int[]{1, 4, 1, 4, Integer.MAX_VALUE, 1}));
    }

    @Test
    public void testMinValue() {
        Assert.assertFalse(IntArrayMethods.checkIfOnlyOnesAndFoursInArray(new int[]{4, Integer.MIN_VALUE, 1, 4, 1, 1}));
    }
}
