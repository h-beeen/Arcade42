import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;

    static int[][] board;
    static boolean[][] vis;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Node> queue = new LinkedList<>();

    static class Node {
        int x;
        int y;
        int level;

        public Node(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }
    }

    static int bfs() {
        int day = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            day = cur.level;
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (board[nx][ny] == -1 || vis[nx][ny]) continue;
                vis[nx][ny] = true;
                board[nx][ny] = 1;
                queue.add(new Node(nx, ny, cur.level + 1));
            }
            System.out.println("cur : " + "( " + cur.x + " " + cur.y + " )" + ", level: " + cur.level );
        }
        return day;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        vis = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    queue.add(new Node(i, j, 0));
                    vis[i][j] = true;
                }
            }
        }

        int res = bfs();

        for (int i = 0; i < n; i++) {
            for (int j=0;j<m;j++) {
                if (board[i][j] == 0) {
                    res = -1;
                    break;
                }
            }
        }
        System.out.println(res);
    }
}
