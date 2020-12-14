import edu.princeton.cs.algs4.RedBlackBST;


class Calendar {

    RedBlackBST<Integer, Integer> calender = new RedBlackBST<Integer, Integer>();

    public Calendar() {};

    public boolean book(int start, int end) {
        if(calender.isEmpty()){
            calender.put(start, end);
            return true;
        }
        else{
            Integer preEvent = null;
            if(calender.min() <= start) preEvent = calender.floor(start);
            Integer postEvent = null;
            if(calender.max() >= start) postEvent = calender.ceiling(start);
            
            if((preEvent == null || calender.get(preEvent) <= start) && (postEvent == null || postEvent >= end)){
                calender.put(start, end);
                return true;
            }
            else return false;
        }
    }

    public static void main(String[] args) {
        Calendar calendar = new Calendar();
        System.out.println(calendar.book(10, 20));
        System.out.println(calendar.book(15, 25));
        System.out.println(calendar.book(20, 25));
        System.out.println(calendar.book(17, 21));
        System.out.println(calendar.book(0, 3));
        System.out.println(calendar.book(2, 6));
        System.out.println(calendar.book(3, 6));

        Calendar calendar2 = new Calendar();
        System.out.println(calendar2.book(5, 15));
        System.out.println(calendar2.book(0, 18));
        System.out.println(calendar2.book(24, 29));
        System.out.println(calendar2.book(13, 25));
        System.out.println(calendar2.book(18, 22));
        System.out.println(calendar2.book(15, 18));
    }
}