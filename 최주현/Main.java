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
    static int day = 0;

    static void bfs(Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        vis[start.x][start.y] = true;
        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            for(int dir=0;dir<4;dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(vis[nx][ny] || board[nx][ny] == -1) continue;
                vis[nx][ny] = true;
                board[nx][ny] = 1;
                queue.add(new Point(nx, ny));
            }
            System.out.println("cur : " + "( " + cur.x + " " + cur.y + " )" + ", day: " + day );
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        vis = new boolean[n][m];

        for (int i=0;i<n;i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0;j<m;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if (board[i][j] == 1 && !vis[i][j]) {
                    bfs(new Point(i, j));
                }
            }
        }

        System.out.println(day);
    }
}
