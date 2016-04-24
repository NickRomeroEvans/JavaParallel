/**
 * @author David Swanson
 * 
 * Something like this may be useful for computing the longest sequence.
 * Feel free to modify this or not use it at all.
 * For example, numLeftEdge could represent the length of the sequence 
 * at the beginning of the range processed by a subproblem. 
 * 
 */
public class Result {
    int		numLeftEdge;
    int		numRightEdge;
    int		numLongest;
    boolean	entireRange;

    Result(int l, int r, int m, boolean a) {
	numLeftEdge = l;
	numRightEdge = r;
	numLongest = m;
	entireRange = a;
    }
}
