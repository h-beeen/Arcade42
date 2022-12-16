import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(a);
        int answer = 0;

        int lt = 1;
        int rt = a[n - 1] - a[0];

        while (rt >= lt) {
            int m = (lt + rt) / 2;
            int cnt = 1;
            int now = 0;
            for (int i = 1; i < n; i++) {
                if ((a[i] - a[now]) >= m) {
                    cnt++;
                    now = i;
                }
            }
            if (cnt >= k) {
                lt = m + 1;
                answer = Math.max(answer, m);
            } else {
                rt = m - 1;
            }
        }

        bw.write(String.valueOf(answer));

        bw.close();
        br.close();
    }
}