import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/* *****************************************************************************
 *  Name:  Lucas Henrique Braga Martins
 *  Date: 12/05/2020
 *  Description: A class to measure the limior of percolation in a lot of experiments 
 **************************************************************************** */
public class PercolationStats {
    private static final double CONSTANT_95 = 1.96;

    private final double[] thereshold;
    private final double mean;
    private final double stddev;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("n or trials <= 0");
        thereshold = new double[trials];
        int row, col;
        for (int i = 0; i < trials; i++) {
            Percolation experiment = new Percolation(n);
            while (!experiment.percolates()) {
                do {
                    row = StdRandom.uniform(1, n + 1);
                    col = StdRandom.uniform(1, n + 1);
                } while (experiment.isOpen(row, col));

                experiment.open(row, col);
            }
            thereshold[i] = (double) experiment.numberOfOpenSites() / (n * n);
            experiment = null;

        }
        mean = StdStats.mean(thereshold);
        stddev = StdStats.stddev(thereshold);
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - ((CONSTANT_95 * stddev()) / Math.sqrt(thereshold.length));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + ((CONSTANT_95 * stddev()) / Math.sqrt(thereshold.length));
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats tests = new PercolationStats(n, trials);

        System.out.println("mean                    = " + tests.mean());
        System.out.println("stddev                  = " + tests.stddev());
        System.out.println(
                "95% confidence interval = [" + tests.confidenceLo() + ", " + tests.confidenceHi()
                        + "]");

    }
}

