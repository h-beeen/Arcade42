import java.io.*;
import java.util.*;

public class BJ_2164 {
    static Queue<Integer> queue = new LinkedList<>();

    static void cards() {
        while (queue.size()>1) {
                queue.poll();
                queue.offer(queue.poll());
        }
        System.out.println(queue.poll());
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            queue.offer(i+1);
        }

        cards();
    }
}
