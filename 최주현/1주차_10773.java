package stack;

import java.util.Scanner;

public class BJ10773 {

    static int[] stack = new int[100000];
    static int top = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int k = in.nextInt();
        int result = 0;
        for (int i=0;i<k;i++) {
            int input = in.nextInt();
            if (input == 0) {
                remove();
            } else {
                add(input);
            }
        }
        for (int i=0;i<top;i++) {
            result += stack[i];
        }
        System.out.println(result);
    }

    public static void add(int item) {
        stack[top] = item;
        top++;
    }

    public static void remove() {
        if (top != 0) {
            stack[top-1] = 0;
            top--;
        }
    }
}
