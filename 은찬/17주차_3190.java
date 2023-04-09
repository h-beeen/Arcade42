import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int[][] apple;
    static int[][] visit;

    //방향 변환 정보를 담는 class
    static class Direction {
        int time; char d;
        public Direction(int time, char d) {
            this.time = time;
            this.d = d;
        }
    }

    //좌표 정보를 담는 class
    static class Point {
        int y; int x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    //방문 정보와 방문 했을 때의 time 정보를 담는 class
    static class Visit {
        boolean isVisit; int time;
        public Visit(boolean isVisit, int time) {
            this.isVisit = isVisit;
            this.time = time;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        apple = new int[n][n];
        Visit[][] visit = new Visit[n][n];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                visit[y][x] = new Visit(false, 0);
            }
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            apple[y - 1][x - 1] = 1;
        }

        //방향정보가 시간순으로 들어오므로 큐를 사용
        Queue<Direction> que = new LinkedList<>();
        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            que.offer(new Direction(k, dir));
        }

        int time = 0;
        int[][] dir = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
        int dirIdx = 2;
        Point head = new Point(0, 0);
        Point tail = new Point(0, 0);
        visit[0][0] = new Visit(true, 0);
        while (true) {
            //현재 시간과 방향정보의 시간이 일치하면 방향 고체 후 poll 진행
            if (!que.isEmpty()) {
                Direction p = que.peek();
                if (p.time == time) {
                    if (p.d == 'L') dirIdx--;
                    if (p.d == 'D') dirIdx++;
                    if (dirIdx < 0) dirIdx = 3;
                    if (dirIdx > 3) dirIdx = 0;
                    que.poll();
                }
            }
            time++; //시작하자마자 시간이 지남
            //정해진 방향으로 다음 좌표를 정함
            int ny = head.y + dir[dirIdx][0];
            int nx = head.x  +dir[dirIdx][1];
            //아래 두 조건이 종료 조건
            if (ny < 0 || nx < 0 || ny >= n || nx >= n) break;
            if (visit[ny][nx].isVisit) break;
            //visit 배열에 방문했을 때 시간까지 등록 <- 다음 꼬리를 정할때 필요
            visit[ny][nx].isVisit = true;
            visit[ny][nx].time = time;
            //사과를 먹으면 사과를 없애고 꼬리는 갱신하지 않음
            if (apple[ny][nx] == 1) {
                apple[ny][nx] = 0;
            } else {
                //사과가 없으면 기존 꼬리를 없애고 새로운 꼬리로 갱신
                visit[tail.y][tail.x].isVisit = false;
                for (int i = 0; i < 4; i++) {
                    int y = tail.y + dir[i][0];
                    int x = tail.x + dir[i][1];
                    if (y < 0 || x < 0 || y >= n || x >= n) continue;
                    if (!visit[y][x].isVisit) continue;
                    //상하좌우에서 방문 한 곳중에 현재 꼬리의 방문 배열의 time 보다 1 큰 곳이 현재 꼬리 다음으로 움직인곳 이므로 그곳으로 갱신
                    if (visit[y][x].time == visit[tail.y][tail.x].time + 1) {
                        tail.y = y;
                        tail.x = x;
                        break;
                    }
                }
            }
            //head 를 다음 좌표로 갱신
            head.y = ny;
            head.x = nx;
        }

        bw.write(String.valueOf(time));

        br.close();
        bw.close();
    }
}
