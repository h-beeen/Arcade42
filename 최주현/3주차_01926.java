import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//참고 블로그: https://blog.encrypted.gg/941

public class Main {

    static int[][] board;
    static boolean[][] vis;
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int n, m, largest;

    static void bfs(Point start){
        //Point 클래스는 x, y 좌표로 구성되어 있음
        //xy좌표를 활용하는 bfs에서 주로 Queue를 Point 타입으로 선언해서 활용함

        //시작점을 Queue에 추가하고 시작
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        vis[start.x][start.y] = true;

        int count = 0; //각 그림의 넓이를 저장하는 변수
        while(!queue.isEmpty()) {
            //Queue.poll 메서드는 stack의 pop()과 동일한 기능을 함
            //현재 위치를 queue에서 가져옴
            Point cur = queue.poll();
            for(int dir=0;dir<4;dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];
                if(nx < 0 || nx >= n || ny <0 || ny >= m) continue;
                //방문했거나 borad가 0인 부분 통과
                if (vis[nx][ny] || board[nx][ny] != 1) continue;
                vis[nx][ny] = true; //방문처리
                //방문한 부분을 queue에 추가함으로 너비우선 탐색 가능
                queue.add(new Point(nx, ny));
            }
            //queue에 각 그림의 한칸한칸씩 탐색되므로 큐에 들어갔던 갯수가 그림의 넓이가 됨
            count++;
        }
        // 가장 큰 그림 넓이
        if (largest < count) largest = count;
    }

    public static void main(String[] args) throws IOException {
        //자바에서 보통 입력에 사용하는 scanner 를 사용하면 시간초과가 나는 문제가 많음
        // 따라서 BufferedReader ( 이하 br) 를 사용

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //도화지 가로, 세로 입력 파트
        //br은 readline 으로 한줄씩 입력받을수 있기 때문에
        //공백 기준으로 문자를 나눠주는 StringTokenizer 이용
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        vis = new boolean[n][m];

        //도화지 내용 입력 파트
        for (int i=0;i<n;i++) {
            String row = br.readLine();
            st = new StringTokenizer(row);
            for (int j=0;j<m;j++) {
                int cell = Integer.parseInt(st.nextToken());
                board[i][j] = cell;
            }
        }

        int picCount = 0;
        for(int i=0;i<n;i++) {
            for (int j=0;j<m;j++) {
                if (board[i][j] == 1 && !vis[i][j]) {
                    //그림마다 시작점이 다르므로 처음 만나는 1을 시작점으로 넘겨줌
                    bfs(new Point(i, j));
                    picCount++; // 그림 개수
                }
            }
        }
        System.out.println(picCount);
        System.out.println(largest);
    }
}
