import java.io.*;
import java.util.*;

public class BJ_2493 {
    static Stack<Integer> stack = new Stack<>();
    static ArrayList<Long> tower = new ArrayList<>();
    static int[] answer;

    static void compare(int n) {
        for (int i = n - 1; i > -1; i--) {
            while (!stack.isEmpty()) {
                if((tower.get(stack.peek())) < tower.get(i)) {
                    answer[stack.peek()] = i + 1;
                    stack.pop();
                }
                else break;
            }
            stack.push(i);
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        answer = new int[n];
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            tower.add(Long.parseLong(st.nextToken()));
        }

        compare(n);

        for (int i = 0; i < n; i++)
            System.out.print(answer[i] + " ");

    }
}
