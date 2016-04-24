/** 
 * @author Nickolas Evans
 * */
public class LeftMostResult {
	boolean found;
	boolean incomplete;
	int index; // index will represent two values if found is true then index is the mindex found
				// if incomplete is true and found false then 
	
	LeftMostResult(boolean found, boolean incomplete, int index){
		this.found = found;
		this.incomplete = incomplete;
		this.index = index;
	}
}
