
import java.util.HashMap;


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int n;
    WeightedQuickUnionUF gridConnect;
    HashMap<Integer, Integer> openedSites = new HashMap<Integer, Integer>();
    HashMap<Integer, Integer> bottomCanonical = new HashMap<Integer, Integer>();
    public Percolation(int N) {
        // create N-by-N grid, with all sites blocked
        // grid = new boolean[N*N];
        // for(int i = 0; i < grid.length; i++) grid[i]=false;
        gridConnect = new WeightedQuickUnionUF(N*N);
        n=N;
    }

    public void open(int row, int column) {
        // open site (row , column) if it is not open already
        int pos = n*(row) + column;
        if(!openedSites.containsKey(pos)){
            //connect();
            int left = n*row + column - 1;
            int right = n*row + column + 1;
            int top = n*(row-1) + column;
            int bottom = n*(row+1) + column;
            if(row == 0 && column != 0 && column != n-1){
                if(openedSites.containsKey(left)) gridConnect.union(left, pos);
                if(openedSites.containsKey(right)) gridConnect.union(right, pos);
                if(openedSites.containsKey(bottom)) gridConnect.union(bottom, pos);
            }
            else if(row == n-1 && column != 0 && column != n-1){
                if(openedSites.containsKey(left)) gridConnect.union(left, pos);
                if(openedSites.containsKey(right)) gridConnect.union(right, pos);
                if(openedSites.containsKey(top)) gridConnect.union(top, pos);
            }
            else if(column == 0 && row != 0 && row != n-1){
                if(openedSites.containsKey(right)) gridConnect.union(right, pos);
                if(openedSites.containsKey(top)) gridConnect.union(top, pos);
                if(openedSites.containsKey(bottom)) gridConnect.union(bottom, pos);
            }
            else if(column == n-1 && row != 0 && row != n-1){
                if(openedSites.containsKey(left)) gridConnect.union(left, pos);
                if(openedSites.containsKey(top)) gridConnect.union(top, pos);
                if(openedSites.containsKey(bottom)) gridConnect.union(bottom ,pos);
            }
            else if(pos == 0){
                if(openedSites.containsKey(right)) gridConnect.union(right, pos);
                if(openedSites.containsKey(bottom)) gridConnect.union(bottom, pos);
            }
            else if(pos == n-1){
                if(openedSites.containsKey(left)) gridConnect.union(left, pos);
                if(openedSites.containsKey(bottom)) gridConnect.union(bottom, pos);
            }
            else if(pos == (n-1)*n){
                if(openedSites.containsKey(right)) gridConnect.union(right, pos);
                if(openedSites.containsKey(top)) gridConnect.union(top, pos);
            }
            else if(pos == (n*n)-1){
                if(openedSites.containsKey(left)) gridConnect.union(left, pos);
                if(openedSites.containsKey(top)) gridConnect.union(top, pos); 
            }
            else{
                if(openedSites.containsKey(left)) gridConnect.union(left, pos);
                if(openedSites.containsKey(right)) gridConnect.union(right, pos);
                if(openedSites.containsKey(top)) gridConnect.union(top, pos);
                if(openedSites.containsKey(bottom)) gridConnect.union(bottom, pos);
            }
            openedSites.put(pos, gridConnect.find(pos));
        }        
    }

    public boolean isOpen(int i, int j) {
        // is site (row i, column j) open?
        int pos = n*i+j;
        if(openedSites.containsKey(pos)) return true;
        else return false;
    }

    public boolean isFull(int i, int j) {
        // if(justOpened) familyMapping();
        // is site (row i, column j) full?
        int pos = n*i+j;
        for(int m = 0; m < n; m++){
            if(openedSites.containsKey(m) && openedSites.get(m) == gridConnect.find(pos)) return true;
        }
        return false;
    }   

    public boolean percolates() {
        // if(justOpened) familyMapping();
        // does the system percolate?
        bottomCanonical.clear();
        for(int i = n*(n-1); i < n*n; i++){
            bottomCanonical.put(i, gridConnect.find(i));
        }
        for(int i = 0; i < n; i++){
            if(bottomCanonical.containsValue(gridConnect.find(i))) return true;
        }
        return false;
    }
    
    // public static void main(String[] args) {
    //     // test
    //     Percolation s = new Percolation(3);
    //     s.open(1,1);
    //     System.out.println(s.isFull(1, 1));
    //     System.out.println(s.percolates());
    //     s.open(0,1);
    //     s.open(2,0);
    //     System.out.println(s.isFull(1, 1));
    //     System.out.println(s.isFull(0, 1));
    //     System.out.println(s.isFull(2, 0));
    //     System.out.println(s.percolates());
    //     s.open(2,1);
    //     System.out.println(s.isFull(1, 1));
    //     System.out.println(s.isFull(0, 1));
    //     System.out.println(s.isFull(2, 0));
    //     System.out.println(s.isFull(2, 1));
    //     System.out.println(s.percolates());
    // }
    public static void main(String[] args) {
        int n = 80;
        Percolation s = new Percolation(n);
        // for (int i = 0; i < n; i++){
        //     for (int j = 0; j < n; j++){
        //         s.open(i, j);
        //     }
        // }
        // System.out.println(s.isFull(2, 3));
        // System.out.println(s.isOpen(2, 3));
        // System.out.println(s.percolates());
        for(int i = 0; i < n-1; i++){
            s.open(i, i);
            if (i+1 < n) s.open(i, i+1);
            s.open(79, 77);
            System.out.println(s.isFull(i, i));
        }
        System.out.println(s.percolates());
    }
}