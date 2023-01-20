import java.io.*;
import java.util.*;

public class Main {
    //DP를 기본으로 풀면서 그리디한 우선순위큐를 적용해서 시간복잡도를 줄이며 풀었다.
    static int n;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static class Node{
        int y; int x; int val;
        public Node(int y, int x, int val){
            this.y = y;
            this.x = x;
            this.val = val;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        int[][] dp = new int[n][n];
        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                dp[y][x] = 1;
                que.offer(new Node(y, x, map[y][x]));
            }
        }




        int answer = 0;
        while (!que.isEmpty()) {
            Node p = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                if (map[ny][nx] >= map[p.y][p.x]) continue;
                dp[p.y][p.x] = Math.max(dp[p.y][p.x], dp[ny][nx] + 1);
            }
            answer = Math.max(answer, dp[p.y][p.x]);
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}