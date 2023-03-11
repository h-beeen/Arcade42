package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ18258 {
    static int MAX = 2000000;
    static int[] queue;
    static int head, tail = 0;

    public static void main(String[] args) throws IOException {
        queue = new int[MAX];

        StringBuilder result = new StringBuilder();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        for (int i=0;i<N;i++) {
            String cmd = bf.readLine();
            StringTokenizer st = new StringTokenizer(cmd);

            switch (st.nextToken()) {
                case "push":
                    push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    result.append(pop()).append("\n");
                    break;
                case "size":
                    result.append(size()).append("\n");
                    break;
                case "empty":
                    result.append(empty()).append("\n");
                    break;
                case "front":
                    result.append(front()).append("\n");
                    break;
                case "back":
                    result.append(back()).append("\n");
                    break;
                default:
                    break;
            }
        }
        System.out.print(result);
    }

    public static void push(int item) {
        queue[tail] = item;
        tail++;
    }

    public static int size() {
        return tail-head;
    }

    public static int empty() {
        if (size() > 0) return 0;
        else return 1;
    }

    public static int pop() {
        if (size() > 0) {
            return queue[head++];
        }
        return -1;
    }

    public static int front() {
        if (size() > 0) {
            return queue[head];
        } else return -1;
    }

    public static int back() {
        if (size() > 0) {
            return queue[tail-1];
        } else return -1;
    }

}
