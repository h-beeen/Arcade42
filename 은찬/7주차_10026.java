import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[][] map1;
    static char[][] map2;
    static int[][] visit1;
    static int[][] visit2;
    static int cnt1 = 0;
    static int cnt2 = 0;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static void bfs(int y, int x, char[][] arr, int[][] visit, char now) {
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(y, x));
        visit[y][x] = 1;

        while (!que.isEmpty()) {
            Node p = que.poll();
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                if (visit[ny][nx] == 1) continue;
                if (arr[ny][nx] != now) continue;
                visit[ny][nx] = 1;
                que.offer(new Node(ny, nx));
            }
        }
    }
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
        n = Integer.parseInt(br.readLine());
        map1 = new char[n][n];
        map2 = new char[n][n];
        visit1 = new int[n][n];
        visit2 = new int[n][n];

        for (int y = 0; y < n; y++) {
            String s = br.readLine();
            for (int x = 0; x < n; x++) {
                char ch = s.charAt(x);
                map1[y][x] = ch;
                if (ch == 'G') map2[y][x] = 'R';
                else map2[y][x] = ch;
            }
        }

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (visit1[y][x] == 0) {
                    bfs(y, x, map1, visit1, map1[y][x]);
                    cnt1++;
                }
                if (visit2[y][x] == 0) {
                    bfs(y, x, map2, visit2, map2[y][x]);
                    cnt2++;
                }
            }
        }

        bw.write(cnt1 + " " + cnt2);

        br.close();
        bw.close();
    }
}