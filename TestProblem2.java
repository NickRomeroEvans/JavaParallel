import java.util.concurrent.ForkJoinPool;

/**
 * 
 * Testing/Running Framework for homework 6 problem 2
 * @author Nickolas Evans
 * Section AB
 */

public class TestProblem2 {

    public static ForkJoinPool fjPool = new ForkJoinPool();

    public static void main(String[] args) {

	int[] sample = new int[] { 2, 17, 17, 8, 17, 17, 17, 0, 17, 1 };

	int longest = getLongestSequence(17, sample);
	System.out.println(longest == 3 ? "Test passed" : "Test failed");

	longest = getLongestSequence(9, sample);
	System.out.println(longest == 0 ? "Test passed" : "Test failed");
    }

    public static int getLongestSequence(int i, int[] arr) {
		
	LongestSequence ls = new LongestSequence(i, arr, 0, arr.length);
	Result s = fjPool.invoke(ls);
	return s.numLongest;
    }
    
    public static int getLongestSequenceCutOff(int i, int[] arr){
    	LongestSequenceCutOff co = new LongestSequenceCutOff(i,arr,0, arr.length);
    	Result s = fjPool.invoke(co);
    	return s.numLongest;
    }

}

