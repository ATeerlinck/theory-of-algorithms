import java.util.stream.IntStream;

/**
 * SubsetPrac
 */
public class SubsetPrac {
    public static void main(String[] args) {
        int[] arr = new int[]{7,2,3,9};
        subsetSum(arr, arr.length, 12);
        arr = new int[]{4,6,3,2};
        int[] p = new int[]{70,20,60,30};
        Knapsack(arr, p, arr.length, 12);
    }
    public static boolean subsetSum(int[] arr, int n, int X){
        boolean[][] M = new boolean[n+1][X+1];
        for (int i = 0; i <= n; i++) {
            M[i][0] = true;
            if(i>=1)
            for (int j = 1; j <= X; j++) {
                M[0][j] = false;
                if(M[i-1][j]) M[i][j] = true;
                else if(arr[i-1] <= j && M[i-1][j-arr[i-1]]) M[i][j] = true;
                else M[i][j] = false;
            }
        }
        return M[n][X];
    }
    public static int Knapsack(int[] w,int[] p, int n, int W){
        int[][] P = new int[n+1][W+1];
        for (int i = 0; i <= n; i++) {
            P[i][0] = 0;
            if(i>=1)
            for (int j = 1; j <= W; j++) {
                P[0][j] = 0;
                if(w[i-1]>j) P[i][j] = P[i-1][j];
                else if(P[i-1][j] > P[i-1][j-w[i-1]] + p[i-1]) P[i][j] = P[i-1][j];
                else P[i][j] = P[i-1][j-w[i-1]] + p[i-1];
            }
        }
        return P[n][W];
    }
}
    