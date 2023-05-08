import java.io.*;
import java.util.*;

public class Main {

    static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static int n;
    static int m;
    static int energy;
    static Node taxi;

    static int[][] map;
    static int[][][][] disMap;

    static Comparator<Customer> comparator = (a, b) -> {
        if (a.dis == b.dis) {
            if (a.start.y == b.start.y) {
                return Integer.compare(a.start.x, b.start.x);
            } return Integer.compare(a.start.y, b.start.y);
        } return Integer.compare(a.dis, b.dis);
    };
    static PriorityQueue<Customer> pq = new PriorityQueue<>(comparator);

    static class Node{
        int y; int x; int level;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
            this.level = 0;
        }
        public Node(int y, int x, int level) {
            this.y = y;
            this.x = x;
            this.level = level;
        }
    }

    static class Customer {
        Node start; Node end; int dis;

        public Customer(Node start, Node end, int dis) {
            this.start = start;
            this.end = end;
            this.dis = dis;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        energy = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        disMap = new int[n][n][n][n];

        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int taxiY = Integer.parseInt(st.nextToken()) - 1;
        int taxiX = Integer.parseInt(st.nextToken()) - 1;
        taxi = new Node(taxiY, taxiX);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            Node start = new Node(a - 1, b - 1);
            Node end = new Node(c - 1, d - 1);

            int dis = getDis(taxi, start);
            if (dis == -1) {
                bw.write("-1");
                br.close();
                bw.close();
                return;
            }

            Customer customer = new Customer(start, end, dis);
            pq.offer(customer);
        }

        boolean result = run();

        if (result) {
            bw.write(String.valueOf(energy));
        } else bw.write("-1");

        br.close();
        bw.close();
    }

    static boolean run() {
        while (!pq.isEmpty()) {
            Customer customer = pq.poll();
            if (energy >= customer.dis) {
                energy -= customer.dis;
            } else return false;

            int driveDis = getDis(customer.start, customer.end);
            if (driveDis == -1) return false;
            if (energy >= driveDis) {
                energy += driveDis;
                updateTaxi(customer);
                pq = updateCustomersDis();
            } else return false;
        }
        return true;
    }

    static int getDis(Node s, Node e) {
        if (disMap[s.y][s.x][e.y][e.x] == 0) {
            disMap[s.y][s.x][e.y][e.x] = getDisByBfs(s, e);
        }
        return disMap[s.y][s.x][e.y][e.x];
    }

    static PriorityQueue<Customer> updateCustomersDis() {
        PriorityQueue<Customer> newPq = new PriorityQueue<>(comparator);
        while (!pq.isEmpty()) {
            Customer customer = pq.poll();
            customer.dis = getDis(taxi, customer.start);
            newPq.add(customer);
        }
        return newPq;
    }

    static void updateTaxi(Customer customer) {
        taxi.y = customer.end.y;
        taxi.x = customer.end.x;
    }

    static int getDisByBfs(Node start, Node end) {
        boolean[][] visit = new boolean[n][n];
        visit[start.y][start.x] = true;
        Queue<Node> que = new LinkedList<>();
        que.offer(start);

        while (!que.isEmpty()) {
            Node p = que.poll();
            if (p.y == end.y && p.x == end.x) {
                return p.level;
            }

            for (int i = 0; i < 4; i++) {
                int ny = p.y + dir[i][0];
                int nx = p.x + dir[i][1];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                if (visit[ny][nx]) continue;
                if (map[ny][nx] == 1) continue;
                visit[ny][nx] = true;
                que.offer(new Node(ny, nx, p.level + 1));
            }
        }
        return -1;
    }
}