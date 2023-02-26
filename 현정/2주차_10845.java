import java.io.*;
import java.util.*;

public class BJ_10845 {

    static ArrayList<Integer> queue = new ArrayList<>();
    static int head = 0;
    static int tail = 0;

    static void push(int x) {
        queue.add(tail, x);
        tail++;
    }

    static void pop() {
        if (tail == head)
            System.out.println(-1);
        else {
            System.out.println(queue.get(head));
            head++;
        }
    }

    static void size() {
        System.out.println(tail - head);
    }

    static void empty() {
        if (tail <= head)
            System.out.println(1);
        else System.out.println(0);
    }

    static void front() {
        if (tail <= head)
            System.out.println(-1);
        else
            System.out.println(queue.get(head));
    }

    static void back() {
        if (tail <= head)
            System.out.println(-1);
        else
            System.out.println(queue.get(tail - 1));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();
            switch (command) {
                case "push":
                    int x = Integer.parseInt(st.nextToken());
                    push(x);
                    break;
                case "pop":
                    pop();
                    break;
                case "size":
                    size();
                    break;
                case "empty":
                    empty();
                    break;
                case "front":
                    front();
                    break;
                case "back":
                    back();
                    break;
            }
        }
    }
}
