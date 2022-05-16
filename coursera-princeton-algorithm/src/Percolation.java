import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private final WeightedQuickUnionUF wuf;
    private final int n;
    private int openNum;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        grid = new boolean[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                grid[i][j] = false;
            }
        }
        wuf = new WeightedQuickUnionUF((n + 1) * (n + 1));
        this.n = n;
        openNum = 0;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw(new IllegalArgumentException());
        } else {
            if (grid[row][col]) return;
            else {
                grid[row][col] = true;
                openNum++;
                if (row - 1 >= 1 && isOpen(row - 1, col)) wuf.union((row - 2) * n + col, (row - 1) * n + col);
                if (row + 1 <= n && isOpen(row + 1, col)) wuf.union(row * n + col, (row - 1) * n + col);
                if (col - 1 >= 1 && isOpen(row, col - 1)) wuf.union((row - 1) * n + col - 1, (row - 1) * n + col);
                if (col + 1 <= n && isOpen(row, col + 1)) wuf.union((row - 1) * n + col + 1, (row - 1) * n + col);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || row > n || col < 1 || col > n) {
            throw(new IllegalArgumentException());
        }
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        for (int i = 1; i <= n; i++) {
            if (isOpen(row, col) && isOpen(1, i) && wuf.find(i) == wuf.find((row - 1)  * n + col)) {
                return true;
            }
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openNum;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 1; i <= n; i++) {
            if (isOpen(n, i) && isFull(n, i)) return true;
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation pc = new Percolation(1);
        System.out.println(pc.openNum);
    }
}
