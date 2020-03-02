package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid; //0 is block, 1 is open
    private int[] grid1D; //0 is block, 1 is open
    private int gridSize;
    private int numOfOpenSite;
    private WeightedQuickUnionUF wUF;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        gridSize = N;
        grid = new int[gridSize][gridSize];
        grid1D = new int[gridSize * gridSize];
        numOfOpenSite = 0;
        wUF = new WeightedQuickUnionUF(gridSize * gridSize);

        for (int i = 0; i < gridSize - 1; i++) {
            int pos = xyto1D(0, i);
            grid1D[pos] = 1;
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        int pos = xyto1D(row, col);
        grid1D[pos] = 1;
        numOfOpenSite += 1;

        if (row < gridSize - 1 && isOpen(row + 1, col)) {
            wUF.union(pos, xyto1D(row + 1, col));
        }
        if (row > 0 && isOpen(row - 1, col)) {
            wUF.union(pos, xyto1D(row - 1, col));
        }
        if (col < gridSize - 1 && isOpen(row, col + 1)) {
            wUF.union(pos, xyto1D(row, col + 1));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            wUF.union(pos, xyto1D(row, col - 1));
        }

        if (isFull(row, col)) {
            System.out.println(row + " " + col + " is Full");
        }
    }

    // return 1D array position of a 2D array index
    private int xyto1D(int x, int y) {
        return (x * gridSize + y);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        int pos = xyto1D(row, col);
        return (grid1D[pos] == 1);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        int pos = xyto1D(row, col);
        for (int k = 0; k < gridSize; k++) {
            int top = xyto1D(0, k);
            if (wUF.connected(pos, top)) {
                return true;
            }
        }
        return false;
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOfOpenSite;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                int posTop = xyto1D(0, i);
                int posButtom = xyto1D(gridSize - 1, j);
                if (wUF.connected(posTop, posButtom)) {
                    return true;
                }
            }
        }
        return false;
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {

    }
}
