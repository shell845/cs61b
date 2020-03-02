import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class BubbleGrid {
    private int[][] grid;
    private WeightedQuickUnionUF wQU;
    private int gridCol;
    private int gridRow;
    private int gridSize;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        gridRow = grid.length;
        gridCol = grid[0].length;
        gridSize = gridRow * gridCol;
        this.grid = grid;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int[] result = new int[darts.length];
        for (int i = 0; i < darts.length; i++) {
            result[i] = pop(darts[i][0], darts[i][1]);
        }
        return result;
    }

    // Pop bubbles
    private int pop(int row, int col) {
        int count = 0;

        // Pop the bubble which the dart hits
        grid[row][col] = 0;

        // Initial a new WeightedQuickUnionUF array with dummy node
        wQU = new WeightedQuickUnionUF(gridSize + 1);

        // Link top bubbles to dummy node
        for (int i = 0; i < gridCol; i++) {
            if (grid[0][i] == 1) {
                wQU.union(gridSize, xyto1D(0, i));
            }
        }

        // Link bubbles
        for (int i = 1; i < gridRow; i++) {
            for (int j = 0; j < gridCol; j++) {
                if (grid[i][j] == 1) {
                    if (grid[i - 1][j] == 1 && wQU.find(xyto1D(i-1, j)) == gridSize) {
                        wQU.union(xyto1D(i-1, j), xyto1D(i, j));
                    } else if (j > 0 && grid[i][j - 1] == 1 && wQU.find(xyto1D(i, j-1)) == gridSize) {
                        wQU.union(xyto1D(i, j-1), xyto1D(i, j));
                    } else if (j < gridCol - 1 && grid[i][j + 1] == 1 && wQU.find(xyto1D(i, j+1)) == gridSize) {
                        wQU.union(xyto1D(i, j+1), xyto1D(i, j));
                    }
                }
            }
        }

        // Drop and count bubbles which cannot be linked
        for (int i = 0; i < gridRow; i++) {
            for (int j = 0; j < gridCol; j++) {
                if (grid[i][j] == 1 && wQU.find(xyto1D(i, j)) != gridSize) {
                    grid[i][j] = 0;
                    count += 1;
                }
            }
        }

        return count;
    }

    // Return the position in WeightedQuickUnionUF array
    private int xyto1D (int x, int y) {
        return (x * gridCol + y);
    }

    public static void main(String[] args) {
        BubbleGrid bG = new BubbleGrid(new int[][]{{1, 1, 0}, {1, 0, 0}, {1, 1, 0}, {1, 1, 1}});
        for(int i : bG.popBubbles(new int[][]{{2, 2}, {2, 0}})) {
            System.out.print(i + " ");
        }
    }
}
