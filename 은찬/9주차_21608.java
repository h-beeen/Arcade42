import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Node[][] map;
    static Data[] d;
    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static class Node{
        int y; int x; int cnt; int other; int val; Set<Integer> set;
        public Node(int y, int x, int cnt, int val){
            this.y = y;
            this.x = x;
            this.cnt = cnt;
            this.other = 0;
            this.val = val;
        }
    }
    static class Data{
        int k; Set<Integer> set = new HashSet<>();
        public Data(int k){
            this.k = k;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        d = new Data[n * n];
        map = new Node[n][n];
        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                int k = Integer.parseInt(st.nextToken());
                if (j == 0) {
                    d[i] = new Data(k);
                } else{
                    d[i].set.add(k);
                }
            }
        }

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                map[y][x] = new Node(y, x, 0, 0);
            }
        }

        for (int i = 0; i < n * n; i++) {
            PriorityQueue<Node> que = new PriorityQueue<>((a, b) -> {
                if (a.cnt == b.cnt) {
                    if (a.other == b.other) {
                        if (a.y == b.y) {
                            return Integer.compare(a.x, b.x);
                        } else return Integer.compare(a.y, b.y);
                    } else return Integer.compare(b.other, a.other);
                } else return Integer.compare(b.cnt, a.cnt);
            });
            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    if (map[y][x].val != 0) continue;
                    Node node = new Node(y, x, 0, d[i].k);
                    for (int j = 0; j < 4; j++) {
                        int ny = y + dir[j][0];
                        int nx = x + dir[j][1];
                        if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                        if (d[i].set.contains(map[ny][nx].val)) {
                            node.cnt++;
                        }
                        if (map[ny][nx].val == 0) node.other++;
                    }
                    que.offer(node);
                }
            }
            Node p = que.poll();
            map[p.y][p.x] = p;
            map[p.y][p.x].set = d[i].set;
        }

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                int cnt = 0;
                for (int i = 0; i < 4; i++) {
                    int ny = y + dir[i][0];
                    int nx = x + dir[i][1];
                    if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                    if (map[y][x].set.contains(map[ny][nx].val)) {
                        cnt++;
                    }
                }
                map[y][x].cnt = cnt;
            }
        }

        int answer = 0;
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                if (map[y][x].cnt == 0) {
                    answer += 0;
                }
                if (map[y][x].cnt == 1) {
                    answer += 1;
                }
                if (map[y][x].cnt == 2) {
                    answer += 10;
                }
                if (map[y][x].cnt == 3) {
                    answer += 100;
                }
                if (map[y][x].cnt == 4) {
                    answer += 1000;
                }
            }
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}