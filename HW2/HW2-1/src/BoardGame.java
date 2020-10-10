import java.util.ArrayList;
import java.util.HashMap;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class BoardGame {
    HashMap<Integer, Integer> stoneOrder = new HashMap<Integer, Integer>();
    HashMap<Integer, Boolean> stoneType = new HashMap<Integer, Boolean>();
    HashMap<Integer, Integer> stonePos = new HashMap<Integer,Integer>();
    HashMap<Integer, ArrayList<Integer>> stoneFamily = new HashMap<Integer, ArrayList<Integer>>();
    WeightedQuickUnionUF stoneConnect;
    int size = 0;
    int board = 0;
        
    public BoardGame(int h, int w) {
        // create a board of size h*w
        //ArrayList<ArrayList<String>> board = new ArrayList<>();
        stoneConnect = new WeightedQuickUnionUF(h*w);
        board = h*w;
        
    }
    
    public void putStone(int[] x, int[] y, char Type) {
        // put stones on the board according to the coordinate
        for(int i = 0; i < x.length; i++){
            int pos = x[i]*100000+y[i];
            boolean type = false;
            int order = size + i;
            if(Type == 'O'){
                type = true;
            }
            stoneType.put(pos, type);
            stoneOrder.put(pos, order);
            stonePos.put(order, pos);
            int left = (x[i]-1)*100000+y[i];
            int right = (x[i]+1)*100000+y[i];
            int top = x[i]*100000+y[i]+1;
            int bottom = x[i]*100000+y[i]-1;
            if(stoneType.containsKey(left)){
                if(stoneType.get(left)==stoneType.get(pos)){
                    stoneConnect.union(stoneOrder.get(pos), stoneOrder.get(left));
                }
            }
            if(stoneType.containsKey(right)){
                if(stoneType.get(right)==stoneType.get(pos)){
                    stoneConnect.union(stoneOrder.get(pos), stoneOrder.get(right));
                }
            }
            if(stoneType.containsKey(top)){
                if(stoneType.get(top)==stoneType.get(pos)){
                    stoneConnect.union(stoneOrder.get(pos), stoneOrder.get(top));
                }
            }
            if(stoneType.containsKey(bottom)){
                if(stoneType.get(bottom)==stoneType.get(pos)){
                    stoneConnect.union(stoneOrder.get(pos), stoneOrder.get(bottom));
                }
            }
        
        }

        size = size + x.length;

        for(int i = 0; i < size; i++){
            int canonical = stoneConnect.find(i);
            if(stoneFamily.containsKey(canonical)){
                stoneFamily.get(canonical).add(i);
            }
            else{
                ArrayList<Integer> family = new ArrayList<Integer>();
                stoneFamily.put(canonical, family);
                stoneFamily.get(canonical).add(i);
            }
        }
    }

    public boolean surrounded(int x, int y) {
        // Answer if the stone and its connected stones are surrounded by another type of stones
        int checkPos = x*100000+y;
        int checkOrder = stoneOrder.get(checkPos);
        int checkCanonical = stoneConnect.find(checkOrder);
        ArrayList<Integer> checkFamily = stoneFamily.get(checkCanonical);
        for (int i = 0; i < checkFamily.size(); i++){
            int current = stonePos.get(checkFamily.get(i));
            int left =  current - 100000;
            int right = current + 100000;
            int top = current + 1;
            int bottom = current - 1;
            if(stoneType.containsKey(left) == false || stoneType.containsKey(right) == false || stoneType.containsKey(top) == false || stoneType.containsKey(bottom) == false){
                return false;
            }  
        }
        return true;
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

