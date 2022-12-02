import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (int i = 1; i < n; i++) {
            while (!stack.isEmpty()) {
                int now = a[i];
                int pre = a[stack.peek()];
                if (now > pre) {
                    a[stack.pop()] = now;
                } else break;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int pop = stack.pop();
            a[pop] = -1;
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int i = 0; i < n; i++) {
            String s = String.valueOf(a[i]);
            bw.write(s + " ");
        }
        bw.close();
        bf.close();
    }
}