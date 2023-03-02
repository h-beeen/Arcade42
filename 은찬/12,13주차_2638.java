import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static int total = 0;

    static class Node{
        int y; int x;
        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][m + 1];

        for (int y = 1; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x < m; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 1) total++;
            }
        }

        int answer = 0;
        while (total > 0) {
            bfs();
            answer++;
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }

    static void bfs() {
        Queue<Node> que = new LinkedList<>();
        int[][] visit = new int[n + 1][m + 1];
        visit[0][0] = 1;
        que.offer(new Node(0, 0));
        while (!que.isEmpty()) {
            Node p = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n + 1 || nx >= m + 1) continue;
                if (map[ny][nx] == 1) {
                    visit[ny][nx]++;
                    if (visit[ny][nx] >= 2) {
                        map[ny][nx] = 0;
                        visit[ny][nx] = 1;
                        total--;
                    }
                    continue;
                }
                if (visit[ny][nx] == 1) continue;
                visit[ny][nx] = 1;
                que.offer(new Node(ny, nx));
            }
        }
    }
}