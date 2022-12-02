import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static long[] a;
    static long[] answer = new long[3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        a = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(a);

        long min = Long.MAX_VALUE;
        for (int i=0; i < n; i++) {
            int lt = i + 1; int rt = n - 1;
            while (rt > lt) {
                long sum = a[rt] + a[lt] + a[i];
                long abs = Math.abs(sum);
                if (abs < min) {
                    min = abs;
                    answer[0] = a[i]; answer[1] = a[lt]; answer[2] = a[rt];
                }
                if (sum > 0) rt--;
                else lt++;
            }
        }

        Arrays.sort(answer);
        bw.write(answer[0] + " " + answer[1] + " " + answer[2]);

        br.close();
        bw.close();
    }
}