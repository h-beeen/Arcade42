import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class miro {

    static int n, m = 0;
    static BufferedReader bf;
    static StringTokenizer st;

    static int[][] board;
    static boolean[][] vis;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static class Node {
        int x, y, level;
        public Node(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }
    }

    public static int bfs() {
        Queue<Node> queue = new LinkedList<>();
        //시작점 (0,0) 으로 고정, 문제에서 시작레벨 1로 고정
        queue.add(new Node(0, 0, 1));
        vis[0][0] = true;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if(cur.x == n-1 && cur.y == m-1) return cur.level;

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                //'0'인 칸과 방문했던 칸 통과
                if (board[nx][ny] != 1 || vis[nx][ny]) continue;
                vis[nx][ny] = true; //방문처리
                queue.add(new Node(nx, ny, cur.level + 1));
            }
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {
        bf = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        vis = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String row = bf.readLine();
            for (int j = 0; j < m; j++) {
                //(아스키 코드) char에서 '0' 빼면 숫자로 바뀜
                board[i][j] = row.charAt(j) - '0';
            }
        }
        System.out.print(bfs());
    }
}
