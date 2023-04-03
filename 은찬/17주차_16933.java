import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int k;
    static int[][] map;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static class Node{
        int y; int x; int level; int breakCnt;
        public Node(int y, int x, int level, int breakCnt) {
            this.y = y;
            this.x = x;
            this.level = level;
            this.breakCnt = breakCnt;
        }
    }

    static int bfs() {
        int[][][] visit = new int[k + 1][n][m];
        visit[0][0][0] = 1;
        visit[1][0][0] = 1;

        Queue<Node> que = new ArrayDeque<>();
        que.offer(new Node(0, 0, 0, 0));

        boolean day = true;
        int now = 0;

        while (!que.isEmpty()) {
            Node p = que.poll();
            // 종료 조건
            if (p.y == n - 1 && p.x == m - 1) {
                return p.level;
            }
            // 낮 밤 교체
            if (now != p.level) {
                day = !day;
                now = p.level;
            }

            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (visit[p.breakCnt][ny][nx] == 1) continue;
                //벽없어서 그냥 넘어가는 경우
                if (map[ny][nx] == 0) {
                    for (int j = p.breakCnt; j <= k; j++) {
                        visit[j][ny][nx] = 1;
                    }
                    que.offer(new Node(ny, nx, p.level + 1, p.breakCnt));
                    continue;
                }
                //낮에 벽을 부수고 가는 경우
                if (p.breakCnt < k && day) {
                    for (int j = p.breakCnt + 1; j <= k; j++) {
                        visit[j][ny][nx] = 1;
                    }
                    que.offer(new Node(ny, nx, p.level + 1, p.breakCnt + 1));
                }
                //밤이라 벽을 못 부수는 경우
                if (p.breakCnt < k && !day) {
                    que.offer(new Node(p.y, p.x, p.level + 1, p.breakCnt));
                }
                //모두 소진해서 벽을 못부수는 경우 그냥 종료
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int y = 0; y < n; y++) {
            String s = br.readLine();
            for (int x = 0; x < m; x++) {
                map[y][x] = s.charAt(x) - '0';
            }
        }


        int answer = bfs();

        if (answer == -1) {
            bw.write(String.valueOf(answer));
        } else {
            bw.write(String.valueOf(answer + 1));
        }



        br.close();
        bw.close();
    }
}