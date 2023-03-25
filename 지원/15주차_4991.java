import java.io.*;
import java.util.*;

public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static class Point { // 시작점 + 청소할 부분 관련 Point (1)
        int x;
        int y;
        int distance; // PointS -> PointD로 갈때 거리

        Point(int x, int y) {
            this(x, y, 0);
        }

        Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
    static List<Point> mainPoints;

    static class Node { // (1)에서 찾은 각 Point간의 거리를 저장하기 위한 Node
        int end;
        int weight;

        Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }
    static List<List<Node>> graph;

    final static String DIRTY = "*";
    final static String FURNITURE = "x";
    final static String START = "o";
    static int W;
    static int H;
    static String[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int result;

    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new String[W][H];
            mainPoints = new ArrayList<>();
            graph = new ArrayList<>();
            result = Integer.MAX_VALUE;

            if (W == 0 && H == 0) {
                break;
            }

            for (int i = 0; i < W; i++) {
                String[] split = br.readLine().split("");
                for (int j = 0; j < H; j++) {
                    String token = split[j];
                    map[i][j] = token;
                    if (token.equals(START)) {
                        mainPoints.add(0, new Point(i, j)); // 시작점은 맨 앞에 add
                    } else if (token.equals(DIRTY)) {
                        mainPoints.add(new Point(i, j));
                    }
                }
            }

            // mainPoints내부 각 Point들간의 거리 계산 graph -> 모든 경우의 수
            for (int i = 0; i < mainPoints.size(); i++) {
                graph.add(new ArrayList<>());
            }

            for (int start = 0; start < mainPoints.size() - 1; start++) {
                for (int end = start + 1; end < mainPoints.size(); end++) {
                    // start -> end 최단 거리
                    int distance = bfs(mainPoints.get(start), mainPoints.get(end));

                    if (distance != -1) { // start -> end에 도달할 수 있다면 graph에 적용
                        graph.get(start).add(new Node(end, distance));
                        graph.get(end).add(new Node(start, distance));
                    }
                }
            }

            boolean[] visited = new boolean[mainPoints.size()];
            visited[0] = true; // 시작점 출발
            backtracking(0, 0, 0, visited); // backtracking
            bw.write((result == Integer.MAX_VALUE ? -1 : result) + "\n");
        }

        bw.flush();
    }

    private static int bfs(Point start, Point end) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[W][H];

        visited[start.x][start.y] = true;
        queue.offer(new Point(start.x, start.y, 0));

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.x == end.x && current.y == end.y) { // end 도착
                return current.distance;
            }

            for (int i = 0; i < dx.length; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (isRange(nx, ny) && !visited[nx][ny] && !map[nx][ny].equals(FURNITURE)) { // 범위 O + 방문 X + 가구 X
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny, current.distance + 1));
                }
            }
        }

        return -1; // end 도착 불가능
    }

    private static void backtracking(int index, int depth, int sum, boolean[] visited) {
        if (depth == mainPoints.size() - 1) { // 모든 더러운 곳 청소 완료했으면
            result = Math.min(result, sum); // update
            return;
        }

        for (Node neighbor : graph.get(index)) { // index의 이웃들에 대해서 순차적 방문
            if (!visited[neighbor.end]) {
                visited[neighbor.end] = true; // 이웃 N 방문
                backtracking(neighbor.end, depth + 1, sum + neighbor.weight, visited);
                visited[neighbor.end] = false; // 이웃 N rollback
            }
        }
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < W && 0 <= y && y < H;
    }
}

/*
7 5
.......
.o...*.
.......
.*...*.
.......
15 13
.......x.......
...o...x....*..
.......x.......
.......x.......
.......x.......
...............
xxxxx.....xxxxx
...............
.......x.......
.......x.......
.......x.......
..*....x....*..
.......x.......
10 10
..........
..o.......
..........
..........
..........
.....xxxxx
.....x....
.....x.*..
.....x....
.....x....
5 5
....*
.*.*.
..o..
..*..
.....
6 5
.....*
.*..*.
...o..
.*....
......
6 5
....o*
.xxxxx
.....x
xxxx.x
*....x
0 0
>>
8
49
-1
8
10
18
8
 */
