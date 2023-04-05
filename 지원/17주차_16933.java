import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Point {
        int x;
        int y;
        int broken; // (x, y)까지 갈 동안 벽 부순 개수
        boolean isNight;
        int distance;

        public Point(int x, int y, int broken, boolean isNight, int distance) {
            this.x = x;
            this.y = y;
            this.broken = broken;
            this.isNight = isNight;
            this.distance = distance;
        }
    }

    static int N;
    static int M;
    static int K;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(split[j]);
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Point> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][K + 1]; // 세번째 [] = 벽 몇개 부수고 [][]로 갔는지

        queue.offer(new Point(0, 0, 0, false, 1)); // 현재 위치도 거리에 포함
        visited[0][0][0] = true; // 시작 (0, 0)에서는 벽 안부수고 위치

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int cx = current.x;
            int cy = current.y;

            if (cx == N - 1 && cy == M - 1) {
                return current.distance;
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (!isRange(nx, ny)) {
                    continue;
                }

                if (map[nx][ny] == 0) { // (nx, ny)로 갈 수 있으면
                    if (!visited[nx][ny][current.broken]) { // 아직 방문 안했으면
                        visited[nx][ny][current.broken] = true;
                        queue.offer(new Point(nx, ny, current.broken, !current.isNight, current.distance + 1)); // 갈 수 있으면 그냥 가기
                    }
                } else { // (nx, ny)가 벽이면
                    if (current.broken < K) { // 부술 기회 남았으면
                        if (current.isNight) { // 밤이면
                            queue.offer(new Point(cx, cy, current.broken, false, current.distance + 1)); // (cx, cy)에 가만히 있기
                        } else { // 낮이면
                            if (!visited[nx][ny][current.broken + 1]) { // 아직 방문 안했으면
                                visited[nx][ny][current.broken + 1] = true; // 벽 부수고
                                queue.offer(new Point(nx, ny, current.broken + 1, true, current.distance + 1)); // (nx, ny)로 이동
                            }
                        }
                    }
                }
            }
        }

        return -1;
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}

/*
1 4 1
0010
>> 5
1 4 1
0100
>> 4
6 4 1
0100
1110
1000
0000
0111
0000
>> 15
6 4 2
0100
1110
1000
0000
0111
0000
>> 9
2 4 2
0111
0110
>> 7
3 3 3
011
111
110
>> 7
 */