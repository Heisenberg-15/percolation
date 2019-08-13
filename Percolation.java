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

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int[][] arr;
    private boolean[][] state;
    private final WeightedQuickUnionUF obj2;
    private final WeightedQuickUnionUF obj3;
    private final int size;
    private int num1;

    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException();
        this.num1 = 0;
        this.size = n;
        this.obj2 = new WeightedQuickUnionUF((n * n) + 2);
        this.obj3 = new WeightedQuickUnionUF((n * n) + 1);
        int num = 0;
        this.arr = new int[n][n];
        this.state = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.arr[i][j] = num;
                this.state[i][j] = false;
                num += 1;


            }
        }


    }

    public void open(int row, int col) {
        if (row < 0 || row >= this.size) throw new IllegalArgumentException();
        if (col < 0 || col >= this.size) throw new IllegalArgumentException();

        if (!this.state[row][col]) {
            this.state[row][col] = true;
            this.num1 += 1;
            if (row == 0) {
                this.obj2.union(this.arr[row][col], this.size * this.size);
                this.obj3.union(this.arr[row][col], this.size * this.size);

            }
            if (row == this.size - 1) {
                //System.out.println("yay");
                this.obj2.union(this.arr[row][col], this.size * this.size + 1);
            }
            if (row + 1 < this.size && this.isOpen(row + 1, col)) {
                this.obj2.union(this.arr[row][col], this.arr[row + 1][col]);
                this.obj3.union(this.arr[row][col], this.arr[row + 1][col]);
            }
            if (col + 1 < this.size && this.isOpen(row, col + 1)) {
                this.obj2.union(this.arr[row][col], this.arr[row][col + 1]);
                this.obj3.union(this.arr[row][col], this.arr[row][col + 1]);
            }
            if (row - 1 < this.size && row - 1 >= 0 && this.isOpen(row - 1, col)) {
                this.obj2.union(this.arr[row][col], this.arr[row - 1][col]);
                this.obj3.union(this.arr[row][col], this.arr[row - 1][col]);
            }
            if (col - 1 >= 0 && col - 1 < this.size && this.isOpen(row, col - 1)) {
                this.obj2.union(this.arr[row][col], this.arr[row][col - 1]);
                this.obj3.union(this.arr[row][col], this.arr[row][col - 1]);
            }
        }

    }


    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= this.size) throw new IllegalArgumentException();
        if (col < 0 || col >= this.size) throw new IllegalArgumentException();
        return this.state[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= this.size) throw new IllegalArgumentException();
        if (col < 0 || col >= this.size) throw new IllegalArgumentException();
        if (this.isOpen(row, col)) {
            return this.obj3.connected(this.arr[row][col], this.size * this.size);
        }
        else {
            return false;
        }
    }


    public int numberOfOpenSites() {
        return this.num1;
    }

    public boolean percolates() {
        return this.obj2.connected(this.size * this.size + 1, this.size * this.size);

    }
//this is a test


}

