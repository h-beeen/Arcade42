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
        // 큐가 빌 떄까지 반복
        while (!queue.isEmpty()) {
            //큐에서 front 가져오기
            Point p = queue.poll();
            day = p.level;
            //상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int row = p.y + dist_y[i];
                int col = p.x + dist_x[i];
                //예외처리
                if (row < 0 || col < 0 || row >= n || col >= m) continue;
                if (visit[row][col] == 1) continue;
                //익지않은 토마토 발견 시 방문처리, 큐에 추가
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

        //입력
        for (int i = 0; i < n; i++) {
            //" "을 토큰으로 사용하여 배열에 저장
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());
                //익은 토마토 위치 큐에 저장 & 방문처리
                if (tomato[i][j] == 1) {
                    queue.offer(new Point(i, j, 0));
                    visit[i][j] =1;
                }
                //-1은 빈칸이므로 방문불가 -> 방문처리
                if (tomato[i][j] == -1) visit[i][j] = 1;
            }
        }

        int result = bfs();

        // visit에 0이 남아있으면 익지 않은 토마토 존재 검사
        for (int i =0;i<n;i++){
            for(int j =0;j<m;j++)
                if (visit[i][j] == 0) {
                    result = -1;
                }
        }


        System.out.println(result);
    }
}
