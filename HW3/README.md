# Homework 3 (HW3)
###### tags: `2020pdsa`

Due: 10/30 21:00
Judge Open: 10/19

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


# HW3-1 Restaurants
Given a list of restaurants and then filter it.

The attributes of a restaurant are four integers in this order: `id` `rate` `price` `distance`, where the `rate` has only five possible values: 1,2,3,4 or 5.

To filter the list, I will give you `min_price`, `max_price`, `min_rate` then you return the resturants' id that meets the condition: `min_price <= price <= max_price` and `rate >= min_rate`.

The output is an integer list, which should be in the increasing order of distance; if the distance equals, order the restaurant ids from the largest to the smallest.


In case6, we want you to implement two comparators for the class Restaurant. One is the natural order, which should be implemented by the comparable interface (java), the other one is a comparator object/method (called `Comparator1`).

For example, there is a list of restaurants, `r :list[Restaurant]`. `sorted(r)` will sort the array by calling the natural comparator. Similariary `sorted(r, cmp=Compparator1)` will sort the list by `Comparator1`. See more details in the template session.

The natural comparator should order the restaurants by the formula `distance * price / rate`, if the value is the same, keep the same order as input.

The `comparator1` should order the restaurants by the rate in increasing order, distance in increasing order (if tied), and id in decreasing order (if tied again).



