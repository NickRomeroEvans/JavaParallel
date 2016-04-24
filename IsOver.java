//Nickolas Evans
// Section AB
import java.util.concurrent.RecursiveTask;

public class IsOver extends RecursiveTask<Boolean>{
	private int[] array;
	private int checker;
	private int lo;
	private int hi;
	
	@Override
	protected Boolean compute() {
		if(hi - lo == 1){
			return checker < array[lo]; //Returns whether element is over
		}
		IsOver left = new IsOver(array, lo,(hi+lo)/2,checker);
		IsOver right = new IsOver(array,(hi+lo)/2,hi,checker);
		left.fork();
		boolean rightResult = right.compute();
		boolean leftResult = left.join();
		return rightResult||leftResult; //Takes or of both sides returns true if either side has an element over the supplied int checker
	}

	public IsOver(int[] array, int lo, int hi, int checker){
		this.array = array;
		this.lo = lo;
		this.hi = hi;
		this.checker = checker;
	}
}
