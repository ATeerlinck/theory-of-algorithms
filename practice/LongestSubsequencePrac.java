import java.util.ArrayList;
import java.util.Arrays;

public class LongestSubsequencePrac {
    public static void main(String[] args) {
        int[] arr = new int[] { 130, 90, 60, 150, 80, 40, 70, 0, 120, 95 };
        System.out.println(Arrays.toString(arr));
        String out = "LIS: ";
        ArrayList<Integer> l = LIS(arr, arr.length);
        while (l.size() > 0) {
            out += l.removeFirst();
            out += ", ";
        }
        System.out.println(out);
        out = "LDS: ";
        l = LDS(arr, arr.length);
        while (l.size() > 0) {
            out += l.removeFirst();
            out += ", ";
        }
        System.out.println(out);
        LCS("AGTCXG","ACGT");
    }

    public static ArrayList<Integer> LIS(int[] A, int n) {
        int[] length = new int[n];
        int[] pred = new int[n];
        length[0] = 1;
        pred[0] = -1;
        for (int i = 1; i < n; i++) {
            ArrayList<Integer> psi = new ArrayList<>();
            for (int k = 0; k < i; k++)
                if (A[k] < A[i])
                    psi.add(k);
            if (psi.size() > 0) {
                int j = psi.getFirst();
                for (int k : psi) {
                    if (length[k] > length[j])
                        j = k;
                }
                length[i] = length[j] + 1;
                pred[i] = j;
            } else {
                length[i] = 1;
                pred[i] = -1;
            }
        }
        int lisIndex = getMax(length);
        ArrayList<Integer> answer = new ArrayList<>();
        while (lisIndex >= 0) {
            answer.addFirst(A[lisIndex]);
            lisIndex = pred[lisIndex];
        }
        return answer;
    }

    public static ArrayList<Integer> LDS(int[] A, int n) {
        int[] length = new int[n];
        int[] pred = new int[n];
        length[0] = 1;
        pred[0] = -1;
        for (int i = 1; i < n; i++) {
            ArrayList<Integer> psi = new ArrayList<>();
            for (int k = 0; k < i; k++)
                if (A[k] > A[i])
                    psi.add(k);
            if (psi.size() > 0) {
                int j = psi.getFirst();
                for (int k : psi) {
                    if (length[k] > length[j])
                        j = k;
                }
                length[i] = length[j] + 1;
                pred[i] = j;
            } else {
                length[i] = 1;
                pred[i] = -1;
            }
        }
        int lisIndex = getMax(length);
        ArrayList<Integer> answer = new ArrayList<>();
        while (lisIndex >= 0) {
            answer.addFirst(A[lisIndex]);
            lisIndex = pred[lisIndex];
        }
        return answer;
    }

    public static String LCS(String n, String m){
        int [][] LEN = new int[n.length()+1][m.length()+1];
        String[][] DIR = new String[n.length()+1][m.length()+1];
        int i, j;
        LEN[0][0] = 0;
        for(i = 0; i<=n.length();i++){
            for (j = 0; j <= m.length(); j++) {
                if(i == 0 || j == 0) DIR[i][j] = null;
                else if(n.charAt(i-1) == m.charAt(j-1)){
                    LEN[i][j] = 1 + LEN[i-1][j-1];
                    DIR[i][j] = "D";
                }
                else if (LEN[i-1][j] > LEN[i][j-1]){
                    LEN[i][j] = LEN[i-1][j];
                    DIR[i][j] = "U";
                }
                else {
                    LEN[i][j] = LEN[i][j-1];
                    DIR[i][j] = "L";
                }
            }
        }
        i = n.length();
        j = m.length();
        String les = "";
        while (DIR[i][j] != null){
            if(DIR[i][j] == "D"){
                les = n.charAt(i-1)+les;
                i--;
                j--; 
            }
            else if(DIR[i][j] == "U") i--;
            else j--;
        }
        return les;
    }

    public static int getMax(int[] A) {
        int max = 0;
        for (int i =0; i<A.length; i++) {
            if (A[max] < A[i])
                max = i;
        }
        return max;
    }
}
