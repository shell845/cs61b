/**
 * @author YC 02/21/2020
 */

package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;


import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private ArrayList<Vertex> solution;
    private int numDequeOpt;
    private double timeSpent;
    private double timeout;
    private Stopwatch sw;
    private HashMap<Vertex, Double> disTo;
    private HashMap<Vertex, Vertex> edgeTo;

    private Vertex start, end;
    private AStarGraph<Vertex> input;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        this.input = input;
        this.start = start;
        this.end = end;
        this.numDequeOpt = 0;
        this.sw = new Stopwatch();
        this.timeout = timeout;
        this.disTo = new HashMap<>();
        this.edgeTo = new HashMap<>();

        solver();
    }

    private void solver() {

        if (start.equals(end)) {
            timeSpent = sw.elapsedTime();
            outcome = SolverOutcome.SOLVED;
            return;
        }

        // starting point
        List<WeightedEdge<Vertex>> neighbors = input.neighbors(start);
//        DoubleMapPQ<Vertex> fringe = new DoubleMapPQ<>();
        ArrayHeapMinPQ<Vertex> fringe = new ArrayHeapMinPQ<>();
        for (WeightedEdge<Vertex> v:neighbors) {
            if (isComplete(v.to())) {
                return;
            }
            disTo.put(v.to(), v.weight());
            edgeTo.put(v.to(), v.from());
            fringe.add(v.to(), disTo.get(v.to()) + input.estimatedDistanceToGoal(v.to(), end));
        }

        aStar(fringe);
    }

    private void aStar(ArrayHeapMinPQ<Vertex> fringe) {

        if (fringe.size() > 0) {

            // end the process if timeout
            if (sw.elapsedTime() > timeout) {
                outcome = SolverOutcome.TIMEOUT;
                return;
            }

            // dequeue the vertex with smallest distance from fringe
            Vertex v = fringe.removeSmallest();
            numDequeOpt += 1;

            if (isComplete(v)) {
                return; // end if v is the end point
            } else {
                List<WeightedEdge<Vertex>> neighbors = input.neighbors(v);
                for (WeightedEdge<Vertex> n:neighbors) {
                    // relax edge -- need further modify !!!
                    // no h(v, goal) now
                    double newDis = disTo.get(n.from()) + n.weight();

                    if (disTo.containsKey(n.to())) {
                        double oldDis = disTo.get(n.to()); // checked this vertex before
                        if (newDis < oldDis) { // find a better distance
                            disTo.put(n.to(), newDis); // update neighbors' distance
                            edgeTo.put(n.to(), n.from());
                            if (fringe.contains(n.to())) { // update neighbor's weight if it exists in fringe
                                fringe.changePriority(n.to(), newDis + input.estimatedDistanceToGoal(n.to(), end));
                            } else {  // add neighbors to fringe
                                fringe.add(n.to(), newDis + input.estimatedDistanceToGoal(n.to(), end));
                            }
                        }
                    } else {
                        disTo.put(n.to(), newDis);
                        edgeTo.put(n.to(), n.from());
                        fringe.add(n.to(), newDis);
                    }
                }
                aStar(fringe);
            }
        } else {
            outcome = SolverOutcome.UNSOLVABLE;
            return;
        }
    }

    // return true if find the end point
    private boolean isComplete(Vertex v) {
        if (v.equals(end)) {
            timeSpent = sw.elapsedTime();
            outcome = SolverOutcome.SOLVED;
            return true;
        }
        return false;
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        this.solution = new ArrayList<>();
        if (outcome == SolverOutcome.SOLVED) {
            Vertex pre = end;
            solution.add(pre);
            while (pre != start) {
                pre = edgeTo.get(pre);
                solution.add(pre);
            }
        }
        Collections.reverse(solution);
        return solution;
    }

    @Override
    public double solutionWeight() {
        double solutionWeight;
        if (outcome == SolverOutcome.SOLVED) {
            solutionWeight = disTo.get(end);
        } else {
            solutionWeight = 0;
        }
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numDequeOpt;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
