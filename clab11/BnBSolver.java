import java.util.ArrayList;
import java.util.List;
import edu.princeton.cs.algs4.BST;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    private List<Bear> a;
    private List<Bed> b;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        this.a = bears;
        this.b = new ArrayList<Bed>();

        /* BST tree approach O(NlogN) */
        BST<Integer, Bed> bt = new BST<>();
        for (Bed bed:beds) {
            bt.put(bed.getSize(), bed);
        }
        for (Bear bear:bears) {
            b.add(bt.get(bear.getSize()));
        }

        /* Naive approach O(N2)
        for (Bear bear:bears) {
            for (Bed bed:beds) {
                if (bear.compareTo(bed) == 0) {
                    b.add(bed);
                }
            }
        } */
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        // TODO: Fix me.
        return a;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        // TODO: Fix me.
        return b;
    }
}
