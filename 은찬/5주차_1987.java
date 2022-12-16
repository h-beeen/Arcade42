import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static char[][] map;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static Map<Character, Integer> check = new HashMap<>();
    static int max = Integer.MIN_VALUE;

    static void dfs(int dy, int dx) {
        max = Math.max(max, check.size());
        for (int i = 0; i < 4; i++) {
            int ny = dy + dir[i][0];
            int nx = dx + dir[i][1];
            if (ny <0 || nx < 0 || ny >= n || nx >= m) continue;
            if (check.containsKey(map[ny][nx])) continue;
            check.put(map[ny][nx], 1);
            dfs(ny, nx);
            check.remove(map[ny][nx]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        for (int y = 0; y < n; y++) {
            String temp = br.readLine();
            for (int x = 0; x < m; x++) {
                char ch = temp.charAt(x);
                map[y][x] = ch;
            }
        }

        check.put(map[0][0], 1);
        dfs(0, 0);

        bw.write(String.valueOf(max));

        br.close();
        bw.close();
    }
}