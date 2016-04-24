
/**
 * 
 * Testing/Running Framework for homework 6 
 * 
 */

public class RunHW6 {

    public static void main(String[] args) {

	int[] sample1 = new int[] { 2, 17, 19, 8, 21, 17, 35, 0, 4, 1 };

	boolean oldEnough = TestProblem1.isOver(17, sample1);
	System.out.println(oldEnough == true ? "Test passed" : "Test failed");

	oldEnough = TestProblem1.isOver(35, sample1);
	System.out.println(oldEnough == false ? "Test passed" : "Test failed");

	int[] sample2 = new int[] { 2, 17, 17, 8, 17, 17, 17, 0, 17, 1 };

	int longest = TestProblem2.getLongestSequence(17, sample2);
	System.out.println(longest == 3 ? "Test passed" : "Test failed");

	longest = TestProblem2.getLongestSequence(9, sample2);
	System.out.println(longest == 0 ? "Test passed" : "Test failed");

	longest = TestProblem2.getLongestSequenceCutOff(17, sample2);
	System.out.println(longest == 3 ? "Test passed" : "Test failed");

	longest = TestProblem2.getLongestSequenceCutOff(9, sample2);
	System.out.println(longest == 0 ? "Test passed" : "Test failed");  

	char[] array = "Dudecse4ocse332momcse332Rox".toCharArray();
		
	int leftMost = TestProblem3.getLeftMostIndex("cse332".toCharArray(), array);
	System.out.println(leftMost == 9 ? "Test passed" : "Test failed");  

    }
}