## Hint
* Sorting
* The concept of argsort
* Minimize the number of iterations (You should do as minimal steps as you can)
* You will get lots of TLE if you iterate the restaurants when unnecessary.
* Implement comparator
![](https://i.imgur.com/kLDopcJ.png)
* The above figure shows you should not waste time on P and Q region
* Data imblancing(e.g. number of rate1 >> rate5) will make `P >> M` when query `min_rate = 5`


## Template
``` python
import functools
from typing import List


class Restaurant(object):
    def __init__(self, id :int, rate :int, price :int, distance :int):
        pass

    def getID(self) -> int:
        return 0

    def __lt__(self, b) -> bool:
        """
        The natural comparator of Restaurant
        
        The comparator should compare the restaurants by the value calculated from the formula
        `distance * price / rate`
        and return True if the value of `self` is lower than `b`
        If the value is the same, keep the same order as input.
        """
        return True

    @staticmethod
    def comparator1(a, b) -> int:
        """
        Compare two restaurants by restaurant object

        Order the restaurants by the rate in increasing order,
        distance in increasing order (if tied),and 
        id in decreasing order (if tied again).
        
        Parameters:
            a(Restaurant): The restaurant object
            b(Restaurant): The restaurant object

        Returns:
            result(int): -1 for restaurant a has smaller order, 1 for restaurant b has smaller order, 0 for equal.
        """
        return 0


class Restaurants(object):
    def __init__(self, restaurants :List[Restaurant]):
        pass

    def filter(self, min_price: int, max_price :int, min_rate: int) -> List[int]:
        """
        Filter the restaurants, output the list of restaurant id that meet the condition.

        Output the list in in the increasing order of distance;
        If the distance is the same, order the restaurant ids from the highest to the lowest.

        Returns:
            restaurants (List[int]): The list of restaurant id.
        """
        return []


if __name__ == "__main__":
    rests = [
        # id, rate, price, distance
        Restaurant(20, 1, 20, 12),
        Restaurant(15, 3, 19, 11),
        Restaurant(19, 4, 19, 12),
        Restaurant(18, 5, 20, 11),
    ]
    r = Restaurants(rests)
    print(r.filter(0, 25, 3)) 
    print(r.filter(0, 25, 4)) 
    print(r.filter(0, 20, 1)) 
    print(r.filter(0, 10, 1))
    print(r.filter(0, 19, 1))
    print(r.filter(19, 19, 3))

    # case6
    rests = [
        # id, rate, price, distance
        Restaurant(3, 2, 3, 8),
        Restaurant(0, 2, 4, 6),
        Restaurant(2, 4, 5, 12),
        Restaurant(1, 5, 6, 11),
    ]
    print([i.getID() for i in sorted(rests)])
    print([i.getID() for i in sorted(rests, key=functools.cmp_to_key(Restaurant.comparator1))])

    """
Output:
[18, 15, 19]
[18, 19]
[18, 15, 20, 19]
[]
[15, 19]
[15, 19]
[3, 0, 1, 2]
[0, 3, 2, 1]
    """
```

``` java
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Collections;


class Restaurant implements Comparable<Restaurant> {
    Restaurant(int id, int rate, int price, int distance) {
    }

    public int getID() {
        return 0;
    }

    @Override
    public int compareTo(Restaurant b) {
        return 0;
    }

    public static class Comparator1 implements Comparator<Restaurant> {
        public int compare(Restaurant a, Restaurant b) {
            return 0;
        }
    }
}


class Restaurants {
    public Restaurants(List<Restaurant> restaurants) {
    }

    public int[] filter(int min_price, int max_price, int min_rate) {
        return new int[];
    }

    public static void main(String[] args) {
        // test
        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        restaurants.add(new Restaurant(20, 1, 20, 12));
        restaurants.add(new Restaurant(15, 3, 19, 11));
        restaurants.add(new Restaurant(19, 4, 19, 12));
        restaurants.add(new Restaurant(18, 5, 20, 11));
        Restaurants r = new Restaurants(restaurants);
        System.out.println(Arrays.toString(r.filter(0, 25, 3)));
        System.out.println(Arrays.toString(r.filter(0, 25, 4)));
        System.out.println(Arrays.toString(r.filter(0, 20, 1)));
        System.out.println(Arrays.toString(r.filter(0, 10, 1)));
        System.out.println(Arrays.toString(r.filter(0, 19, 1)));
        System.out.println(Arrays.toString(r.filter(19, 19, 3)));

        // case 6
        restaurants = new ArrayList<Restaurant>();
        restaurants.add(new Restaurant(3, 2, 3, 8));
        restaurants.add(new Restaurant(0, 2, 4, 6));
        restaurants.add(new Restaurant(2, 4, 5, 12));
        restaurants.add(new Restaurant(1, 5, 6, 11));

        Collections.sort(restaurants);
        for (Restaurant a: restaurants) {
            System.out.print(a.getID());
            System.out.print(" ");
        }
        System.out.println("");

        Collections.sort(restaurants, new Restaurant.Comparator1());
        for (Restaurant a: restaurants) {
            System.out.print(a.getID());
            System.out.print(" ");
        }
        System.out.println("");
    }
}
```

## TestCase
* `0 <= Distance, Price <= 10000`
* `0 <= id <= 10000000`
* The number of query(Called `filter`) will less than 1000
* We guarantee `id` is unique
* We guarantee `min_price <= max_price`


`N` is the number of restaurants
`M` is the total length of output for each sample(The total length of `filter` output for each class after initial)


* case1: 20 points: N = 10, M < 100
* case2: 20 points: N = 100000, M < 1000000 Special Case
* case3: 20 points: N = 1000, M < 100*N
* case4: 20 points: N = 9000, M < 100*N
* case5: 10 points: N = 100000, M < 10*N
* case6: 10 points: Implementation of compararsion.

## Time Limit
Python: 4s
Java: 1s


# HW3-2 Warriors 
There are warriors standing in a line. Each warrior has two important properties: **STH** and **RNG**. **STH** stands for "strength", the power of each warrior. **RNG** stands for Range, the effective radius that the warrior can attack.
Suppose that there are *N* warriors in this contest. An index *i* indicates one’s position (an integer coordinate). An attack will be blocked if there is a warrior with a higher or equal **STH** within the **RNG** distance. Formally speaking, let {$s_0$, $s_1$, ..., $s_{N−1}$} be the sequence of **STH** for the *N* warriors, and {$r_0$, $r_1$, ..., $r_{N−1}$} be the sequence of **RNG** for them, then the *i*-th warrior can attack the *j*-th warrior if and only if the following conditions are satisfied:
* $|j − i| ≤ r_i$,
* $s_j < s_i$
* $\{s_k\} < s_i$, for all *k* between *i* and *j*

• $a_i$ =  the index of the leftmost standing warrior that the *i*-th warrior can attack;
• $b_i$ =  the index of the rightmost standing warrior that the *i*-th warrior can attack.

Please determine the sequence of pairs {($a_0$, $b_0$), ..., ($a_{N-1}$, $b_{N−1}$)}.

To simplify the edge case, a warrior can attach itself.

## Hint
* Stack


## Template
python:
``` python
from typing import List

class Warriors: 
    def warriors(self, strength :List[int], attack_range :List[int]):
        """
        Given the attributes of each warriors and output the minimal and maximum
        index of warrior can be attacked by each warrior.

        Parameters:
          strength (List[int]): The strength value of N warriors
          attack_range (List[int]): The range value of N warriors

        Returns:
          attack_interval (List[int]):
              The min and the max index that the warrior can attack.
              The format of output is 2N int array `[a0, b0, a1, b1, ...]`
        """
        return []
```
java:
```java
import java.util.Arrays;


public class Warriors {
    public int[] warriors(int[] strength, int[] range) {
          //Given the attributes of each warriors and output the minimal and maximum 
          //index of warrior can be attacked by each warrior.
        return new int[]{};
    }

    public static void main(String[] args) {
        Warriors sol = new Warriors();
        System.out.println(Arrays.toString(
            sol.warriors(new int[] {11, 13, 11, 7, 15},
                         new int[] { 1,  8,  1, 7,  2})));
        // Output: [0, 0, 0, 3, 2, 3, 3, 3, 2, 4]
    }
}
```

## Example
``` python
if __name__ == "__main__":
    sol = Warriors()
    print(sol.warriors([11, 13, 11, 7, 15],
                       [ 1,  8,  1, 7,  2]))
    """
    # Output
    [0, 0, 0, 3, 2, 3, 3, 3, 2, 4]
    """
```

## TestCase
`N` is the number of warriors
`0 <= strength <= 1000000000`
`0 <= attack_range <= M`
`1 <= N`

Time Limit:
* Python: 4s
* Java: 2s

Case:
* case1: 20 points: N <= 10, M < 10
* case2: 20 points: N <= 200000, M <= 200000 Special case
* case3: 20 points: N <= 10000, M <= 5000
* case4: 20 points: N <= 400000, M <= 200000
* case5: 20 points: N <= 1000000, M <= 500000

