import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static int total = 0;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    static class Node{
        int y; int x; int level;
        public Node(int y, int x, int level){
            this.y = y;
            this.x = x;
            this.level = level;
        }
    }
    static List<Node> list;
    static List<Node> comb = new ArrayList<>();
    static int[][][][] dist;
    static int[] combVisit;
    static int minDis;

    static void runComb(int level, Node startNode) {
        if (level == list.size()) {
            minDis = Math.min(minDis, getMinDis(startNode));
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            if (combVisit[i] == 1) continue;
            comb.add(list.get(i));
            combVisit[i] = 1;
            runComb(level + 1, startNode);
            combVisit[i] = 0;
            comb.remove(comb.size() - 1);
        }
    }

    static int getMinDis(Node startNode) {
        int sum = 0;
        if (dist[startNode.y][startNode.x][comb.get(0).y][comb.get(0).x] == 0) {
            dist[startNode.y][startNode.x][comb.get(0).y][comb.get(0).x] = bfs(startNode, comb.get(0));
        }
        sum += dist[startNode.y][startNode.x][comb.get(0).y][comb.get(0).x];
        for (int i = 0; i < comb.size() - 1; i++) {
            if (dist[comb.get(i).y][comb.get(i).x][comb.get(i + 1).y][comb.get(i + 1).x] == 0) {
                dist[comb.get(i).y][comb.get(i).x][comb.get(i + 1).y][comb.get(i + 1).x] = bfs(comb.get(i), comb.get(i + 1));
            }
            sum += dist[comb.get(i).y][comb.get(i).x][comb.get(i + 1).y][comb.get(i + 1).x];
        }
        return sum;
    }

    static int bfs(Node start, Node goal) {
        int[][] visit = new int[n][m];
        visit[start.y][start.x] = 1;
        Queue<Node> que = new LinkedList<>();
        que.offer(start);

        while (!que.isEmpty()) {
            Node p = que.poll();
            if (p.y == goal.y && p.x == goal.x) return p.level;
            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= m) continue;
                if (visit[ny][nx] == 1) continue;
                if (map[ny][nx] == -1) continue;
                visit[ny][nx] = 1;
                que.offer(new Node(ny, nx, p.level + 1));
            }
        }
        return 999999;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 0 -> ., 1 -> *, -1 -> x
        while (true) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            if (n == 0 && m == 0) break;
            map = new int[n][m];
            list = new ArrayList<>();
            dist = new int[n][m][n][m];
            minDis = 999999;
            Node startNode = null;
            for (int y = 0; y < n; y++) {
                String str = br.readLine();
                for (int x = 0; x < m; x++) {
                    char ch = str.charAt(x);
                    int k = -1;
                    if (ch == '.') k = 0;
                    else if (ch == 'o') {
                        k = 2;
                        startNode = new Node(y, x, 0);
                    }
                    else if (ch == '*') {
                        k = 1;
                        total++;
                        list.add(new Node(y, x, 0));
                    }
                    map[y][x] = k;
                }
            }
            combVisit = new int[list.size()];
            runComb(0, startNode);
            if (minDis == 999999) minDis = -1;
            bw.write(String.valueOf(minDis));
            bw.newLine();
        }

        br.close();
        bw.close();
    }
}