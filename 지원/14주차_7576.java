import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Retry1 {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final int RIPE = 1;
    static final int NON_RIPE = 0;
    static int Y;
    static int X;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static Queue<Point> tomato = new LinkedList<>(); // 익은 토마토 담는 상자

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        map = new int[X][Y];

        for (int i = 0; i < X; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < Y; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == RIPE) {
                    tomato.offer(new Point(i, j));
                }
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        while (!tomato.isEmpty()) {
            Point current = tomato.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (isRange(nx, ny) && map[nx][ny] == NON_RIPE) { // 범위 안에 존재 + 익지 않은 토마토
                    map[nx][ny] = map[current.x][current.y] + 1;
                    tomato.offer(new Point(nx, ny));
                }
            }
        }

        if (containsNonRipe()) {
            return -1;
        } else {
            int result = getMax();
            return result == 1 ? 0 : result - 1;
        }
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < X && 0 <= y && y < Y;
    }

    private static boolean containsNonRipe() {
        for (int[] row : map) {
            for (int value : row) {
                if (value == NON_RIPE) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int getMax() {
        int result = Integer.MIN_VALUE;

        for (int[] row : map) {
            for (int value : row) {
                result = Math.max(result, value);
            }
        }

        return result;
    }
}