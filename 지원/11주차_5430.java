import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 백준 5430번 AC : https://www.acmicpc.net/problem/5430
public class Retry1 {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Deque<Integer> deque = new ArrayDeque<>();
    static boolean leftPoll = true;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String[] commands = br.readLine().split("");
            br.readLine();
            applyArrayInDeque();
            bw.write(solve(commands, deque) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    private static void applyArrayInDeque() throws IOException {
        st = new StringTokenizer(br.readLine(), "[],");
        while (st.hasMoreTokens()) {
            deque.offer(Integer.parseInt(st.nextToken()));
        }
    }

    private static String solve(String[] commands, Deque<Integer> deque) {
        leftPoll = true;
        for (String command : commands) {
            if (command.equals("R")) {
                leftPoll = !leftPoll;
            } else {
                if (deque.isEmpty()) {
                    return "error";
                } else {
                    if (leftPoll) {
                        deque.pollFirst();
                    } else {
                        deque.pollLast();
                    }
                }
            }
        }

        return assembleDeque(leftPoll, deque);
    }

    private static String assembleDeque(boolean leftPoll, Deque<Integer> deque) {
        sb = new StringBuilder("[");
        while (!deque.isEmpty()) {
            sb.append(leftPoll ? deque.pollFirst() : deque.pollLast());
            if (deque.size() > 0) {
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

/*
4
RDD
4
[1,2,3,4]
DD
1
[42]
RRD
6
[1,1,2,3,5,8]
D
0
[]
>>
[2,1]
error
[1,2,3,5,8]
error
 */