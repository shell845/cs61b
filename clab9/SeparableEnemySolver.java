import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SeparableEnemySolver {

    Graph g;

    /**
     * Creates a SeparableEnemySolver for a file with name filename. Enemy
     * relationships are biderectional (if A is an enemy of B, B is an enemy of A).
     */
    SeparableEnemySolver(String filename) throws java.io.FileNotFoundException {
        this.g = graphFromFile(filename);
    }

    /** Alterntive constructor that requires a Graph object. */
    SeparableEnemySolver(Graph g) {
        this.g = g;
    }

    /**
     * Returns true if input is separable, false otherwise.
     */
    public boolean isSeparable() {
        Set<String> set = g.labels();

        Set<String> a = new HashSet<>();
        Set<String> b = new HashSet<>();

        for (String s: set) {
            Set<String> neighbor = g.neighbors(s);
            if (containNeighbor(neighbor, a)) {
                a.add(s);
            } else if (containNeighbor(neighbor, b)) {
                b.add(s);
            } else {
                return false;
            }
        }
        return true;
    }

    // Return false if contain neighbor
    public boolean containNeighbor(Set<String> neighbor, Set<String> group) {
        for (String n:neighbor) {
            if (group.contains(n)) {
                return false;
            }
        }
        return true;
    }

    // DFS
    public void dfs() {
        Set<String> set = g.labels();
        String[] graph = (String[]) set.toArray();
        HashSet<String> mark = new HashSet<>();
        dfsHelper(graph[0], mark);
    }

    private void dfsHelper(String s, HashSet mark) {
        mark.add(s);
        for (String n:g.neighbors(s)) {
            if (!mark.contains(n)) {
                // set edgeTo(n) = s
                dfsHelper(n, mark);
            }
        }
        System.out.println(s);
    }

    // BFS
    public void bfs() {
        Set<String> set = g.labels();
        String[] graph = (String[]) set.toArray();
        Queue<String> fringe = new ArrayDeque<>();
        HashSet<String> mark = new HashSet<>();

        fringe.add(graph[0]);
        mark.add(graph[0]);
        while (!fringe.isEmpty()) {
            String v = fringe.poll();
            for (String n:g.neighbors(v)) {
                if (!mark.contains(n)) {
                    fringe.add(n);
                    mark.add(n);
                    // set edgeTo(n) = v, disTo(n) = v
                }
            }
        }
    }

    /* HELPERS FOR READING IN CSV FILES. */

    /**
     * Creates graph from filename. File should be comma-separated. The first line
     * contains comma-separated names of all people. Subsequent lines each have two
     * comma-separated names of enemy pairs.
     */
    private Graph graphFromFile(String filename) throws FileNotFoundException {
        List<List<String>> lines = readCSV(filename);
        Graph input = new Graph();
        for (int i = 0; i < lines.size(); i++) {
            if (i == 0) {
                for (String name : lines.get(i)) {
                    input.addNode(name);
                }
                continue;
            }
            assert(lines.get(i).size() == 2);
            input.connect(lines.get(i).get(0), lines.get(i).get(1));
        }
        return input;
    }

    /**
     * Reads an entire CSV and returns a List of Lists. Each inner
     * List represents a line of the CSV with each comma-seperated
     * value as an entry. Assumes CSV file does not contain commas
     * except as separators.
     * Returns null if invalid filename.
     *
     * @source https://www.baeldung.com/java-csv-file-array
     */
    private List<List<String>> readCSV(String filename) throws java.io.FileNotFoundException {
        List<List<String>> records = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
            records.add(getRecordFromLine(scanner.nextLine()));
        }
        return records;
    }

    /**
     * Reads one line of a CSV.
     *
     * @source https://www.baeldung.com/java-csv-file-array
     */
    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        Scanner rowScanner = new Scanner(line);
        rowScanner.useDelimiter(",");
        while (rowScanner.hasNext()) {
            values.add(rowScanner.next().trim());
        }
        return values;
    }

    /* END HELPERS  FOR READING IN CSV FILES. */

}
