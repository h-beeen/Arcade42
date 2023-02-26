package queue;

import java.util.Scanner;

public class BJ10845 {

    static int MAX = 10000;
    static int[] queue;
    static int head, tail = 0;

    public static void main(String[] args) {
        queue = new int[MAX];

        StringBuilder st = new StringBuilder();
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();

        for (int i=0;i<N;i++) {
            String cmd = in.next();
            switch (cmd) {
                case "push":
                    push(in.nextInt());
                    break;
                case "pop":
                    st.append(pop()).append("\n");
                    break;
                case "size":
                    st.append(size()).append("\n");
                    break;
                case "empty":
                    st.append(empty()).append("\n");
                    break;
                case "front":
                    st.append(front()).append("\n");
                    break;
                case "back":
                    st.append(back()).append("\n");
                    break;
                default:
                    break;
            }
        }
        System.out.print(st);
    }

    public static void push(int item) {
        queue[tail] = item;
        tail++;
    }

    public static int size() {
        return tail-head;
    }

    public static int empty() {
        if (size() > 0) return 1;
        else return 0;
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
