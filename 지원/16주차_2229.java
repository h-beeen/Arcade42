import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] list = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list[i + 1] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1]; // dp[i] = i번째 학생까리 고려했을 경우 max
        dp[0] = 0;
        dp[1] = 0; // 1명이면 max = 0

        for (int i = 2; i <= N; i++) {
            int max = list[i];
            int min = list[i];
            for (int j = i; j >= 1; j--) { // (j vs 나머지) -> (j ~ j-1 vs 나머지) -> (j ~ j-2 vs 나머지) -> (j ~ j-3 vs 나머지) -> ... => update max per case
                max = Math.max(max, list[j]); // j ~ j-N 최댓값
                min = Math.min(min, list[j]); // j ~ j-N 최솟값

                dp[i] = Math.max(dp[i], dp[j - 1] + (max - min)); // (j - j ~ N) = (max - min) vs 나머지 = dp[j-1]
            }
        }

        System.out.println(dp[N]);
    }
}

/*
10
2 5 7 1 3 4 8 6 9 3
>> 20
 */