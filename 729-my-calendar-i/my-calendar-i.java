import java.util.*;

class MyCalendar {

    List<int[]> events;

    public MyCalendar() {
        events = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] e : events) {
            if (start < e[1] && end > e[0]) {
                return false;
            }
        }
        events.add(new int[]{start, end});
        return true;
    }
}