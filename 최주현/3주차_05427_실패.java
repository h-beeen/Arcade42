import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//실패 사유
/*
불과 상근이 둘다 동시에 bfs 진행된다.

--> 같은 레벨에서 진행되는 순서는 입력에 따라 큐에 먼저 들어 간거 부터 진행될텐데
  이제 불이 붙으려는 칸으로 이동할 수 없다. >> 상근이보다 불이 먼저 움직여야돼
  상근이가 있는 칸에 불이 옮겨옴과 동시에 다른 칸으로 이동할 수 있다. >> 불보다 상근이가 먼저 움직여야돼
>> ????

탈출 못하는 경우 >> ????

상근이가 벽으로 가는 최단루트 --> level 계산
 */

//이하 코드는 그냥 bfs틀만 잡혀있다. 나중에 다시 풀때 입력부분 참고 예정
public class Main {

    static int[][] board;
    static boolean[][] vis;
    static int[] dx = {1, 0, -0, 0};
    static int[] dy = {0, 1, 0, -1};
    static Queue<Node> queue;

    public static class Node{
        int x, y, level;
        public Node(int x, int y, int level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }
    }

    public void bfs(int w, int h) {
        while(!queue.isEmpty()) {
            Node cur = queue.poll();
            for (int dir =0;dir<4;dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 0 || nx >= w || ny < 0 || ny >= h) continue;
                if(vis[nx][ny] || board[nx][ny] == -1) continue;
                vis[nx][ny] = true;
                queue.add(new Node(nx, ny, cur.level+1));
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        for (int c=0;c<T;c++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            board = new int[h][w];
            queue = new LinkedList<>();

            for (int i=0;i<h;i++) {
                String row = bf.readLine();

                for (int j=0;j<w;j++) {
                    char input = row.charAt(j);
                    switch (input) {
                        case '.':
                            board[i][j] = 0;
                            break;
                        case '#':
                            board[i][j] = -1;
                            break;
                        case '@':
                            board[i][j] = 1;
                            queue.add(new Node(i, j, 0));
                            break;
                        case '*':
                            board[i][j] = 5;
                            queue.add(new Node(i, j, 0));
                            break;
                        default:
                            break;
                    }
                }
            }
            //입력 확인 코드
            for(int i=0;i<h;i++) {
                for (int j=0;j<w;j++) {
                    System.out.print(board[i][j] + "   ");
                }
                System.out.println();
            }
            //입력 확인 코드
            System.out.println();
        }

    }
}
