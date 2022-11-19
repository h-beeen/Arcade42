import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int k;
    static int n1, n2;
    static int[][] dir = {
            {-1, 0}, {0, -1}, {0, 1}, {1, 0}
    };
    static int[][] horse = {
            {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2},
            {1, -2}, {2, -1}, {2, 1}, {1, 2}
    };
    static int[][] map;
    static int[][][] visit;

    static class Node{
        int y;
        int x;
        int level;
        int horseCnt;

        public Node(int y, int x, int level, int horseCnt) {
            this.y = y;
            this.x = x;
            this.level = level;
            this.horseCnt = horseCnt;
        }
    }

    static int bfs(Node node) {
        Queue<Node> que = new LinkedList<>();
        visit[0][0][0] = 1;
        que.offer(node);

        while (!que.isEmpty()) {
            Node p = que.poll();
            if (p.y == n2 - 1 && p.x == n1 - 1) {
                return p.level;
            }
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || ny >= n2 || nx < 0 || nx >= n1) continue;
                if (map[ny][nx] == 1) continue;
                if (visit[p.horseCnt][ny][nx] == 1) continue;
                visit[p.horseCnt][ny][nx] = 1;
                que.offer(new Node(ny, nx, p.level + 1, p.horseCnt));
            }
            if (p.horseCnt < k) {
                for (int i = 0; i < 8; i++) {
                    int ny = p.y + horse[i][0];
                    int nx = p.x + horse[i][1];
                    if (ny < 0 || ny >= n2 || nx < 0 || nx >= n1) continue;
                    if (map[ny][nx] == 1) continue;
                    if (visit[p.horseCnt + 1][ny][nx] == 1) continue;
                    visit[p.horseCnt + 1][ny][nx] = 1;
                    que.offer(new Node(ny, nx, p.level + 1, p.horseCnt + 1));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        n1 = Integer.parseInt(st.nextToken());
        n2 = Integer.parseInt(st.nextToken());
        map = new int[n2][n1];
        visit = new int[k + 1][n2][n1];

        for (int y = 0; y < n2; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n1; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(new Node(0, 0, 0, 0)));
    }
}