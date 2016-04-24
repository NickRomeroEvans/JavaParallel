import java.util.concurrent.RecursiveTask;

/**
 * @author David Swanson
 * @coauthor Nickolas Evans
 * Skeleton to compute the longest sequence of a given number
 * in an array using the Fork-Join framework
 * 
 * 
 * part c) Using a sequential cut-off is more efficient because it stops from creating an exorbitant of threads that could flood memory.
 * Also creating a thread can take more time than scanning through multiple elements in an array instead.
 */
public class LongestSequenceCutOff extends RecursiveTask<Result> {
	private static final int SEQUENTIAL_CUTOFF=25;
	private int checker;
	private int[] array;
	private int low;
	private int high;
    /**
     * Constructor
     * 
     * @param x
     *            The target number to find the longest sequence of.
     * @param array
     *            The source array where the sequence is located
     * @param low
     *            The lower bound on the search
     * @param high
     *            The upper bound on the search
     */
    LongestSequenceCutOff(int x, int[] array, int low, int high) {
    	this.checker = x;
    	this.array = array;
    	this.low = low;
    	this.high = high;
    }

    /**
     * Computes the longest sequence with a sequential cutoff
     */
    @Override
	protected Result compute() {
		if(high - low <= SEQUENTIAL_CUTOFF){
			int elements = high - low;
			boolean entireBlock = true;
			int leftEdge = 0;
			int longest = 0;
			int rightEdge = 0; //Is the main counter 
			for(int i = 0; i < elements; i++){
				if(array[low+i] == checker){
					rightEdge++;
				}else{
					if(entireBlock){
						//Found an element that disrupts the initial sequence
						leftEdge = rightEdge;
						entireBlock = false;		//Entire block is not a sequence
					}
					if(longest < rightEdge){
						longest = rightEdge;
					}
					rightEdge = 0;//Reset counter
				}
			}
			if(entireBlock){
				//know entire block is a sequence
				return new Result(rightEdge,rightEdge,rightEdge,entireBlock);
			}else{
				if(longest < rightEdge){
					longest = rightEdge;
				}
				return new Result(leftEdge,rightEdge,longest,entireBlock);
			}
		}
		LongestSequenceCutOff left = new LongestSequenceCutOff(checker, array, low, (high+low)/2);
		LongestSequenceCutOff right = new LongestSequenceCutOff(checker, array, (low+high)/2, high);
		left.fork();
		Result rightResult = right.compute();
		Result leftResult = left.join();
		if(leftResult.entireRange){
			if(rightResult.entireRange){
				//left and right are entire range
				leftResult.numLeftEdge = leftResult.numLeftEdge+rightResult.numLeftEdge;
				leftResult.numLongest = leftResult.numLeftEdge;
				leftResult.numRightEdge = leftResult.numLeftEdge;
				return leftResult; // left = right = longest entire range = true
			}else{
				//left entire range right not
				rightResult.numLeftEdge = rightResult.numLeftEdge+leftResult.numLongest;
				if(rightResult.numLeftEdge > rightResult.numLongest){
					rightResult.numLongest = rightResult.numLeftEdge;
				}
				return rightResult;
			}
		}else{
			if(rightResult.entireRange){
				//left not entire range && right has entire range
				leftResult.numRightEdge = leftResult.numRightEdge + rightResult.numLongest;
				if(leftResult.numRightEdge > leftResult.numLongest){
					leftResult.numLongest = leftResult.numRightEdge;
				}
				return leftResult;
			}else{
				// left not entire range and right not entire range
				Result s = new Result(leftResult.numLeftEdge,rightResult.numRightEdge,0,false);
				int mid = leftResult.numRightEdge + rightResult.numLeftEdge;
				if(mid >= leftResult.numLongest && mid >= rightResult.numLongest){
					s.numLongest = mid;
				}else{
					if(leftResult.numLongest > rightResult.numLongest){
						s.numLongest = leftResult.numLongest;
					}else{
						s.numLongest = rightResult.numLongest;
					}
				}
				return s;
			}
		}
    }

}
