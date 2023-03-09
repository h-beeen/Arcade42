import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1926 {
    static int[][] array;
    static int[][] visit;
    static int[] dist_x = {1, 0, -1, 0};
    static int[] dist_y = {0, 1, 0, -1};
    static int n;
    static int m;
    static int answer = 0;
    static class Point{
        int x; int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int bfs(Point start) {
        //방문 배열에 현재 위치 1로 값 변경
        visit[start.x][start.y] = 1;
        Queue<Point> que = new LinkedList<>();
        // 큐에 현재 위치 푸시
        que.offer(start);
        // 그림 길이 0으로 초기화
        int cnt = 0;

        // 큐 비워질 때까지 bfs 탐색
        while (!que.isEmpty()) {
            Point p = que.poll();
            // 큐에서 꺼낼때 ++
            cnt++;
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dist_x[i];
                int ny = p.y + dist_y[i];
                // 상하좌우 탐색 조건
                // 1. index 범위 초과하는지 확인
                if (ny < 0 || nx < 0 || ny >= m || nx >= n) continue;
                // 2. 이미 방문한 노드인지 확인
                if (visit[nx][ny] == 1) continue;
                // 3. 0인 노드 방문 X
                if (array[nx][ny] == 0) continue;
                // 방문할 노드들 1로 변경
                visit[nx][ny] = 1;
                // 방문할 노드 큐에 푸시
                que.offer(new Point(nx, ny));

            }
        }
        return cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        //n, m 값 입
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        array = new int[n][m];
        visit = new int[n][m];

        // 배열 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int pic = 0;

        //0,0에서부터 탐색
        for (int x= 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                // 현재 1이면 bfs 탐색
                if (array[x][y] == 1) {
                    // 이미 방문했던 배열이면 bfs실행 X
                    if (visit[x][y] == 1) continue;
                    //bfs 실행하면 그림 개수 세기
                    pic++;
                    // 그림 너비 비교
                    int result = bfs(new Point(x, y));
                    answer = Math.max(answer, result);
                }
            }
        }
        System.out.println(pic);
        System.out.println(answer);
    }
}
