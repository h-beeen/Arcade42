import java.io.*;
import java.util.*;
 
public class Main {
    public int solution(int[] m) {
        int answer = 0;
        int n = m.length;

        if (n == 3){
            answer = Arrays.stream(m).max().getAsInt();
        } else if (n == 4){
            answer = Math.max(answer, m[0] + m[2]);
            answer = Math.max(answer, m[1] + m[3]);
        } else{
            int[] dp = new int[n];
            int[] dp2 = new int[n];
            for (int i=0; i < n; i++){
                dp[i] = m[i];
                dp2[i] = m[i];
            }
            dp[0] = 0;
            dp2[n - 1] = 0;
            answer = getRet(dp, answer, n);
            answer = getRet(dp2, answer, n);
        }

        return answer;
    }
    static int getRet(int[] dp, int answer, int n){
        for (int i = 2; i < n; i++){
            int pre1 = i - 3;
            int pre2 = i - 2;
            int max;
            if (i == 2) max = dp[0];
            else max = Math.max(dp[pre1], dp[pre2]);
            dp[i] = max + dp[i];
            answer = Math.max(answer, dp[i]);
        }
        return answer;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;


        br.close();
        bw.close();
    }
}