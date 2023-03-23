import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static class Node{
        int y; int x; int level; int b;
        public Node(int y, int x, int level, int b) {
            this.y = y;
            this.x = x;
            this.level = level;
            this.b = b;
        }
    }

    static int bfs(Node start) {
        Queue<Node> que = new LinkedList<>();
        que.offer(start);
        int[][][] visit = new int[2][n][m];
        visit[0][0][0] = 1;
        visit[1][0][0] = 1;

        while (!que.isEmpty()) {
            Node p = que.poll();
            if (p.y == n - 1 && p.x == m - 1) return p.level;

            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (visit[p.b][ny][nx] == 1) continue; // 단순 방문처리
                if (p.b == 1 && visit[0][ny][nx] == 1) continue; // 그리디한 방문처리
                if (p.b == 1 && map[ny][nx] == 1) continue; // 벽을 더이상 못부숨
                if (p.b == 0 && map[ny][nx] == 1) { // 벽읇 부술 때
                    visit[1][ny][nx] = 1;
                    que.offer(new Node(ny, nx, p.level + 1, 1));
                    continue;
                }
                visit[p.b][ny][nx] = 1;
                que.offer(new Node(ny, nx, p.level + 1, p.b));
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
        map = new int[n][m];

        for (int y = 0; y < n; y++) {
            String s = br.readLine();
            for (int x = 0; x < m; x++) {
                map[y][x] = s.charAt(x) - '0';
            }
        }

        int result = bfs(new Node(0, 0, 1, 0));

        bw.write(String.valueOf(result));

        br.close();
        bw.close();
    }
}
