import java.util.*;

class Solution {
    static int[][] graph; // 그래프
    final static int CONNECT = 1;
    final static int NON_CONNECT = 0;
    static Queue<Integer> queue;
    static boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        int result = n;
        graph = new int[n][n];
        
        for(int i=0; i<wires.length; i++){
            int a = wires[i][0] - 1;
            int b = wires[i][1] - 1;
            
            graph[a][b] = graph[b][a] = CONNECT; // 연결
        }
        
        for(int i=0; i<wires.length; i++){
            int a = wires[i][0] - 1;
            int b = wires[i][1] - 1;
            
            // (a, b) = (b, a) 전선 끊어보기
            graph[a][b] = graph[b][a] = NON_CONNECT;
            
            // 끊은채로 bfs
            result = Math.min(result, bfs(n, a));
            
            // 다시 연결
            graph[a][b] = graph[b][a] = CONNECT;
        }
        
        return result;
    }
    
    private int bfs(int n, int start) {
        queue = new LinkedList<>();
        visited = new boolean[graph.length];
        queue.offer(start);
        visited[start] = true;
        
        int count = 1; // 연결된 송전탑 개수
        
        while(!queue.isEmpty()) {
            int point = queue.poll();
            
            for(int i=0; i<graph.length; i++) { // point와 연결된 점들
                if(graph[point][i] == CONNECT && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                    count++;
                }
            }
        }
        
        return Math.abs(n - 2 * count);
    }
}