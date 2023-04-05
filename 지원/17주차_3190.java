import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Retry1 {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Part {
        int x;
        int y;

        public Part(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Part part = (Part) o;
            return x == part.x && y == part.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static final int APPLE = 7777;
    static int N; // 보드 크기 N x N
    static int K; // 사과 개수
    static int L; // 방향 변환 횟수
    static Map<Integer, String> directionMap = new HashMap<>(); // 방향 변환 Map [시간: 방향]
    static int[][] map;
    static String direction = "R"; // 바라보고 있는 방향
    static int[] go = {0, 1}; // (dx, dy)
    static Deque<Part> queue = new LinkedList<>(); // 뱀 몸통 정보 들어있는 Deque
    static int time = 0; // 시간

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            map[x][y] = APPLE;
        }

        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            directionMap.put(time, direction);
        }

        queue.offer(new Part(0, 0)); // 초기 위치 머리 넣어주기
        game();
        System.out.println(time);
    }

    private static void game() {
        while (true) {
            if (directionMap.containsKey(time)) { // 방향 변환할 시간이면
                changeDirection(directionMap.get(time));
            }

            Part head = queue.peekFirst(); // 머리 꺼내기
            int nx = head.x + go[0];
            int ny = head.y + go[1];

            if (!isRange(nx, ny) || isTouch(nx, ny)) { // 종료 조건
                time += 1; // 종료 조건에 도달하면 이동한거니까 시간 추가
                return;
            }

            queue.offerFirst(new Part(nx, ny)); // 1. 앞단 머리 늘리기
            if (map[nx][ny] == APPLE) { // 2-1. (nx, ny)에 사과 있으면
                map[nx][ny] = 0; // 사과 먹기
            } else { // 2-2. 사과 없으면
                queue.pollLast(); // 꼬리 자르기
            }

            time += 1; // 이동했으니까 시간 추가
        }
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static boolean isTouch(int x, int y) {
        return queue.contains(new Part(x, y));
    }

    private static void changeDirection(String change){
        if(change.equals("L")){
            switch (direction) {
                case "R" -> {
                    direction = "U";
                    go = new int[]{-1, 0};
                }
                case "L" -> {
                    direction = "D";
                    go = new int[]{1, 0};
                }
                case "D" -> {
                    direction = "R";
                    go = new int[]{0, 1};
                }
                default -> {
                    direction = "L";
                    go = new int[]{0, -1};
                }
            }
        }
        else{
            switch (direction) {
                case "R" -> {
                    direction = "D";
                    go = new int[]{1, 0};
                }
                case "L" -> {
                    direction = "U";
                    go = new int[]{-1, 0};
                }
                case "D" -> {
                    direction = "L";
                    go = new int[]{0, -1};
                }
                default -> {
                    direction = "R";
                    go = new int[]{0, 1};
                }
            }
        }
    }
}

/*
6
3
3 4
2 5
5 3
3
3 D
15 L
17 D
>> 9
10
4
1 2
1 3
1 4
1 5
4
8 D
10 D
11 D
13 L
>> 21
10
5
1 5
1 3
1 2
1 6
1 7
4
8 D
10 D
11 D
13 L
>> 13
 */