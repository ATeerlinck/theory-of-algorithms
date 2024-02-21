

public class RadixSort {

	private static void countSortOnDigits(int A[], int n, int digits[]) { // complete this function
		int[] C = new int[n], T = new int[n];
		for(int i = 0; i<n;i++) C[digits[i]]++;
		for (int i = 1; i <=n-1; i++) C[i] = C[i-1] + C[i];
		for (int i = n-1; i >= 0; i--) {
			C[digits[i]]--;
			T[C[digits[i]]] = A[i];
		}
		for (int i = 0; i<n;i++) A[i] = T[i];
	}

	private static void radixSortNonNeg(int A[], int n) { // complete this function
		int M = A[0];
		for (int i : A) if (i>M) M = i;
		int[] digits = new int[n];
		int e = 1;
		while(M/e > 0){
			for (int i = 0; i < n; i++) {
				digits[i] = (A[i]/e)%n;
			}
			countSortOnDigits(A, n, digits);
			e = e*n;
		}
	}

	public static void radixSort(int[] array, int n) { // complete this function
		//Approach 2 will be awarded 5 bonus points. Avoiding the creation of extra arrays for the negative and non-negative integers in Approach 2 will be rewarded 10 bonus points.
		int[] NEG = new int[n], NONNEG = new int[n];
		int negativeCount = 0;
		for (int i = 0; i < array.length; i++) {
			if(array[i]<0) {
				NEG[i] = Math.abs(array[i]);
				negativeCount++;
			}
			else NONNEG[i] = array[i];
		}
		radixSortNonNeg(NEG, n);
		radixSortNonNeg(NONNEG, n);
		for (int i = 0; i < n; i++) {
			if(i<negativeCount) array[i] = -NEG[n-i-1];
			else array[i] = NONNEG[i];
		}
		negativeCount++;
	}
}
