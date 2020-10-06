# Homework 2 (HW2)
###### tags: `2020pdsa`

Due: 10/9 21:00

[toc]

## Version
Python: 3.8.5 (Note: no numpy)
Java: openjdk14 (https://openjdk.java.net/projects/jdk/14/)
Platform: Debian

## Our Judge
https://140.112.183.224:13000

Grading Status:
* AC: ACcepted
* TLE: Time Limit Excess
* WA: Wrong Answer
* RE: Runtime Error (Mostly your program raise some error unexpectly)

Memory: 1G

Handin the file with utf-8 encoding if there are 中文 words inside your code (including comments).

### Template Download
https://cool.ntu.edu.tw/courses/3501/files/folder/Homework_Template

### Hint
How Python sort:
`nums = sorted(nums)`

# HW2-1 Board Game
Play a board game. The playing pieces in our board game are called stones. There are two types of stones: 'O' and 'X'. A set of stones can be flipped if the connected stones are surrounded by another type of stones (not including border).

We define connected as two stones are next to each other in vertically or horizontally.

For example:
```
.XXOX
XOXO.
XXOX.
XXOXX
..XOX
```

After the flipStone('O') operation:
```
.XXOX
XXXO.
XXXX.
XXXXX
..XOX
```

``` python
from typing import List


class BoardGame:
    def __init__(self, h :int, w :int):
        """
        Set the width and height of the board
        
        Parameters:
            h (int): The height of the board
            w (int): The width of the board
        """
        pass

    def putStone(self, x :List[int], y :List[int], stoneType :str):
        """
        Put the stones on (x[0],y[0]), (x[1], y[1]) ...
        
        We grantee that there are not stones at (x[i],y[i]) on the board.
        
        Parameters:
            x (int): The vertical position of the stone, 0 <= x < h
            y (int): The horizotal position of the stone, 0 <= y < w
            stoneType (string): The type of the stones to be put ont the board, which has only two values {'O', 'X'}
        """
        pass

    def surrounded(self, x :int, y :int) -> bool:
        """
        Answer if the stone and its connected stones are surrounded by another type of stones, which means they are qualified to be flipped if we want.
        
        Parameters:
            x (int): The vertical position of the stone, 0 <= x < h
            y (int): The horizaontal position of the stone, 0 <= y < w
        Returns:
            surrounded (bool): can be flipped of not.
        """
        return True

    def getStoneType(self, x :int, y :int) -> str:
        """
        Get the type of the stone at (x,y)
            
        We grantee that there are stones at (x,y)
        
        Parameters:
            x (int): The vertical position of the stone, 0 <= x < h
            y (int): The horizaontal position of the stone, 0 <= y < w
        Returns:
            stoneType (string): The type of the stones, which has only two value {'O', 'X'}
        """
        stoneType = 'X'
        return stoneType
```

``` java
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class BoardGame {
   public BoardGame(int h, int w) // create a board of size h*w
   public void putStone(int[] x, int[] y, char stoneType) // put stones on the board according to the coordinates
   public boolean surrounded(int x, int y) // Answer if the stone and its connected stones are surrounded by another type of stones
   public char getStoneType(int x, int y) // Get the type of the stone at (x,y)
}
```

## Example
Given a board 
```
...    ...    .X.    .X.    .X.
... -> .O. -> XOX -> XOX -> XOX
...    ...    ...    .X.    OX.
```

``` py
g = BoardGame(3, 3)
g.putStone([1], [1], 'O')
print(g.surrounded(1, 1))

g.putStone([0, 1, 1], [1, 0, 2], 'X')
print(g.surrounded(1, 1))

g.putStone([2], [1], 'X')
print(g.surrounded(1, 1))
print(g.surrounded(2, 1))

g.putStone([2], [0], 'O')
print(g.surrounded(2, 0))

-> 

False
False
True
False
False
```

Note the stone at the corner will never be surrounded.


## TestCase
Time Limit 2s

* `N` is the number of stones on the board.
* `M` is the total times we call `putStone` or `surrounded`
* We guarantee there is stone at (x,y) when we call surrounded(x,y)
* And there is no stone at (x,y) when we call putStone(x,y).


We removed the function `flipStone`.
We only call `__init__`(`BoardGame` in java), `putStone`, `surrounded`


Total 100 points (Not sure)
* 20 points: `w,h < 3` `M < 100` Simple
* 20 points: `w,h <= 1001` `M < 7000` Specical Case
* 20 points: `w,h <= 101` `M < 1000` 
* 20 points: `w,h <= 101` `M < 4000`
* 20 points: `w,h <= 10001` `M < 4000`



# HW2-2 Percolation
Give a material with NxN grids which are initially blocked . Then we open the grid one-by-one and ask you to check if the grid is full or the material is percolation.

## Definition
* Open: Turn the blocked grid to open grid
* Full: The grid can connect to top row grid via a chain of neighboring (left, right, up, down) open grid.
* Percolation: There has full grid at the bottom row.

## Reference
Assignment: https://introcs.cs.princeton.edu/java/assignments/percolation.html
Java visualizer: https://coursera.cs.princeton.edu/algs4/assignments/percolation/percolation.zip
Python visualizer:
https://introcs.cs.princeton.edu/python/24percolation/

## Template
``` python
class Percolation:
    def __init__(self, N :int):
        """ Create N-by-N grid, with all sites blocked """
        pass

    def open(self, i :int, j :int):
        """ Open site (row i, column j) if it is not open already """
        pass

    def isOpen(self, i :int, j :int) -> bool:
        """ Is site (row i, column j) open? """
        return True

    def isFull(self, i :int, j :int) -> bool:
        """ Is site (row i, column j) full? """
        return True
            
    def percolates(self) -> bool:
        """ Does the system percolate? """
        return True
```

``` java
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
   public Percolation(int N)               // create N-by-N grid, with all sites blocked
   public void open(int i, int j)          // open site (row i, column j) if it is not open already
   public boolean isOpen(int i, int j)     // is site (row i, column j) open?
   public boolean isFull(int i, int j)     // is site (row i, column j) full?
   public boolean percolates()             // does the system percolate?
}
```

[`algs4`](http://algs4.cs.princeton.edu/code/algs4.jar)

## Example


``` py
s = Percolation(3)
s.open(1,1)
print(s.isFull(1, 1)) 
print(s.percolates())
s.open(0,1)
s.open(2,0)
print(s.isFull(1, 1)) 
print(s.isFull(0, 1)) 
print(s.isFull(2, 0)) 
print(s.percolates())
s.open(2,1)
print(s.isFull(1, 1)) 
print(s.isFull(0, 1)) 
print(s.isFull(2, 0)) 
print(s.isFull(2, 1)) 
print(s.percolates())


=>

False
False

True
True
False
False

True
True
True
True
True
```

The final material should be like this
```
XOX
XOX
OOX
```

Java test (The same example)
``` java
public class Percolation {
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
}
```

## TestCase
Time Limit 2.5s (for python)
Time Limit 0.25s (for java)

We grantee the (i,j) is a opened grid when we called `isFull`. Note 0 <= i < N and 0 <= j < N

We grantee we will `open` the blocked grid.

Total number of our judger calling your function will be smaller than $3 N^2$

Total 100 points
* 20 points: `N < 5` Easy
* 20 points: `N < 50` Special Case
* 20 points: `N < 50`
* 20 points: `N < 100`
* 20 points: `N < 250`