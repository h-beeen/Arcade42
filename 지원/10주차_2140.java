import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 백준 2140번 지뢰 찾기 : https://www.acmicpc.net/problem/2140

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static final String QUESTION_MARK = "#";
    static final int MINE = 9999;
    static final int NO_MINE = 10001;
    static int N;
    static int[][] map;
    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        if (N < 3) { // N < 3이면 애초에 지뢰를 설치할 수 없다
            System.out.println(0);
        } else {
            map = new int[N][N];

            for (int i = 0; i < N; i++) {
                String[] line = br.readLine().split("");
                for (int j = 0; j < N; j++) {
                    String value = line[j];
                    map[i][j] = value.equals(QUESTION_MARK) ? MINE : Integer.parseInt(value); // "#" -> 전부 지뢰로 치환
                }
            }

            for (int cx = 1; cx < N - 1; cx++) {
                for (int cy = 1; cy < N - 1; cy++) {
                    if (isDirectlyAttachedToBorder(cx, cy)) {
                        process(cx, cy);
                    }
                }
            }
            System.out.println(getResult());
        }
    }

    private static boolean isDirectlyAttachedToBorder(int x, int y) { // (x, y)가 직접적으로 테두리와 붙어있는지 여부
        return x == 1 || x == N - 2 || y == 1 || y == N - 2;
    }

    private static void process(int cx, int cy) {
        boolean flag = true; // 값이 0인 테두리가 존재하면 false
        List<Point> list = new ArrayList<>(); // flag가 true면 list에 존재하는 Point들의 값 -1

        for (int i = 0; i < dx.length; i++) {
            int nx = cx + dx[i];
            int ny = cy + dy[i];

            if (map[nx][ny] != MINE && map[nx][ny] != NO_MINE) {
                if (map[nx][ny] == 0) {
                    flag = false;
                    break;
                } else {
                    list.add(new Point(nx, ny));
                }
            }
        }

        if (flag) {
            applyMinusOne(list);
        } else { // 값이 0인 테두리가 존재하면 (cx, cy)에는 지뢰가 없어야 한다
            map[cx][cy] = NO_MINE;
        }
    }

    private static void applyMinusOne(List<Point> list) {
        for (Point point : list) {
            map[point.x][point.y] -= 1;
        }
    }

    private static int getResult() {
        int result = 0;

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < N - 1; j++) {
                if (map[i][j] == MINE) {
                    result++;
                }
            }
        }

        return result;
    }
}

/*
5
11100
2###1
3###1
2###1
12210
>> 6
 */