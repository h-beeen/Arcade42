import java.io.*;
import java.util.*;

public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int[] a;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        a = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        stack.push(n);
        for (int i = n - 1; i >= 1; i--) {
            while (!stack.isEmpty()) {
                int p = stack.peek();
                int now = a[p];
                if (now <= a[i]) {
                    a[p] = i;
                    stack.pop();
                } else break;
            }
            stack.push(i);
            int k = 3;
        }

        for (int p : stack) {
            a[p] = 0;
        }

        for (int i = 1; i <= n; i++) {
            bw.write(a[i] + " ");
        }

        br.close();
        bw.close();
    }
}