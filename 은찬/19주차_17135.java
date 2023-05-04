import java.io.*;
import java.util.*;

public class Main {

    static int n;
    static int m;
    static int D;
    static List<List<Integer>> map = new ArrayList<>();
    static List<List<Integer>> tempMap;
    static List<Point> hunters = new ArrayList<>();

    static int answer = 0;

    static class Node{
        int y; int x; int dis;
        public Node(int y, int x, int dis) {
            this.y = y;
            this.x = x;
            this.dis = dis;
        }
    }

    static class Point {
        int y; int x;
        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int doShot() {
        int cnt = 0;
        while (true) {
            List<Node> shots = new ArrayList<>();
            PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
                if (a.dis == b.dis) return Integer.compare(a.x, b.x);
                return Integer.compare(a.dis, b.dis);
            } );

            for (Point hunter : hunters) {
                pq.clear();
                boolean flag = false;
                for (int x = 0; x < m; x++) {
                    List<Integer> list = tempMap.get(x);
                    for (int y = 0; y < list.size(); y++) {
                        if (list.get(y) == 1) {
                            int dis = getDis(hunter, y, x);
                            if (dis <= D) {
                                pq.offer(new Node(y, x, dis));
                            }
                            flag = true;
                        }
                    }
                }
                if (!flag) {
                    return cnt;
                }
                if (!pq.isEmpty()) {
                    shots.add(pq.poll());
                }
            }

            for (Node shot : shots) {
                if (tempMap.get(shot.x).get(shot.y) != 0) {
                    cnt++;
                    tempMap.get(shot.x).set(shot.y, 0);
                }
            }

            for (int i = 0; i < m; i++) {
                tempMap.get(i).remove(0);
            }
        }
    }

    static int getDis(Point hunter, int enemyY, int enemyX) {
        int a = Math.abs(hunter.y - enemyY);
        int b = Math.abs(hunter.x - enemyX);
        return a + b;
    }

    static void run(int level, int start) {
        if (level == 3) {
            deepCopyMap();
            int result = doShot();
            answer = Math.max(answer, result);
            return;
        }

        for (int i = start; i < m; i++) {
            hunters.add(new Point(-1, i));
            run(level + 1, i + 1);
            hunters.remove(hunters.size() - 1);
        }
    }

    static void deepCopyMap() {
        tempMap = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            tempMap.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            tempMap.get(i).addAll(map.get(i));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        for (int i = 0; i < m; i++) {
            map.add(new ArrayList<>());
        }

        List<Stack<Integer>> stacks = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            stacks.add(new Stack<>());
        }

        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < m; x++) {
                int input = Integer.parseInt(st.nextToken());
                stacks.get(x).push(input);
            }
        }

        for (int x = 0; x < m; x++) {
            Stack<Integer> stack = stacks.get(x);
            List<Integer> list = map.get(x);
            while (!stack.isEmpty()) {
                list.add(stack.pop());
            }
        }

        run(0, 0);

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}