import java.io.*;
import java.util.*;

public class BJ_18258 {
    static Queue queue = new LinkedList<>();
    static int tail = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken();
            switch (command) {
                case "push":
                    int x = Integer.parseInt(st.nextToken());
                    queue.offer(x);
                    tail = x;
                    break;
                case "pop":
                    if (queue.isEmpty())
                        bw.write(-1 + "\n");
                    else bw.write(queue.poll() + "\n");
                    break;
                case "size":
                    bw.write(queue.size() + "\n");
                    break;
                case "empty":
                    if (queue.isEmpty())
                        bw.write(1 + "\n");
                    else bw.write(0 + "\n");
                    break;
                case "front":
                    if (queue.isEmpty())
                        bw.write(-1 + "\n");
                    else bw.write(queue.peek() + "\n");
                    break;
                case "back":
                    if (queue.isEmpty())
                        bw.write(-1 + "\n");
                    else bw.write(tail + "\n");
                    break;
            }
        }
        bw.close();
    }
}
