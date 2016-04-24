import java.util.concurrent.RecursiveAction;


public class VecAdd extends RecursiveAction {
 public int lo; 
 public int hi;
int[] res; int[] arr1; int[] arr2;
public int SEQUENTIAL_CUTOFF = 5;
	VecAdd(int l,int h ,int[] r,int[] a1,int[] a2){
		lo = l;
		hi = h;
		res = r;
		arr1 = a1;
		arr2 = a2;
	}
	protected void compute(){
	if ((hi - lo) < SEQUENTIAL_CUTOFF) {
		for(int i =lo; i < hi; i++)
			res[i] = arr1[i] + arr2[i];
	} else {
			int mid= (hi+lo)/2;
			VecAdd left = new VecAdd(lo,mid,res,arr1,arr2);
			VecAdd right = new VecAdd(mid,hi,res,arr1,arr2); 
			left.fork();
			right.compute();
			left.join();
		}
	}
}
