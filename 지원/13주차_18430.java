import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int M;
    static int[][] map;
    static boolean[][] apply;
    static int robbery = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        apply = new boolean[N][M];

        if (N >= 2 && M >= 2) { // 부메랑을 만들 수 있는 경우에만
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            backTracking(0, 0, 0);
        }
        System.out.println(robbery);
    }

    private static void backTracking(int x, int y, int sum) { // column 블록 단위의 (x, y)를 각각 부메랑의 축으로 생성
        // x가 column 레벨에서 전부 사용되었을 경우
        if (x == N) {
            x = 0;
            y++; // 오른쪽으로 column 블록 이동
        }

        // backTracking간에 모든 (x, y) 축을 사용했을 경우
        if (y == M) {
            robbery = Math.max(robbery, sum); // update
            return;
        }

        if (notYetUsed(x, y) && isRange(x, y)) { // (x, y)를 축으로 아직 사용하지 않은 경우
            // 왼쪽 + 위
            if (canUseParts(x, y - 1) && canUseParts(x - 1, y)) {
                apply[x][y] = apply[x][y - 1] = apply[x - 1][y] = true;
                backTracking(x + 1, y, sum + calcLeftUp(x, y));
                apply[x][y] = apply[x][y - 1] = apply[x - 1][y] = false;
            }

            // 왼쪽 + 아래
            if (canUseParts(x, y - 1) && canUseParts(x + 1, y)) {
                apply[x][y] = apply[x][y - 1] = apply[x + 1][y] = true;
                backTracking(x + 1, y, sum + calcLeftDown(x, y));
                apply[x][y] = apply[x][y - 1] = apply[x + 1][y] = false;
            }

            // 오른쪽 + 위
            if (canUseParts(x, y + 1) && canUseParts(x - 1, y)) {
                apply[x][y] = apply[x][y + 1] = apply[x - 1][y] = true;
                backTracking(x + 1, y, sum + calcRightUp(x, y));
                apply[x][y] = apply[x][y + 1] = apply[x - 1][y] = false;
            }

            // 오른쪽 + 아래
            if (canUseParts(x, y + 1) && canUseParts(x + 1, y)) {
                apply[x][y] = apply[x][y + 1] = apply[x + 1][y] = true;
                backTracking(x + 1, y, sum + calcRightDown(x, y));
                apply[x][y] = apply[x][y + 1] = apply[x + 1][y] = false;
            }
        }
        backTracking(x + 1, y, sum); // (x, y)를 축으로 사용할 수 없는 경우 column 레벨에서 다음 축으로 이동
    }

    private static boolean canUseParts(int x, int y) {
        return isRange(x, y) && notYetUsed(x, y);
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private static boolean notYetUsed(int x, int y) {
        return !apply[x][y];
    }

    private static int calcLeftUp(int x, int y) {
        return map[x][y - 1] + 2 * map[x][y] + map[x - 1][y];
    }

    private static int calcLeftDown(int x, int y) {
        return map[x][y - 1] + 2 * map[x][y] + map[x + 1][y];
    }

    private static int calcRightUp(int x, int y) {
        return map[x][y + 1] + 2 * map[x][y] + map[x - 1][y];
    }

    private static int calcRightDown(int x, int y) {
        return map[x][y + 1] + 2 * map[x][y] + map[x + 1][y];
    }
}