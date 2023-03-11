import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static Queue<Point> que;
    static int[][] visit;
    static int goal = 0;
    static int answer = 0;

    static class Point{
        int y; int x; int level;
        public Point(int y, int x, int level){
            this.y = y;
            this.x = x;
            this.level = level;
        }
    }

    static int bfs() {
        int cnt = 0;
        while (!que.isEmpty()) {
            Point p = que.poll();
            cnt = p.level;
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (visit[ny][nx] == 1) continue;
                if (map[ny][nx] == 0){
                    visit[ny][nx] = 1;
                    que.offer(new Point(ny, nx, p.level + 1));
                    goal--;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        que = new LinkedList<>();
        visit = new int[n][m];

        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < m; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 1) {
                    que.offer(new Point(y, x, 0));
                    visit[y][x] = 1;
                }
                if (map[y][x] == 0) goal++;
            }
        }

        int result = bfs();

        if (goal == 0) bw.write(String.valueOf(result));
        else bw.write(String.valueOf(-1));

        br.close();
        bw.close();
    }
}