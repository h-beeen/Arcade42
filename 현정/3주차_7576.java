import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7576 {
    static int[][] tomato;
    static int[][] visit;

    static Queue<Point> queue = new LinkedList<>();

    static int[] dist_y = {1, 0, -1, 0};
    static int[] dist_x = {0, 1, 0, -1};

    static int n;
    static int m;

    static class Point {
        int y;
        int x;
        int level;

        public Point(int y, int x, int level) {
            this.y = y;
            this.x = x;
            this.level = level;
        }
    }

    static int bfs() {
        int day = 0;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            day = p.level;
            for (int i = 0; i < 4; i++) {
                int row = p.y + dist_y[i];
                int col = p.x + dist_x[i];
                if (row < 0 || col < 0 || row >= n || col >= m) continue;
                if (visit[row][col] == 1) continue;
                if (tomato[row][col] == 0){
                    visit[row][col] = 1;
                    queue.offer(new Point(row, col, p.level + 1));
                }
            }
        }
        return day;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        tomato = new int[n][m];
        visit = new int[n][m];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());
                if (tomato[i][j] == 1) {
                    queue.offer(new Point(i, j, 0));
                    visit[i][j] =1;
                }
                if (tomato[i][j] == -1) visit[i][j] = 1;
            }
        }

        int result = bfs();

        for (int i =0;i<n;i++){
            for(int j =0;j<m;j++)
                if (visit[i][j] == 0) {
                    result = -1;
                }
        }


        System.out.println(result);
    }
}
