
import java.util.HashMap;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class BoardGame {
    HashMap<Integer, Boolean> stoneType = new HashMap<Integer, Boolean>();
    HashMap<Integer, Integer> familySides = new HashMap<Integer, Integer>();
    WeightedQuickUnionUF stoneConnect;
    int size = 0;
    int board = 0;
    int height = 0;
    int width = 0;
        
    public BoardGame(int h, int w) {
        // create a board of size h*w
        stoneConnect = new WeightedQuickUnionUF(h*w);
        height = h;
        width = w;
    }
    
    public void putStone(int[] x, int[] y, char Type) {
        // put stones on the board according to the coordinate
        for(int i = 0; i < x.length; i++){
            //int pos = y[i]+x[i]*100000;
            int index = y[i]+x[i]*width;
            boolean type = false;
            if(Type == 'O') type = true;
            stoneType.put(index, type);
            familySides.put(index, 4);

            //int left = (x[i]-1)*100000+y[i];
            int indexLeft = x[i]*width + y[i] - 1;//(left%100000)*width + (left/100000);
            //int right = (x[i]+1)*100000+y[i];
            int indexRight = x[i]*width + y[i] + 1;//(right%100000)*width + (right/100000);
            //int top = x[i]*100000+y[i]+1;
            int indexTop = (x[i] - 1)*width + y[i];//(top%100000)*width + (top/100000);
            //int bottom = x[i]*100000+y[i]-1;
            int indexBottom = (x[i]+1)*width + y[i];//(bottom%100000)*width + (bottom/100000);

            int thisfamily = stoneConnect.find(index);

            if(stoneType.containsKey(indexLeft)){
                int compareToFamily = stoneConnect.find(indexLeft);
                if(stoneType.get(indexLeft)==stoneType.get(index)){
                    if(thisfamily == compareToFamily){
                        familySides.put(thisfamily, familySides.get(thisfamily)-2);
                    }
                    else{
                        int sides = familySides.get(thisfamily) + familySides.get(compareToFamily) - 2;
                        stoneConnect.union(index, indexLeft);
                        familySides.put(thisfamily, sides);
                    }
                }
                else{
                    // sides --
                    familySides.put(compareToFamily, familySides.get(compareToFamily)-1);
                    familySides.put(thisfamily, familySides.get(thisfamily)-1);
                }
            }
            if(stoneType.containsKey(indexRight)){
                int compareToFamily = stoneConnect.find(indexRight);
                if(stoneType.get(indexRight)==stoneType.get(index)){
                    if(thisfamily == compareToFamily){
                        familySides.put(thisfamily, familySides.get(thisfamily)-2);
                    }
                    else{
                        int sides = familySides.get(thisfamily) + familySides.get(compareToFamily) - 2;
                        stoneConnect.union(index, indexRight);
                        familySides.put(thisfamily, sides);
                    }
                }
                else{
                    // sides --
                    familySides.put(compareToFamily, familySides.get(compareToFamily)-1);
                    familySides.put(thisfamily, familySides.get(thisfamily)-1);
                }
            }
            if(stoneType.containsKey(indexTop)){
                int compareToFamily = stoneConnect.find(indexTop);
                if(stoneType.get(indexTop)==stoneType.get(index)){
                    if(thisfamily == compareToFamily){
                        familySides.put(thisfamily, familySides.get(thisfamily)-2);
                    }
                    else{
                        int sides = familySides.get(thisfamily) + familySides.get(compareToFamily) - 2;
                        stoneConnect.union(index, indexTop);
                        familySides.put(thisfamily, sides);
                    }
                }
                else{
                    // sides --
                    familySides.put(compareToFamily, familySides.get(compareToFamily)-1);
                    familySides.put(thisfamily, familySides.get(thisfamily)-1);
                }
            }
            if(stoneType.containsKey(indexBottom)){
                int compareToFamily = stoneConnect.find(indexBottom);
                if(stoneType.get(indexBottom)==stoneType.get(index)){
                    if(thisfamily == compareToFamily){
                        familySides.put(thisfamily, familySides.get(thisfamily)-2);
                    }
                    else{
                        int sides = familySides.get(thisfamily) + familySides.get(compareToFamily) - 2;
                        stoneConnect.union(index, indexBottom);
                        familySides.put(thisfamily, sides);
                    }
                }
                else{
                    // sides --
                    familySides.put(compareToFamily, familySides.get(compareToFamily)-1);
                    familySides.put(thisfamily, familySides.get(thisfamily)-1);
                }
            }
        }
    }

    public boolean surrounded(int x, int y) {
        // Answer if the stone and its connected stones are surrounded by another type of stones
        boolean sides = familySides.get(stoneConnect.find(x*width+y)) == 0;
        if(x == 0 || x == height-1 || y == 0 || y == width-1) return false;
        if(height < 3 || width < 3) return false;
        if(stoneType.containsKey(x*width+y)) {
            if(sides) return true;
            else return false;
        }
        else return false;
    }

    public char getStoneType(int x, int y) {
        return 'O';
    }

    public static void main(String[] args) {
        BoardGame test = new BoardGame(3, 3);
        int[] array1 = {1};
        int[] array2 = {0, 1, 1};
        int[] array3 = {1, 0, 2};
        int[] array4 = {2};
        int[] array5 = {0};
        test.putStone(array1,array1, 'O');
        System.out.println(test.surrounded(1, 1));

        test.putStone(array2, array3, 'X');
        System.out.println(test.surrounded(1, 1));

        test.putStone(array4, array1, 'X');
        System.out.println(test.surrounded(1, 1));
        System.out.println(test.surrounded(2, 1));

        test.putStone(array4, array5, 'O');
        System.out.println(test.surrounded(2, 0));

    }
}

