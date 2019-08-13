/* *****************************************************************************
 *  Name:    Alan Turing
 *  NetID:   aturing
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final int expnum;


    private final double[] fraction;

    public PercolationStats(int n, int t) {
        Percolation pr;
        if (n <= 0 || t <= 0) {
            throw new IllegalArgumentException("Given N <= 0 || T <= 0");
        }
        this.expnum = t;
        fraction = new double[t];
        for (int expNum = 0; expNum < t; expNum++) {
            pr = new Percolation(n);

            while (!pr.percolates()) {
                int i = StdRandom.uniform(1, n + 1);
                int j = StdRandom.uniform(1, n + 1);
                if (!pr.isOpen(i, j)) {
                    pr.open(i, j);

                }
            }
            double fractions = (double) pr.numberOfOpenSites() / (n * n);
            fraction[expNum] = fractions;
        }

    }

    public double mean() {
        return StdStats.mean(fraction);
    }

    public double stddev() {
        return StdStats.stddev(fraction);
    }

    public double confidenceLo() {
        return (mean() - (1.96 * stddev() / Math.sqrt(expnum)));

    }

    public double confidenceHi() {
        return (mean() + (1.96 * stddev() / Math.sqrt(expnum)));
    }

    public static void main(String[] args) {
        int numero = Integer.parseInt(args[0]);
        int trialo = Integer.parseInt(args[1]);
        PercolationStats objecto = new PercolationStats(numero, trialo);
        System.out.println("mean                   " + "=" + objecto.mean());
        System.out.println("stddev                 " + "=" + objecto.stddev());
        String confidence = objecto.confidenceLo() + ", " + objecto.confidenceHi();
        System.out.println("95% confidence interval" + "=" + "[" + confidence + "]");

    }
}
