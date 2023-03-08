import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[][] board;
    static boolean[][] vis;
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int n, m, largest;

    static void bfs(Point start){
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        vis[start.x][start.y] = true;

        int count = 0;
        while(!queue.isEmpty()) {
            Point cur = queue.poll();
            for(int dir=0;dir<4;dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 0 || nx >= n || ny <0 || ny >= m) continue;
                if (vis[nx][ny] || board[nx][ny] != 1) continue;
                vis[nx][ny] = true;
                queue.add(new Point(nx, ny));
            }
            count++;
        }
        if (largest < count) largest = count;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        vis = new boolean[n][m];

        for (int i=0;i<n;i++) {
            String row = br.readLine();
            st = new StringTokenizer(row);
            for (int j=0;j<m;j++) {
                int cell = Integer.parseInt(st.nextToken());
                board[i][j] = cell;
            }
        }

        int picCount = 0;
        for(int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (board[i][j] == 1 && !vis[i][j]) {
                    bfs(new Point(i, j));
                    picCount++;
                }
            }
        }
        System.out.println(picCount);
        System.out.println(largest);
    }
}
