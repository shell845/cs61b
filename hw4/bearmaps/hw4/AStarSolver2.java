/**
 * @author YC 02/22/2020
 * Improve AStarSolver according to sample answer
 */

package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.*;


public class AStarSolver2<Vertex> implements ShortestPathsSolver<Vertex>{

    private SolverOutcome outcome;
    private LinkedList<Vertex> solution;
    private int numDequeOpt;
    private double timeSpent;
    private double solutionWeight;

    public AStarSolver2(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        this.numDequeOpt = 0;
        this.solutionWeight = 0;
        Stopwatch sw = new Stopwatch();
        HashMap<Vertex, Double> disTo = new HashMap<>();
        HashMap<Vertex, Vertex> edgeTo = new HashMap<>();
        this.solution = new LinkedList<>();

        ArrayHeapMinPQ<Vertex> fringe = new ArrayHeapMinPQ<>();

        disTo.put(start, 0.0);
        fringe.add(start, disTo.get(start) + input.estimatedDistanceToGoal(start, end));

        while (fringe.size() > 0 && !(fringe.getSmallest()).equals(end) && sw.elapsedTime() < timeout) {
            // dequeue the vertex with smallest distance from fringe
            Vertex v = fringe.removeSmallest();
            numDequeOpt += 1;
            for (WeightedEdge<Vertex> n:input.neighbors(v)) {
                Vertex q = n.to();
                double w = n.weight();
                if (!disTo.containsKey(q) || disTo.get(v) + w < disTo.get(q)) {
                    disTo.put(q, disTo.get(v) + w); // update neighbors' distance
                    edgeTo.put(q, v);
                    if (fringe.contains(q)) { // update neighbor's weight if it exists in fringe
                        fringe.changePriority(q, disTo.get(q) + input.estimatedDistanceToGoal(q, end));
                    } else {  // add neighbors to fringe
                        fringe.add(q, disTo.get(q) + input.estimatedDistanceToGoal(q, end));
                    }
                    }
                }
            }

        if (fringe.size() == 0) {
            outcome = SolverOutcome.UNSOLVABLE;
        } else if (fringe.getSmallest().equals(end)) {
            outcome = SolverOutcome.SOLVED;
            Vertex p = end;
            while (p != null) {
                solution.addFirst(p);
                p = edgeTo.get(p);
            }
            solutionWeight = disTo.get(end);
        } else {
            outcome = SolverOutcome.TIMEOUT;
        }

        timeSpent = sw.elapsedTime();
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
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
