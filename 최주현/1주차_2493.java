package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static Stack<int[]> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder res = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        String towers = br.readLine();
        StringTokenizer st = new StringTokenizer(towers);

        for (int i=1;i<=N;i++) {
            int next = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty()) {
                if (next < stack.peek()[1]) {
                    res.append(stack.peek()[0] + " ");
                    break;
                }
                stack.pop();
            }
            if(stack.isEmpty()) {
                System.out.print("0 ");
            }
            stack.push(new int[] {i, next});
        }
    System.out.println(res);
    }
}
