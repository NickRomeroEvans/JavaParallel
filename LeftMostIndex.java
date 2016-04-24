/**Nickolas Evans
 * Section AB
 * 
 * */
import java.util.concurrent.RecursiveTask;

public class LeftMostIndex extends RecursiveTask<LeftMostResult>{
	private char[] array;
	private char[] goal;
	private int low;
	private int high;
	private static final int SEQUENTIAL_CUTOFF=100;
	
	
	public LeftMostIndex(char[] array, char[] goal, int low, int high){
		this.array = array;
		this.goal = goal;
		this.high = high;
		this.low = low;
	}
		
	@Override
	protected LeftMostResult compute() {
		if(high - low <= SEQUENTIAL_CUTOFF){
			//scan through and return results
			int elements = high - low;
			int count = 0;
			for(int i = 0; i< elements; i++){

				if(array[low+i] == goal[count]){
					count++;
				}else{
					count = 0; //reset count if a point is not equal
				}
				if(count == goal.length){
					//Found the left most of the provided section
					return new LeftMostResult(true, false, low+i-count+1);
				}
			}
			if(count != 0){
				//Incomplete at the end of the provided section of the array
				return new LeftMostResult(false,true,high-count);
			}else{
				return new LeftMostResult(false,false, -1);
			}
		}
		LeftMostIndex left = new LeftMostIndex(array, goal, low, (low+high)/2);
		LeftMostIndex right = new LeftMostIndex(array, goal, (high+low)/2, high);
		left.fork();
		LeftMostResult rightResult = right.compute();
		LeftMostResult leftResult = left.join();
		if(leftResult.found){ 
			//left has left most result
			return leftResult;
		}else{
			if(leftResult.incomplete && leftResult.index+goal.length<high){
				//left might have the sequence with the front of right combined
				for(int i = 0; i < goal.length; i++){
					if(array[leftResult.index+i] != goal[i]){
						//Left doesn't have the sequence so return rightResult
						return rightResult;
					}
				}
				return new LeftMostResult(true, false, leftResult.index);
			}
			if(leftResult.incomplete&& leftResult.index+goal.length>=high ){
				//Needs more elements at the end in order to determine if this is the valid sequence
				return leftResult;
			}
			
			return rightResult;
		}
	}

	


}
