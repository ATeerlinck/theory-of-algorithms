
public class LCS {

	public static String longestCommonSubsequence(final String x, final String y) { // complete this method
        int lenx = x.length();
        int leny = y.length();
        int[][] length = new int[lenx+1][leny+1];
        for (int i = 0; i <= lenx; i++) {
            length[i][0] = 0;
        }
        for (int j = 0; j <= leny; j++) {
            length[0][j] = 0;
        }
        for (int i = 1; i <= lenx; i++) {
            for (int j = 1; j <= leny; j++) {
                if(x.charAt(i-1) == y.charAt(j-1)) length[i][j] = length[i-1][j-1]+1;
                else if (length[i-1][j]> length[i][j-1]) length[i][j] = length[i-1][j];
                else length[i][j]= length[i][j-1];
            }
        }
        String answer = "";
        while(lenx>0 && leny>0){
            if(x.charAt(lenx-1)==y.charAt(leny-1)){
                answer += x.charAt(lenx-1);
                lenx--;
                leny--;
            }
            else if(length[lenx-1][leny]> length[lenx][leny-1]) lenx--;
            else leny--; 
        }
        return reverse(answer);
	}
    
    private static String reverse(String str) {
        StringBuilder rev = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            rev.append(str.charAt(i));
        }
        return rev.toString();
    }
}
