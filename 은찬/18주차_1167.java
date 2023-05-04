import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static List<List<Node>> map = new ArrayList<>();

    static class Node {
        int e; int cost;
        public Node(int e, int cost) {
            this.e = e;
            this.cost = cost;
        }
    }

    static int[] visit;
    static int max = -999;
    static int maxNode;
    static void dfs(int now, int sum) {
        if (sum > max) {
            max = sum;
            maxNode = now;
        }

        List<Node> list = map.get(now);
        for (Node next : list) {
            if (visit[next.e] == 1) continue;
            visit[next.e] = 1;
            dfs(next.e, sum + next.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int t = 0;
            int end = 0;
            while (true) {
                int next = Integer.parseInt(st.nextToken());
                if (next == -1) break;
                if (t % 2 == 0) {
                    end = next;
                }
                if (t % 2 == 1) {
                    map.get(start).add(new Node(end, next));
                }
                t++;
            }
        }

        for (int i = 0; i <= n; i++) {
            if (map.get(i).size() == 0) continue;
            visit = new int[n + 1];
            visit[i] = 1;
            dfs(i, 0);
            break;
        }

        visit = new int[n + 1];
        visit[maxNode] = 1;
        dfs(maxNode, 0);

        bw.write(String.valueOf(max));

        br.close();
        bw.close();
    }