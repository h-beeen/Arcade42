import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 백준 1504번 특정한 최단 경로 : https://www.acmicpc.net/problem/1504
public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Node {
        int point;
        int weight;

        public Node(int point, int weight) {
            this.point = point;
            this.weight = weight;
        }
    }

    static int N;
    static int E;
    static int v1;
    static int v2;
    static final int MAX_DISTANCE = 987654321;
    static int[] distance;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a - 1).add(new Node(b - 1, c));
            graph.get(b - 1).add(new Node(a - 1, c));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken()) - 1;
        v2 = Integer.parseInt(st.nextToken()) - 1;

        // 1 -> v1 -> v2 -> N
        long result1 = 0;
        result1 += dijkstra(0, v1);
        result1 += dijkstra(v1, v2);
        result1 += dijkstra(v2, N - 1); // 0번 노드부터 시작

        // 1 -> v2 -> v1 -> N
        long result2 = 0;
        result2 += dijkstra(0, v2);
        result2 += dijkstra(v2, v1);
        result2 += dijkstra(v1, N - 1); // 0번 노드부터 시작

        if (result1 >= MAX_DISTANCE && result2 >= MAX_DISTANCE) { // 경유해서 갈 수 없는 경우
            System.out.println("-1");
        } else {
            System.out.println(Math.min(result1, result2));
        }
    }

    private static int dijkstra(int start, int end) {
        resetDistance();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight)); // Grouping Distance ASC
        distance[start] = 0;
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            List<Node> neighbors = graph.get(current.point);
            for (Node neighbor : neighbors) {
                if (distance[neighbor.point] > distance[current.point] + neighbor.weight) { // current 경유해서 neighbor에 도달하는 경로가 더 짧을 경우
                    distance[neighbor.point] = distance[current.point] + neighbor.weight; // 짧은 경유지로 갱신
                    queue.offer(new Node(neighbor.point, distance[neighbor.point]));
                }
            }
        }

        return distance[end];
    }

    private static void resetDistance() {
        distance = new int[N];
        Arrays.fill(distance, MAX_DISTANCE);
    }
}

/*
4 6
1 2 3
2 3 3
3 4 1
1 3 5
2 4 5
1 4 4
2 3
>> 7
 */