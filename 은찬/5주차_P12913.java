import java.util.*;
class Solution {
    int solution(int[][] a) {
        int answer = 0;
        int n = a.length;
        int m = 4;
        int[][] dp = new int[n][m];

        for (int i=0; i < m; i++){
            dp[0][i] = a[0][i];
        }

        for (int y=1; y < n; y++){
            for (int x=0; x < m; x++){
                int max = 0;
                for (int z=0; z < m; z++){
                    if (z == x) continue;
                    max = Math.max(max, dp[y - 1][z]);
                }
                dp[y][x] = a[y][x] + max;
            }
        }

        for (int i=0; i < m; i++){
            answer = Math.max(answer, dp[n - 1][i]);
        }

        return answer;
    }
}