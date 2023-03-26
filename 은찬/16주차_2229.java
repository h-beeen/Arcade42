import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int[] dp = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int y = 1; y < n; y++) {
            for (int x = 0; x <= y; x++) {
                int abs = Math.abs(arr[y] - arr[x]);
                int next;
                if (x == 0) next = abs;
                else next = abs + dp[x - 1];
                dp[y] = Math.max(dp[y], next);
            }
        }

        bw.write(String.valueOf(dp[n - 1]));

        br.close();
        bw.close();
    }
}