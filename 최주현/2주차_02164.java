package queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ02164 {
    static Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        queue = new LinkedList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        for (int i=1;i<=N;i++) { queue.add(i); }

        while(queue.size() != 1) {
            queue.poll();
            queue.add(queue.poll());
        }

        System.out.println(queue.peek());
    }
}
