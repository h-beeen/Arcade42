import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2003 {
    static int n;
    static int m;
    static int[] arr;

    static int start = 0;
    static int end = 0;
    static int total = 0;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        while (start < n && end < n) {
            for (int i = start; i <= end; i++) {
                total += arr[i];
            }
            if (total == m) {
                cnt++;
                start++;
                end = start;
            } else if (total>m) {
                start++;
                end = start;
            } else {
                end++;
            }
            total = 0;
        }

        System.out.println(cnt);

    }
}
