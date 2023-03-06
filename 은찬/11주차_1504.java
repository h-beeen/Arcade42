import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static List<List<Node>> list = new ArrayList<>();
    static int[] arr;
    static int[] dis;

    static class Node{
        int k; int val;
        public Node(int k, int val) {
            this.k = k;
            this.val = val;
        }
    }
    static int find(int a) {
        if (arr[a] == a) return a;
        else return arr[a] = find(arr[a]);
    }
    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) arr[fa] = fb;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = i;
        }
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            union(a, b);
            list.get(a).add(new Node(b, c));
            list.get(b).add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int answer = 0;
        if (find(a) != find(b) || find(a) != find(n) || find(b) != find(n)) {
            answer = -1;
        } else {
            int dis1 = getMinDis(1, a);
            dis1 += getMinDis(a, b);
            dis1 += getMinDis(b, n);

            int dis2 = getMinDis(1, b);
            dis2 += getMinDis(b, a);
            dis2 += getMinDis(a, n);

            answer = Math.min(dis1, dis2);
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
    static int getMinDis(int start, int end) {
        dis = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dis[i] = Integer.MAX_VALUE;
        }
        dis[start] = 0;
        PriorityQueue<Node> que = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        que.offer(new Node(start, 0));
        int[] visit = new int[n + 1];
        while (!que.isEmpty()) {
            Node now = que.poll();
            visit[now.k] = 1;
            for (Node next : list.get(now.k)) {
                if (visit[next.k] == 1) continue;
                int sum = now.val + next.val;
                if (sum < dis[next.k]) {
                    dis[next.k] = sum;
                    que.offer(new Node(next.k, sum));
                }
            }
        }
        return dis[end];
    }
}
