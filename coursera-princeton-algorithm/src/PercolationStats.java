import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final int times;
    private final double[] record;
    private static final double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (trials <= 0) {
            throw(new IllegalArgumentException());
        }
        this.times = trials;
        record = new double[trials];
        while (trials > 0) {
            Percolation pc = new Percolation(n);
            int row;
            int col;
            while (!pc.percolates()) {
                row = StdRandom.uniform(1, n + 1);
                col = StdRandom.uniform(1, n + 1);
                if (!pc.isOpen(row, col)) {
                    pc.open(row, col);
                }
            }
            trials--;
            record[trials] = pc.numberOfOpenSites() * 1.0 / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(record);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(record);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        double m = mean();
        double s = stddev();
        return (m - CONFIDENCE_95 * s / Math.sqrt(times));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        double m = mean();
        double s = stddev();
        return (m + CONFIDENCE_95 * s / Math.sqrt(times));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats pcls = new PercolationStats(n, trials);
        System.out.printf("mean                    = %f\n", pcls.mean());
        System.out.printf("stddev                  = %f\n", pcls.stddev());
        System.out.printf("95%% confidence interval = [%f, %f]\n", pcls.confidenceLo(), pcls.confidenceHi());
    }

}
