/**
 * @author Nickolas Evans
 * Section AB
 * */
import java.util.concurrent.ForkJoinPool;


public class TestProblem3 {
	public static ForkJoinPool fjPool = new ForkJoinPool();

	public static int getLeftMostIndex(char[] charArray, char[] array) {
		// TODO Auto-generated method stub
		LeftMostIndex lmi = new LeftMostIndex(array, charArray, 0, array.length);
		LeftMostResult s = fjPool.invoke(lmi);
		if(s.found){
			return s.index;
		}else{
			return -1;
		}
	}
}
