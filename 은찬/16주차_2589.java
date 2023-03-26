import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static char[][] map;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static class Node {
        int y; int x; int level;
        public Node(int y, int x, int level) {
            this.y = y;
            this.x = x;
            this.level = level;
        }
    }

    static int bfs(Node start) {
        int[][] visit = new int[n][m];
        visit[start.y][start.x] = 1;
        Queue<Node> que = new LinkedList<>();
        que.offer(start);
        int max = 0;

        while (!que.isEmpty()) {
            Node p = que.poll();
            max = Math.max(max, p.level);

            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (visit[ny][nx] == 1) continue;
                if (map[ny][nx] == 'W') continue;
                visit[ny][nx] = 1;
                que.offer(new Node(ny, nx, p.level + 1));
            }
        }
        return max;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        for (int y = 0; y < n; y++) {
            String s = br.readLine();
            for (int x = 0; x < m; x++) {
                map[y][x] = s.charAt(x);
            }
        }

        int answer = 0;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < m; x++) {
                if (map[y][x] == 'L') {

                    answer = Math.max(answer, bfs(new Node(y, x, 0)));
                }
            }
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}