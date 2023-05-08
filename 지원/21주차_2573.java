// https://www.acmicpc.net/problem/2573

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
    static boolean[][] groupVisited; // 빙산 덩어리 찾을 때 visited
    static boolean[][] meltVisited; // 빙산 녹일 때 visited
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int year = 0; // 덩어리 분리까지 걸리는 시간

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int seperateCount; // 분리된 빙하 개수
        while (true) {
            seperateCount = getSeperateCount();

            if (seperateCount >= 2) { // 분리되면
                break;
            } else if (seperateCount == 0) { // 끝까지 분리안되면
                year = 0; // 0년
                break;
            }

            melt(); // 빙산 녹이기
            year++;
        }

        System.out.println(year);
    }

    private static int getSeperateCount() {
        groupVisited = new boolean[N][M];
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!groupVisited[i][j] && map[i][j] != 0) { // 방문 X + 빙산
                    dfs(i, j, groupVisited); // 빙산 덩어리 도출
                    count++; // 덩어리 카운트 + 1
                }
            }
        }

        return count;
    }

    private static void dfs(int x, int y, boolean[][] groupVisited) {
        groupVisited[x][y] = true;

        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isRange(nx, ny) && !groupVisited[nx][ny] && map[nx][ny] != 0) {
                dfs(nx, ny, groupVisited);
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    private static void melt() {
        Queue<Point> queue = new LinkedList<>();
        meltVisited = new boolean[N][M];

        // 빙산 전부 queue에 넣기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    queue.offer(new Point(i, j));
                    meltVisited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int water = 0; // current 상하좌우 중 어느곳에 바닷물 있는지

            for (int i = 0; i < dx.length; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (isRange(nx, ny) && !meltVisited[nx][ny] && map[nx][ny] == 0) {
                    water++;
                }
            }

            if (map[current.x][current.y] < water) {
                map[current.x][current.y] = 0;
            } else {
                map[current.x][current.y] -= water;
            }
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

/*
5 7
0 0 0 0 0 0 0
0 2 4 5 3 0 0
0 3 0 2 5 2 0
0 7 6 2 4 0 0
0 0 0 0 0 0 0
>> 2
 */
