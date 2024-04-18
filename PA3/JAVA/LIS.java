
import java.util.ArrayList;

public class LIS {

	public static ArrayList<Integer> longestIncreasingSubsequence(int[] arr, int len) { // complete this method
		int[] length = new int[len];
		int[] pred = new int[len];
		for (int i = 0; i < len; i++) {
			length[i] = 1;
			pred[i] = -1;
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i] && length[j] + 1 > length[i]) {
					length[i] = length[j] + 1;
					pred[i] = j;
				}
			}
		}
		int lisIndex = 0;
		for (int i = 0; i < pred.length; i++) {
			lisIndex = length[i] > length[lisIndex] ? i : lisIndex;
		}
		ArrayList<Integer> d = new ArrayList<>();
		while (lisIndex >= 0) {
			d.add(arr[lisIndex]);
			lisIndex = pred[lisIndex];
		}
		reverse(d);
		return d;
	}

	private static void reverse(ArrayList<Integer> list) {
		for (int i = 0, j = list.size() - 1; i < j; i++, j--) {
			int temp = list.get(j);
			list.set(j, list.get(i));
			list.set(i, temp);
		}
	}
}
