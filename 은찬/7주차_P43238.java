import java.io.*;
import java.util.*;

public class Main {
    public long solution(int n, int[] times) {
        long answer = Long.MAX_VALUE;

        Arrays.sort(times);
        int len = times.length;
        long last = times[len - 1];

        long max = last * n;
        long lt = 0;
        long rt = max;

        while(lt <= rt){
            long m = (lt + rt) / 2;
            long cnt = 0;
            for (int i=0; i < len; i++){
                cnt += m / times[i];
            }
            if (cnt < n) lt = m + 1;
            else if (cnt >= n) {
                rt = m - 1;
                answer = Math.min(answer, m);
            }
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