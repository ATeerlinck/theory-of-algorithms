public class Knapsack01 {

	public static int findOptimalProfit(final int profits[], final int weights[], int numElements, int capacity) {
		// complete this function
		int[] currentRow = new int[capacity + 1];
		int[] prevRow = new int[capacity + 1];
		for (int i = 0; i < numElements; i++) {
			if (weights[i] <= capacity) {
				for (int j = 0; j < weights[i]; j++) {
					currentRow[j] = prevRow[j];
				}
				for (int j = weights[i]; j <= capacity; j++) {
					currentRow[j] = Integer.max(prevRow[j], prevRow[j - weights[i]] + profits[i]);
				}
				System.arraycopy(currentRow, 0, prevRow, 0, currentRow.length);
			}
		}
		return currentRow[capacity];
	}
}
