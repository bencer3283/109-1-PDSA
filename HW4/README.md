# Homework4 (HW4)
###### tags: `2020pdsa`

Due: 11/24 21:00
Judge Open: ?/?

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

Memory: 2G

Handin the file with utf-8 encoding if there are 中文 words inside your code (including comments).

### Template Download
https://cool.ntu.edu.tw/courses/3501/files/folder/Homework_Template

# HW4-1 Airport
There is a small town with n houses. The town needs an airport. An airport is basically a very long, very straight road. Think of it as an infinite line. We need to build the airport such that the average distance from each house to the airport is as small as possible. However, no one wants to walkacross the runway, so all of the houses must be on the same side of the airport. Where should we build the airport, and what will be the average distance?

(Some houses may be a distance of zero away from the runway, but that’s ok; we’ll give them some free ear plugs.)

![](https://i.imgur.com/VRaV9f1.png =400x)


## Hint
* Convex Hull
* How to calculate distance from a point to a line
![](https://i.imgur.com/XGxnhDX.png)
* Hint2
![](https://i.imgur.com/fYHmbSG.png)



* Use `Long Long int`, or `double` to avoid overflow

## Template
``` python
from typing import List
import math


class Airport:
    def airport(self, houses: List[List[int]]) -> float:
        """
        Find the best place to build airport and
        calculate the average distance from all the house to airport

        Parameters:
            houses(list[list[int]]): List of houses.
                Each house contains [x,y] coordination.

        Returns:
            distance(float)
        """
        return 0.


if __name__ == "__main__":
    print(Airport().airport([[0,0],[1,0]]))
    """
    0.0
    """
    print(Airport().airport([[0,0],[1,0],[0,1]]))
    """
    *.
    **
    # Convex: [[0, 0], [1, 0], [0, 1]]
    0.2357022603955159
    """
    print(Airport().airport([[0,0],[2,0],[0,2],[1,1],[2,2]]))
    """
    *.*
    .*.
    *.*
    # Convex: [[0, 0], [2, 0], [2, 2], [0, 2]]
    1.0
    """
    print(Airport().airport([[1,1],[2,2],[0,2],[2,0],[2,4],[3,3],[4,2],[4,1],[4,0]]))
    """
    ..*..
    ...*.
    *.*.*
    .*..*
    ..*.*
    # Convex: [[0, 2], [2, 0], [4, 0], [4, 2], [2, 4]]
    1.3356461422412562
    """
```

``` java
import java.util.List;
import java.util.ArrayList;
import java.lang.Math;


class Airport {
    public double airport(List<int[]> houses) {
        // Output smallest average distance with optimal selection of airport location.
        return 0.; 
    }   

    public static void main(String[] args) {
        // More example are in Python template
        System.out.println(new Airport().airport(new ArrayList<int[]>(){{
            add(new int[]{0,0});
            add(new int[]{1,0});
            add(new int[]{0,1});
        }}));
    }   
}
```

## TestCase
* `0 <= x,y <= M`, x and y are integer.
* We guarantee the coordinates are unique
`N` is the number of houses
* We will check your answer by `abs(output - answer) <= 1e-4`

Cases
* case1: 20 points: N = 10, M <= 100
* case2: 20 points: N = 60000, M < 1000000 Special Case(Four samples)
* case3: 20 points: N = 100, M <= 10
* case4: 20 points: N = 1000, M <= 1000
* case5: 20 points: N = 100000, M <= 100000

## Time Limit
(We can extend it)
Python: 6s
Java: 2s

# Modified from 
https://onlinejudge.org/external/111/11168.pdf