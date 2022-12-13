import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int big;
    static int small;
    static boolean addCnt;
    static int[][] map;
    static int[][] visit;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static Queue<Point> que = new LinkedList<>();
    static class Point{
        int y; int x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        big = Integer.parseInt(st.nextToken());
        small = Integer.parseInt(st.nextToken());

        for (int y=0; y < n; y++){
            st = new StringTokenizer(br.readLine());
            for (int x=0; x< n; x++){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        boolean keep = true;
        while (keep) {
            addCnt = false;
            visit = new int[n][n];
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (visit[y][x] != 1) {
                        bfs(y, x);
                    }
                }
            }
            if (addCnt) cnt++;
            else keep = false;
        }

        bw.write(String.valueOf(cnt));

        br.close();
        bw.close();
    }

    static void bfs(int dy, int dx){
        que.offer(new Point(dy, dx));
        visit[dy][dx] = 1;
        int sum = 0;
        List<Point> list = new ArrayList<>();
        while(!que.isEmpty()){
            Point p = que.poll();
            sum += map[p.y][p.x];
            list.add(p);
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || ny >= n || nx < 0 || nx >= n) continue;
                if (visit[ny][nx] == 1) continue;
                int gap = Math.abs(map[p.y][p.x] - map[ny][nx]);
                if (gap < big || gap > small) continue;
                visit[ny][nx] = 1;
                que.offer(new Point(ny, nx));
            }
        }
        int avg = sum / list.size();
        if (list.size() > 1) addCnt = true;
        for(Point p : list){
            map[p.y][p.x] = avg;
        }
    }
}