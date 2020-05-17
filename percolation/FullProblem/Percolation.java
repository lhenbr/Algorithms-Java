/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private static final int TOP = 0;
    private final WeightedQuickUnionUF grid;
    private final int bottom;
    private boolean[] open;
    private int openSites;
    private final int side;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n < 0");
        grid = new WeightedQuickUnionUF(n * n + 2);
        open = new boolean[n * n];
        side = n;
        openSites = 0;
        bottom = n * n + 1;
    }

    private int calElement(int row, int col) {
        int element;
        element = ((row - 1) * side + (col));
        return element;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > side || col <= 0 || col > side)
            throw new IllegalArgumentException("row or col outside range");
        int element = calElement(row, col);
        if (open[element - 1]) return;
        open[element - 1] = true;

        if (row == 1) grid.union(element, TOP);

        if (row == side) grid.union(element, bottom);

        if (row + 1 <= side && open[calElement(row + 1, col) - 1]) {
            grid.union(element, calElement(row + 1, col));
        }
        if (row - 1 >= 1 && open[calElement(row - 1, col) - 1]) {
            grid.union(element, calElement(row - 1, col));
        }
        if (col + 1 <= side && open[calElement(row, col + 1) - 1]) {
            grid.union(element, calElement(row, col + 1));
        }
        if (col - 1 >= 1 && open[calElement(row, col - 1) - 1]) {
            grid.union(element, calElement(row, col - 1));
        }
        openSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > side || col <= 0 || col > side)
            throw new IllegalArgumentException("row or col outside range");
        int element = calElement(row, col);
        return open[element - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > side || col <= 0 || col > side)
            throw new IllegalArgumentException("row or col outside range");
        return (grid.find(calElement(row, col)) == grid.find(TOP));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.find(TOP) == grid.find(bottom);
    }

    // test client (optional)
    // public static void main(String[] args)

}
