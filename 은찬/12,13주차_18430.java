import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int[][] visit;
    static int[][] dir = {{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    static int answer = 0;
    static void dfs(int y, int x, int sum) {
        if (y >= n) return;
        if (visit[y][x] == 1) {
            if (x < m - 1) dfs(y, x + 1, sum);
            else dfs(y + 1, 0, sum);
            return;
        }

        //skip 하는 부분
        if (x < m - 1) dfs(y, x + 1, sum);
        else dfs(y + 1, 0, sum);
        //연산 하는 부분
        visit[y][x] = 1;
        for (int i = 0; i < 4; i++) {
            int ny = y + dir[i][0];
            int nx = x + dir[i][1];
            if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
            if (visit[ny][x] == 1 || visit[y][nx] == 1) continue;
            visit[ny][x] = 1;
            visit[y][nx] = 1;
            int nextSum = sum;
            nextSum += map[y][x] * 2;
            nextSum += map[ny][x];
            nextSum += map[y][nx];
            answer = Math.max(answer, nextSum);
            if (x < m - 1) dfs(y, x + 1, nextSum);
            else dfs(y + 1, 0, nextSum);
            visit[ny][x] = 0;
            visit[y][nx] = 0;
        }
        visit[y][x] = 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < m; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        visit = new int[n][m];
        dfs(0, 0, 0);

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
