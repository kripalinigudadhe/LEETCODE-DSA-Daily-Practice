import java.util.*;

class Solution {
    public int scheduleCourse(int[][] courses) {
        // Sort by deadline
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        int totalTime = 0;
        
        for (int[] course : courses) {
            int duration = course[0];
            int lastDay = course[1];
            
            totalTime += duration;
            maxHeap.offer(duration);
            
            // If exceeds deadline, remove longest course
            if (totalTime > lastDay) {
                totalTime -= maxHeap.poll();
            }
        }
        
        return maxHeap.size();
    }
}