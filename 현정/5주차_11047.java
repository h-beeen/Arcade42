import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11047 {
    static int n;
    static int k;
    static int[] coin;

    static int greedy() {
        int cnt = 0;
        int remain = k;
        for (int i = n - 1; i >= 0; i--) {
            //큰 동전부터 나눠서 동전 개수 더하기
            cnt = cnt + remain / coin[i];
            //나머지 값 갱신
            remain = remain % coin[i];
        }
        return cnt;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coin = new int[n];

        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(greedy());
    }
}
