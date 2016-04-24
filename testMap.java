import java.util.concurrent.ForkJoinPool;


public class testMap {
	public static void main(String[] args){
		int array1Length = 500;
		int array2Length = 500;
		int[] array1 = new int[array1Length];
		int[] array2 = new int[array2Length];
		int[] ans = new int[array2Length];
		for(int j = 0; j < array1Length; j++){
			array1[j] = j;
			array2[j] = j;
		}
		ForkJoinPool fjpool = new ForkJoinPool();
		fjpool.invoke(new VecAdd(0,array1Length,ans,array1,array2));
		for(int i = 0; i <array1Length; i++){
			System.out.println(ans[i]);
		}
	}
}
