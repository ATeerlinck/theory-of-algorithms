

public class MedianOfMedians {

	private static void insertionSort(int[] arr, int left, int right) {
		for (int i = left + 1; i <= right; i++) {
			int j = i, temp = arr[j];
			while (j > left && temp < arr[j - 1]) {
				arr[j] = arr[j - 1];
				j--;
			}
			arr[j] = temp;
		}
	}

	public static int select(int[] array, int n, int k) {
		return select(array, 0, n - 1, k);
	}

	private static int select(int[] array, int left, int right, int k) { // complete this function
	}
}
