package hw2;

public class PercolationStats {
    private Percolation pl;
    int[] count;
    private int time, size;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must > 0.");
        }

        size = N;
        time = T;
        pl = pf.make(N);
        count = new int[T];

    }

    private void estimate() {
        for (int i = 0; i < time; i++) {
            int counter = 0;
            for (int j = 0; j < size * size; j++) {
                pl.open(getRandomInt(0, size - 1), getRandomInt(0, size - 1));
                counter += 1;
                if (pl.percolates()) {
                    count[i] = counter;
                    continue;
                }
            }
        }
    }


    public static int getRandomInt(int min, int max) {
        int x = (int) (Math.random() * ((max - min) + 1)) + min;
        return x;
    }

    // sample mean of percolation threshold
    public double mean() {
        return -1;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return -1;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return -1.;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return -1;
    }

    public static void main(String[] args) {
       //
    }
}
