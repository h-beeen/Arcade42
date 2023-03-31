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
        int value;

        public Point(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }

    static int N;
    static int M;
    static String[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new String[N][M];

        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split("");
            for (int j = 0; j < M; j++) {
                map[i][j] = split[j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].equals("L")) {
                    int distance = bfs(i, j);
                    result = Math.max(result, distance); // 최종 결과값 갱신
                }
            }
        }

        System.out.println(result);
    }

    private static int bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        visited = new boolean[N][M];

        queue.offer(new Point(x, y, 0));
        visited[x][y] = true;

        int len = 0; // (x, y)부터 보물까지 최장 거리
        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (isRange(nx, ny) && !visited[nx][ny] && map[nx][ny].equals("L")) {
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny, current.value + 1)); // 다음 노드 이동
                    len = Math.max(len, current.value + 1); // 최장거리 비교 & 갱신
                }
            }
        }

        return len;
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}

/*
5 7
WLLWWWL
LLLWLLL
LWLWLWW
LWLWLLL
WLLWLWW
>> 8
 */