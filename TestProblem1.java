import java.util.concurrent.ForkJoinPool;

/**
 * 
 * Testing/Running Framework for homework 6 problem 1
 * @author Nickolas Evans
 * Section AB
 */

public class TestProblem1 {

    public static ForkJoinPool fjPool = new ForkJoinPool();

    public static void main(String[] args) {

	int[] sample = new int[] { 2, 17, 19, 8, 21, 17, 35, 0, 4, 1 };

	boolean oldEnough = isOver(17, sample);
	System.out.println(oldEnough == true ? "Test passed" : "Test failed");

	oldEnough = isOver(35, sample);
	System.out.println(oldEnough == false ? "Test passed" : "Test failed");
    }

    public static boolean isOver(int i, int[] arr) {
    	IsOver o = new IsOver(arr,0,arr.length,i);
    	Boolean s= fjPool.invoke(o);
    	return s;
    }

}

