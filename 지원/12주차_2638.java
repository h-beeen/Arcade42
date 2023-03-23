import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int M;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int totalCheeseCount = 0;
    static int[][] map;
    static boolean[][] visited;
    final static int EXTERNAL_AIR = 100;
    final static int CHEEZE = 1;
    static int time = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == CHEEZE) {
                    totalCheeseCount++;
                }
            }
        }

        while (totalCheeseCount != 0) {
            visited = new boolean[N][M];
            applyExternalAir(0, 0);
            meltingCheese();
            time++;
        }

        System.out.println(time);
    }

    private static void applyExternalAir(int x, int y) {
        visited[x][y] = true;
        map[x][y] = EXTERNAL_AIR;

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isRange(nx, ny) && !visited[nx][ny] && map[nx][ny] != CHEEZE) {
                applyExternalAir(nx, ny);
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private static void meltingCheese() {
        for (int cx = 0; cx < map.length; cx++) {
            for (int cy = 0; cy < map[cx].length; cy++) {
                if (map[cx][cy] != CHEEZE || !isRange(cx, cy)) {
                    continue;
                }

                int contactNumber = 0; // 외부 공기와 접촉한 면 개수
                for (int i = 0; i < dx.length; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];

                    // 치즈 위치에서 상하좌우는 map을 벗어날 일이 없단
                    if (map[nx][ny] == EXTERNAL_AIR) {
                        contactNumber++;
                    }
                }

                if (contactNumber >= 2) { // 외부와 2면 이상 접속했다면
                    map[cx][cy] = 0; // 치즈 없애기
                    totalCheeseCount--;
                }
            }
        }
    }
}