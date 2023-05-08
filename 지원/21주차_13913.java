// https://www.acmicpc.net/problem/13913

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int K;
    static int[] visited = new int[100001]; // 방문 배열
    static int[] parent = new int[100001]; // (x)에 도달했을 경우 (x) 이전에 머물렀던 좌표 (p) -> parent[x] = p
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        go(); // 출발하기
        sb.append(visited[K] - 1).append("\n"); // 최단 시간

        int index = K; // K의 parent -> K의 parent의 parent -> ... -> N이 될때까지
        List<Integer> list = new ArrayList<>();
        while (index != N) {
            list.add(parent[index]);
            index = parent[index];
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            sb.append(list.get(i)).append(" ");
        }
        sb.append(K);

        System.out.println(sb);
    }

    private static void go() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        visited[N] = 1; // 가지 않은곳과 비교하기 위해서 초기값을 0이 아닌 1로

        while (!queue.isEmpty()) {
            int current = queue.poll();

            if (current == K) { // K에 도달했으면
                return;
            }

            for (int i = 0; i < 3; i++) {
                int next;

                if (i == 0) { // + 1
                    next = current + 1;
                } else if (i == 1) { // - 1
                    next = current - 1;
                } else { // * 2
                    next = current * 2;
                }

                if (next < 0 || next > 100000) { // 범위 벗어나면
                    continue;
                }

                if (visited[next] == 0) { // 처음 도달한 곳이면
                    queue.offer(next);
                    visited[next] = visited[current] + 1;
                    parent[next] = current; // next가기 전인 current 좌표를 parent에 적용
                }
            }
        }
    }
}

/*
5 17
>>
4
5 10 9 18 17

5 17
>>
4
5 4 8 16 17
 */
