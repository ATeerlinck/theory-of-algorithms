

import java.util.*;

public class GreedyIntervals {

	private static void sortIntervalsByStartTime(List<Interval> intervals) {
		Collections.sort(intervals, (arg1, arg2) -> arg1.start - arg2.start);
	}

	private static void sortIntervalsByEndTime(List<Interval> intervals) {
		Collections.sort(intervals, (arg1, arg2) -> arg1.end - arg2.end);
	}

	public static ArrayList<Interval> schedule(List<Interval> intervals) { // complete this method
		sortIntervalsByEndTime(intervals);
		ArrayList<Interval> optimal = new ArrayList<>();
		optimal.add(intervals.getFirst());
		Iterator<Interval> it = intervals.iterator();
		while (it.hasNext()) {
			Interval next = it.next();
			if(next.start >= optimal.getLast().end) optimal.add(next);
		}
		return optimal;
	}

	public static int color(List<Interval> intervals) { // complete this method
		sortIntervalsByStartTime(intervals);
		PriorityQueue<Integer> pq = new PriorityQueue<>(new IntegerComparator());
		Iterator<Interval> it = intervals.iterator();
		pq.offer(it.next().end);
		while (it.hasNext()) {
			Interval next = it.next();
			if(next.start >= pq.peek()) pq.poll();
			pq.offer(next.end);
		}	
		return pq.size();
	}
}
