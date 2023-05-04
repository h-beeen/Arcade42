import java.io.*;
import java.util.*;

public class Main {
    static List<List<Integer>> list = new ArrayList<>();
    static Queue<Integer> que = new LinkedList<>();
    static int[] visit;
    static int bfs(int start, int not){
        int cnt = 1;
        que.offer(start);
        visit[start] = 1;

        while(!que.isEmpty()){
            int p = que.poll();
            for (int i=0; i < list.get(p).size(); i++){
                if (visit[list.get(p).get(i)] != 1 && list.get(p).get(i) != not){
                    visit[list.get(p).get(i)] = 1;
                    que.offer(list.get(p).get(i));
                    cnt++;
                }
            }
        }
        return cnt;
    }
    static int solution(int n, int[][] wires) {
        int answer = -1;

        for (int i=0; i <= n; i++){
            list.add(new ArrayList<>());
        }
        for (int i=0; i < n - 1; i++){
            list.get(wires[i][0]).add(wires[i][1]);
            list.get(wires[i][1]).add(wires[i][0]);
        }

        int min = Integer.MAX_VALUE;
        for (int y=0; y < n - 1; y++){
            visit = new int[n + 1];
            int cnt1 = bfs(wires[y][0], wires[y][1]);
            int cnt2 = bfs(wires[y][1], wires[y][0]);
            min = Math.min(min, Math.abs(cnt1 - cnt2));
        }

        return min;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] wires = new int[n-1][2];
        for (int i = 0; i < n - 1; i++) {
            wires[i][0] = sc.nextInt();
            wires[i][1] = sc.nextInt();
        }

        int solution = solution(n, wires);
        System.out.println(solution);
    }
}