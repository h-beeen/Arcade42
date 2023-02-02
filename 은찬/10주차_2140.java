import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[][] map;
    static int totalMine = 0;
    static int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    static class Node{
        int y; int x;
        public Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
    static void findMine(int y, int x) {
        int total = map[y][x]; // 목표 지뢰 개수
        int mineCnt = 0; // 지뢰 개수
        int shCnt = 0;  // 샵 개수
        boolean isZero = false;
        if (total == 0) isZero = true;

        List<Node> list = new ArrayList<>(); // 샵 좌표
        //-1 -> #, -2 -> 지뢰, -3 -> 공백
        for (int i = 0; i < 8; i++) {
            int ny = y + dir[i][0];
            int nx = x + dir[i][1];
            if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
            if (map[ny][nx] >= 0) continue;
            if (isZero) {
                map[ny][nx] = -3;
                continue;
            }
            if (map[ny][nx] == -1) {
                shCnt++;
                list.add(new Node(ny, nx));
            }
            if (map[ny][nx] == -2) mineCnt++;
        }
        if (mineCnt == total) {
            for (Node p : list) {
                map[p.y][p.x] = -3;
            }
        } else if (mineCnt + shCnt == total) {
            for (Node p : list) {
                map[p.y][p.x] = -2;
                totalMine++;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int y = 0; y < n; y++) {
            String str = br.readLine();
            for (int x = 0; x < str.length(); x++) {
                char ch = str.charAt(x);
                int t;
                if (ch == '#') {
                    t = -1;
                } else t = ch - '0';
                map[y][x] = t;
            }
        }

        for (int x = 0; x < n; x++) {
            findMine(0, x);
        }
        for (int y = 1; y < n; y++) {
            findMine(y, 0);
        }
        for (int x = 1; x < n; x++) {
            findMine(n - 1, x);
        }
        for (int y = 1; y < n - 1; y++) {
            findMine(y, n - 1);
        }

        if (n > 4) {
            totalMine += (n - 4) * (n - 4);
        }

        bw.write(String.valueOf(totalMine));

        br.close();
        bw.close();
    }
}