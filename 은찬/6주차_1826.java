import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static Node[] arr;
    static int goal;
    static int start;
    static class Node{
        int dis; int val;
        public Node(int dis, int val){
            this.dis = dis;
            this.val = val;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new Node[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[i] = new Node(a, b);
        }
        st = new StringTokenizer(br.readLine());
        goal = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());

        Arrays.sort(arr, (a, b) -> Integer.compare(a.dis, b.dis));
        PriorityQueue<Node> que = new PriorityQueue<>((a, b) -> Integer.compare(b.val, a.val));

        int answer = 0;
        int k = start;
        int x = 0;
        while (k < goal) {
            for (; x < n; x++) {
                if (k < arr[x].dis) break;
                que.offer(arr[x]);
            }
            if (que.isEmpty()) break;
            Node p = que.poll();
            k += p.val;
            answer++;
        }

        if (k < goal) answer = -1;
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}