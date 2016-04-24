import java.util.concurrent.RecursiveTask;

/**
 * @author David Swanson
 * @coauthor Nickolas Evans
 * Section AB
 */
public class LongestSequence extends RecursiveTask<Result> {
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
    LongestSequence(int x, int[] array, int low, int high) {
    	this.checker = x;
    	this.array = array;
    	this.low = low;
    	this.high = high;
    }

    /**Returns object Result
     * 
     * Computes the longest sequence 
     */
    @Override
	protected Result compute() {
		if(high - low == 1){
			if(array[low] == checker){
				return new Result(1,1,1,true);
			}else{
				return new Result(0,0,0,false);
			}
		}
		LongestSequence left = new LongestSequence(checker, array, low, (high+low)/2);
		LongestSequence right = new LongestSequence(checker, array, (low+high)/2, high);
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
