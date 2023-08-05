import java.io.*;
import java.util.*;

public class BJ_2606 {

    static int com;
    static int connect;

    static int[][] info;
    static int[] visit;

    static Queue<Integer> queue = new LinkedList<>();

    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //컴퓨터 갯수 입력
        com = Integer.parseInt(br.readLine());
        // 연결된 컴퓨터 쌍 입력
        connect = Integer.parseInt(br.readLine());

        // 연결된 부분 없을 때 -1 출력되는 부분 변경
        if(connect == 0) {
            System.out.println(0);
        }
        else {
            info = new int[connect][2];
            visit = new int[com];
            for (int i = 0; i < connect; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                info[i][0] = x;
                info[i][1] = y;
            }
            // 1번 컴퓨터 바이러스 걸림
            queue.add(1);

            while (!queue.isEmpty()) {
                int virus = queue.poll();
                for (int i = 0; i < connect; i++) {
                    // 양쪽 검사
                    if (info[i][0] == virus) {
                        if (visit[info[i][1] - 1] == 1) continue;
                        visit[info[i][1] - 1] = 1;
                        queue.add(info[i][1]);
                    } else if (info[i][1] == virus) {
                        if (visit[info[i][0] - 1] == 1) continue;
                        visit[info[i][0] - 1] = 1;
                        queue.add(info[i][0]);
                    }
                }
            }
            for (int i = 0; i < visit.length; i++) {
                if (visit[i] == 1)
                    cnt++;
            }
            // 1번 컴퓨터 빼고 cnt 출력
            System.out.println(cnt - 1);
        }
    }
}
