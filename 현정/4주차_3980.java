import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_3980 {
    static int[][] arr = new int[11][11];
    static int[] visit = new int[11];
    static Stack<Integer> store = new Stack<>();
    static int max;

    static void run(int level, int sum){
        if (level == 11){
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 11; i++) {
            if (visit[i] == 1) continue;
            if (arr[level][i] == 0) continue;
            visit[i] = 1;
            run(level + 1, sum + arr[level][i]);
            visit[i] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            max = 0;
            for (int x = 0; x < 11; x++) {
                st = new StringTokenizer(br.readLine());
                for(int y =0; y < 11; y++){
                    arr[x][y] = Integer.parseInt(st.nextToken());
                }
            }
            run(0, 0);
            System.out.println(max);
        }

    }
}
