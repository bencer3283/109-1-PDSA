
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int n;
    WeightedQuickUnionUF gridConnect;
    //HashMap<Integer, Integer> openedSites = new HashMap<Integer, Integer>();
    boolean[] grid;
    int[] id;
    int opentimes = 0;
    boolean justopened = false;

    public Percolation(int N) {
        // create N-by-N grid, with all sites blocked
        // grid = new boolean[N*N];
        // for(int i = 0; i < grid.length; i++) grid[i]=false;
        // gridConnect = new WeightedQuickUnionUF(N*N);
        n=N;
        grid = new boolean[N*N];
        id = new int[N*N];
        for(int i = 0; i < grid.length; i++){
            grid[i] = false;
            id[i] = -1;
        }
    }

    private void connect(){
        gridConnect = new WeightedQuickUnionUF(opentimes);
        int order = 0;
        for(int i = 0; i < grid.length; i++){
            if(grid[i]){
                id[i] = order;
                int pos = i;
                int row = i/n;
                int column = i%n;
                int left = n*row + column - 1;
                int right = n*row + column + 1;
                int top = n*(row-1) + column;
                int bottom = n*(row+1) + column;
                if(row == 0 && column != 0 && column != n-1){
                    if(grid[left]) gridConnect.union(id[left], order);
                    if(grid[right]) gridConnect.union(id[right], order);
                    if(grid[bottom]) gridConnect.union(id[bottom], order);
                }
                else if(row == n-1 && column != 0 && column != n-1){
                    if(grid[left]) gridConnect.union(id[left], order);
                    if(grid[right]) gridConnect.union(id[right], order);
                    if(grid[top]) gridConnect.union(id[top], order);
                }
                else if(column == 0 && row != 0 && row != n-1){
                    if(grid[right]) gridConnect.union(id[right], order);
                    if(grid[top]) gridConnect.union(id[top], order);
                    if(grid[bottom]) gridConnect.union(id[bottom], order);
                }
                else if(column == n-1 && row != 0 && row != n-1){
                    if(grid[left]) gridConnect.union(id[left], order);
                    if(grid[top]) gridConnect.union(id[top], order);
                    if(grid[bottom]) gridConnect.union(id[bottom] ,order);
                }
                else if(pos == 0){
                    if(grid[right]) gridConnect.union(id[right], order);
                    if(grid[bottom]) gridConnect.union(id[bottom], order);
                }
                else if(pos == n-1){
                    if(grid[left]) gridConnect.union(id[left], order);
                    if(grid[bottom]) gridConnect.union(id[bottom], order);
                }
                else if(pos == (n-1)*n){
                    if(grid[right]) gridConnect.union(id[right], order);
                    if(grid[top]) gridConnect.union(top, pos);
                }
                else if(pos == (n*n)-1){
                    if(grid[left]) gridConnect.union(id[left], order);
                    if(grid[top]) gridConnect.union(id[top], order); 
                }
                else{
                    if(grid[left]) gridConnect.union(id[left], order);
                    if(grid[right]) gridConnect.union(id[right], order);
                    if(grid[top]) gridConnect.union(id[top], order);
                    if(grid[bottom]) gridConnect.union(id[bottom], order);
                }
                order++;
            }
        }
        
    }

    public void open(int row, int column) {
        opentimes++; 
        // open site (row , column) if it is not open already
        int pos = n*(row) + column;
        if(n == 1) {
            grid[0] = true;
        }
        else{
            grid[pos] = true;
            connect();
            
        }
    }

    public boolean isOpen(int i, int j) {
        // is site (row i, column j) open?
        int pos = n*i+j;
        return grid[pos];
    }

    public boolean isFull(int i, int j) {
        // if(justOpened) familyMapping();
        // is site (row i, column j) full?
        int pos = n*i+j;
        // for(int m = 0; m < n; m++){
        //     if(openedSites.containsKey(m) && openedSites.get(m) == gridConnect.find(pos)) return true;
        // }
        if(n == 1) {
            return isOpen(i, j);
        }
        else if(grid[pos]) {
            for(int m = 0; m < n; m++){
                if(grid[m]) if(gridConnect.find(id[m]) == gridConnect.find(id[pos])) return true;
            }
        }
        return false;
    }   

    public boolean percolates() {
        // if(justOpened) familyMapping();
        // does the system percolate?
        for(int i = 0; i < n; i++){
            if(grid[n*(n-1)+i]) if(isFull(n-1, i)) return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        // test
        Percolation s = new Percolation(3);
        s.open(1,1);
        System.out.println(s.isFull(1, 1));
        System.out.println(s.percolates());
        s.open(0,1);
        s.open(2,0);
        System.out.println(s.isFull(1, 1));
        System.out.println(s.isFull(0, 1));
        System.out.println(s.isFull(2, 0));
        System.out.println(s.percolates());
        s.open(2,1);
        System.out.println(s.isFull(1, 1));
        System.out.println(s.isFull(0, 1));
        System.out.println(s.isFull(2, 0));
        System.out.println(s.isFull(2, 1));
        System.out.println(s.percolates());
    }
    // public static void main(String[] args) {
    //     int n = 1;
    //     Percolation s = new Percolation(n);
    //     s.open(0, 0);
    //     System.out.println(s.isFull(0, 0)); 
    //     System.out.println(s.percolates());
        // for (int i = 0; i < n; i++){
        //     for (int j = 0; j < n; j++){
        //         s.open(i, j);
        //     }
        // }
        // System.out.println(s.isFull(2, 3));
        // System.out.println(s.isOpen(2, 3));
        // System.out.println(s.percolates());
        // for(int i = 0; i < n-1; i++){
        //     s.open(i, i);
        //     if (i+1 < n) s.open(i, i+1);
        //     s.open(79, 77);
        //     System.out.println(s.isFull(i, i));
            
        // }
        // System.out.println(s.isOpen(79, 79));
        // System.out.println(s.percolates());
    // }
}