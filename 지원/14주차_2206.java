import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int M;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean[][][] visited;

    static class Point {
        int x;
        int y;
        int distance; // (x, y)까지 몇칸으로 이동했는지
        int brokenCount; // (x, y)까지 이동하는 동안 벽을 부순적이 있는지 없는지 (없으면 0 / 있으면 1)

        public Point(int x, int y, int distance, int brokenCount) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.brokenCount = brokenCount;
        }
    }

    static int result = -1;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2]; // 0 = (x, y)까지 벽 안부수고 감 / 1 = (x, y)까지 벽 부수고 감

        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        // 출발지 == 목적지
        if (N == 1 && M == 1) {
            System.out.println(1);
        } else {
            bfs();
            System.out.println(result);
        }
    }

    private static void bfs() {
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 1, 0)); // 시작하는 칸도 포함해서 distance count
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (isDestination(current)) {
                result = current.distance;
                return;
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (isRange(nx, ny) && !visited[nx][ny][current.brokenCount]) { // 범위 안에 포함 + 방문한적 없을 때
                    if (isWall(nx, ny)) { // (nx, ny)가 벽일 경우
                        if (current.brokenCount == 0) { // (cx, cy)까지 가는 도중에 벽 부순적 없으면
                            visited[nx][ny][current.brokenCount] = true;
                            queue.offer(new Point(nx, ny, current.distance + 1, current.brokenCount + 1)); // 벽 부수고 이동
                        }
                    } else { // (nx, ny)가 벽이 아닐 경우
                        visited[nx][ny][current.brokenCount] = true;
                        queue.offer(new Point(nx, ny, current.distance + 1, current.brokenCount));
                    }
                }
            }
        }
    }

    private static boolean isDestination(Point point) {
        return point.x == N - 1 && point.y == M - 1;
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private static boolean isWall(int x, int y) {
        return map[x][y] == 1;
    }
}