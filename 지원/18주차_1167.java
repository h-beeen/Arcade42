import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Node {
        int target;
        int weight;

        public Node(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    static List<List<Node>> graph = new ArrayList<>();
    static boolean[] visited;
    static int maxDistance = Integer.MIN_VALUE;
    static int findVertex; // 1단계에서 도출된 가장 먼 vertex

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            String[] split = br.readLine().split(" ");
            int start = Integer.parseInt(split[0]) - 1;

            for (int j = 1; j < split.length - 1; j += 2) {
                int target = Integer.parseInt(split[j]) - 1;
                int weight = Integer.parseInt(split[j + 1]);

                graph.get(start).add(new Node(target, weight));
            }
        }

        // 1. random vertex로부터 가장 먼 vertex찾기
        visited = new boolean[N];
        visited[0] = true;
        dfs(0, 0);

        // 2. 찾은 vertex로부터 가장 먼 vertex간 거리 측정
        visited = new boolean[N];
        visited[findVertex] = true;
        maxDistance = Integer.MIN_VALUE;
        dfs(findVertex, 0);

        System.out.println(maxDistance);
    }

    private static void dfs(int vertex, int sum) {
        if (sum > maxDistance) {
            maxDistance = sum;
            findVertex = vertex;
        }

        for (Node node : graph.get(vertex)) {
            if (!visited[node.target]) {
                visited[node.target] = true; // 이동하기
                dfs(node.target, sum + node.weight);
                visited[node.target] = false; // rollback
            }
        }
    }
}

/*
5
1 3 2 -1
2 4 4 -1
3 1 2 4 3 -1
4 2 4 3 3 5 6 -1
5 4 6 -1
>> 11
2
1 2 1 -1
2 1 1 -1
>> 1
4
1 2 5 3 9 -1
2 1 5 -1
3 1 9 4 8 -1
4 3 8 -1
>> 22
6
1 2 3 -1
2 1 3 5 3 3 5 -1
3 2 5 4 7 -1
4 3 7 -1
5 2 3 6 5 -1
6 5 5 -1
>> 20
4
1 2 7 3 2 -1
2 1 7 -1
3 1 2 4 3 -1
4 3 3 -1
>> 12
5
1 2 7 3 2 5 10 -1
2 1 7 -1
3 1 2 4 3 -1
4 3 3 -1
5 1 10 -1
>> 17
 */