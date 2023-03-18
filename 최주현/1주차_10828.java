package stack;

import java.util.Scanner;

public class BJ10828 {

    static int[] stack = new int[10000];
    static int top = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder stringBuilder = new StringBuilder();

        int n = scanner.nextInt();

        for (int i=0;i<n;i++) {
            String str = scanner.next();

            switch (str) {
                case "push":
                    push(scanner.nextInt());
                    break;
                case "pop":
                    stringBuilder.append(pop()).append('\n');
                    break;
                case "size":
                    stringBuilder.append(size()).append('\n');
                    break;
                case "empty":
                    stringBuilder.append(empty()).append('\n');
                    break;
                case "top":
                    stringBuilder.append(top()).append('\n');
                    break;
            }
        }
        System.out.println(stringBuilder);
    }

    public static int size() {
        return top;
    }

    public static void push(int num) {
        stack[top] = num;
        top++;
    }

    public static int pop() {
        if (top == 0) {
            return -1;
        } else {
            int res = stack[top-1];
            top--;
            return res;
        }
    }

    public static int empty() {
        if (top == 0) {
            return 1;
        } else return 0;
    }

    public static int top() {
        if (top ==0) {
            return -1;
        } else {
            return stack[top-1];
        }
    }
}
