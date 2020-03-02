import java.util.*;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {

    Comparator<Flight> flightComparatorStartTime = (i, j) -> i.startTime - j.startTime;
    Comparator<Flight> flightComparatorEndTime = (i, j) -> i.endTime - j.endTime;

    private PriorityQueue<Flight> pQ;
    private ArrayList<Flight> flights;
    private int maxPeople = 0;

    public FlightSolver(ArrayList<Flight> flights) {
        Collections.sort(flights, flightComparatorStartTime);
        this.flights = flights;
        pQ = new PriorityQueue<Flight>(flightComparatorEndTime);
    }

    public int solve() {
        int localMax = 0;

        for (Flight f:flights) {
            while (pQ.peek() != null) {
                Flight cmpF = pQ.peek();
                if (cmpF.endTime >= f.startTime) {
                    break;
                } else {
                    localMax = localMax - cmpF.passengers;
                    pQ.poll();
                }
            }
            localMax += f.passengers;
            updateMax(localMax);
            pQ.add(f);
        }
        return maxPeople;
    }

    private void updateMax(int i) {
        if (i > maxPeople) {
            maxPeople = i;
        }
    }
}
