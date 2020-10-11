import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    boolean[] grid;
    int n;
    boolean justOpened = false;
    ArrayList<Integer> opened = new ArrayList<Integer>();
    WeightedQuickUnionUF gridConnect;
    HashMap<Integer, ArrayList<Integer>> family = new HashMap<Integer, ArrayList<Integer>>();

    public Percolation(int N) {
        // create N-by-N grid, with all sites blocked
        grid = new boolean[N*N];
        for(int i = 0; i < grid.length; i++) grid[i]=false;
        gridConnect = new WeightedQuickUnionUF(N*N);
        n=N;
    }

    public void connect(){
        // for(int i = 0; i < opened.size(); i++){
        //     int pos = opened.get(i);
        //     // connect open sites
        //     int row = pos/n;
        //     int column = pos%n;
            
        // }
        family.clear();
        for(int i = 0; i < opened.size(); i++){
            // maintain family map
            int pos = opened.get(i);
            int canonical = gridConnect.find(pos);
            if(family.containsKey(canonical)) family.get(canonical).add(pos);
            else{
                ArrayList<Integer> union = new ArrayList<Integer>();
                family.put(canonical, union);
                family.get(canonical).add(pos);
            }
        }
        justOpened = false;
        
    }

    public void open(int row, int column) {
        // open site (row , column) if it is not open already
        int pos = n*(row) + column;
        grid[pos] = true;
        opened.add(pos);
        //connect();
        int left = n*row + column - 1;
            int right = n*row + column + 1;
            int top = n*(row-1) + column;
            int bottom = n*(row+1) + column;
            if(row == 0 && column != 0 && column != n-1){
                if(grid[left]) gridConnect.union(left, pos);
                if(grid[right]) gridConnect.union(right, pos);
                if(grid[bottom]) gridConnect.union(bottom, pos);
            }
            else if(row == n-1 && column != 0 && column != n-1){
                if(grid[left]) gridConnect.union(left, pos);
                if(grid[right]) gridConnect.union(right, pos);
                if(grid[top]) gridConnect.union(top, pos);
            }
            else if(column == 0 && row != 0 && row != n-1){
                if(grid[right]) gridConnect.union(right, pos);
                if(grid[top]) gridConnect.union(top, pos);
                if(grid[bottom]) gridConnect.union(bottom, pos);
            }
            else if(column == n-1 && row != 0 && row != n-1){
                if(grid[left]) gridConnect.union(left, pos);
                if(grid[top]) gridConnect.union(top, pos);
                if(grid[bottom]) gridConnect.union(bottom ,pos);
            }
            else if(pos == 0){
                if(grid[right]) gridConnect.union(right, pos);
                if(grid[bottom]) gridConnect.union(bottom, pos);
            }
            else if(pos == n-1){
                if(grid[left]) gridConnect.union(left, pos);
                if(grid[bottom]) gridConnect.union(bottom, pos);
            }
            else if(pos == (n-1)*n){
                if(grid[right]) gridConnect.union(right, pos);
                if(grid[top]) gridConnect.union(top, pos);
            }
            else if(pos == (n*n)-1){
                if(grid[left]) gridConnect.union(left, pos);
                if(grid[top]) gridConnect.union(top, pos); 
            }
            else{
                if(grid[left]) gridConnect.union(left, pos);
                if(grid[right]) gridConnect.union(right, pos);
                if(grid[top]) gridConnect.union(top, pos);
                if(grid[bottom]) gridConnect.union(bottom, pos);
            }
        justOpened = true;
            
    }

    public boolean isOpen(int i, int j) {
        // is site (row i, column j) open?
        int pos = n*i+j;
        if(grid[pos]) return true;
        else return false;
    }

    public boolean isFull(int i, int j) {
        if(justOpened) connect();
        // is site (row i, column j) full?
        if(grid[n*i+j]){
            int pos = n*i+j;
            int canonical = gridConnect.find(pos);
            ArrayList<Integer> union = family.get(canonical);
            for(int m = 0; m < union.size(); m++){
                if(grid[union.get(m)] == true && union.get(m) < n) return true;
            } 
        }
        return false;
    }    
    public boolean percolates() {
        if(justOpened) connect();
        // does the system percolate?
        for(Map.Entry element : family.entrySet()){
            ArrayList<Integer> union = (ArrayList<Integer>)element.getValue();
            for(int m = 0; m < union.size(); m++){
                if(grid[union.get(m)] == true && union.get(m) < n){
                    for(int p = 0; p < union.size(); p++){
                        if(grid[union.get(p)] == true && union.get(p) >= n*(n-1)) return true;
                    }
                }
            } 
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
    //     // test
        // Percolation s = new Percolation(5);
        // s.open(0, 0);
        // s.open(4, 4);
        // s.open(0, 4);
        // s.open(4, 0);
        // System.out.println(s.isFull(2, 3));
        // System.out.println(s.percolates());
        // for(int i = 0; i < 5; i++){
        //     s.open(i, i);
        //     if (i+1 < 5) s.open(i, i+1);
        //     System.out.println(s.isFull(i, i));
        //     System.out.println(s.percolates());
        // }
    }
// }