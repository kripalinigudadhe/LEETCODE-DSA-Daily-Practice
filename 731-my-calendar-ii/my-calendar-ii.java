import java.util.*;

class MyCalendarTwo {
    List<int[]> bookings;
    List<int[]> overlaps;

    public MyCalendarTwo() {
        bookings = new ArrayList<>();
        overlaps = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        // Check triple booking
        for (int[] o : overlaps) {
            if (Math.max(o[0], start) < Math.min(o[1], end)) {
                return false;
            }
        }

        // Add overlaps
        for (int[] b : bookings) {
            if (Math.max(b[0], start) < Math.min(b[1], end)) {
                overlaps.add(new int[]{
                    Math.max(b[0], start),
                    Math.min(b[1], end)
                });
            }
        }

        bookings.add(new int[]{start, end});
        return true;
    }
}