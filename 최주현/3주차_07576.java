import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;

    static int[][] board;
    static boolean[][] vis;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Node> queue = new LinkedList<>();

    //이번문제에서는 한칸에서 인접한 칸으로 모두 영향을 미쳐야 하루가 지나므로 depth를 계산하는 문제
    //따라서 이전에 x, y만 사용하던 Point 클래스 대신 Node 클래스를 만들어 사용 #level == depth
    static class Node {
        int x;
        int y;
        int level;

        public Node(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }
    }

    static int bfs() {
        //토마토가 입력될 때 queue에 넣어 시작점으로 정했으니 따로 시작 점을 정해주지 않음

        int day = 0;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            // 최종적으로 bfs가 끝났을 때의 level이 다 익은 날짜가 됨
            day = cur.level;
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (board[nx][ny] == -1 || vis[nx][ny]) continue;
                vis[nx][ny] = true;
                board[nx][ny] = 1;
                queue.add(new Node(nx, ny, cur.level + 1));
            }
        }
        return day;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        //상자 크기 입력 부분
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        vis = new boolean[n][m];

        // 토마토 입력 부분
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    //토마토가 여려개 있다면 동시에 익기 시작해야 하므로
                    //토마토가 추가 될 때마다 queue에 넣어줌
                    queue.add(new Node(i, j, 0));
                    vis[i][j] = true; // 방문처리
                }
            }
        }

        int res = bfs();

        // 익지 않은 토마토를 탐색하는 부분
        for (int i = 0; i < n; i++) {
            for (int j=0;j<m;j++) {
                if (board[i][j] == 0) {
                    res = -1;
                    break;
                }
            }
        }
        System.out.println(res);
    }
}
