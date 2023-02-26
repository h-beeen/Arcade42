import java.io.*;
import java.util.*;

public class BJ_10828 {
    ArrayList<Integer> stack = new ArrayList<Integer>();

    void push(int x) {
        stack.add(x);
    }

    void pop() {
        if (stack.size() < 1)
            System.out.println("-1");
        else {
            System.out.println(stack.get(stack.size() - 1));
            stack.remove(stack.size() - 1);
        }
    }

    void size() {
        System.out.println(stack.size());
    }

    void empty() {
        if (stack.size() < 1)
            System.out.println("1");
        else System.out.println("0");
    }

    void top() {
        if (stack.size() <= 0)
            System.out.println("-1");
        else {
            System.out.println(stack.get(stack.size() - 1));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer;
        BJ_10828 m = new BJ_10828();

        int n = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine(), " ");
            String command = stringTokenizer.nextToken();
            switch (command) {
                case "push":
                    int x = Integer.parseInt(stringTokenizer.nextToken());
                    m.push(x);
                    break;
                case "pop":
                    m.pop();
                    break;
                case "size":
                    m.size();
                    break;
                case "empty":
                    m.empty();
                    break;
                case "top":
                    m.top();
                    break;
            }
        }
    }
}
