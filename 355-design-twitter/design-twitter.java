import java.util.*;

class Twitter {

    private static int time = 0;

    class Tweet {
        int id;
        int time;

        Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    private Map<Integer, Set<Integer>> followMap;
    private Map<Integer, List<Tweet>> tweetMap;

    public Twitter() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        tweetMap.putIfAbsent(userId, new ArrayList<>());
        tweetMap.get(userId).add(new Tweet(tweetId, time++));
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>(
            (a, b) -> b.time - a.time
        );

        // Ensure user follows themselves
        followMap.putIfAbsent(userId, new HashSet<>());
        followMap.get(userId).add(userId);

        for (int followee : followMap.get(userId)) {
            if (tweetMap.containsKey(followee)) {
                List<Tweet> tweets = tweetMap.get(followee);
                for (int i = tweets.size() - 1; i >= 0 && i >= tweets.size() - 10; i--) {
                    maxHeap.offer(tweets.get(i));
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        int count = 0;

        while (!maxHeap.isEmpty() && count < 10) {
            res.add(maxHeap.poll().id);
            count++;
        }

        return res;
    }

    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;

        followMap.putIfAbsent(followerId, new HashSet<>());
        followMap.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).remove(followeeId);
        }
    }
}