import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Point {
        int x;
        int y;
        int distance; // 궁수 - 적 사이의 거리

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static int N;
    static int M;
    static int D;
    static int[][] map;
    static List<Point> enemies = new ArrayList<>(); // 적 위치 정보
    static PriorityQueue<Point> queue; // 각 궁수에 대한 공격 후보군
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    enemies.add(new Point(i, j));
                }
            }
        }

        batchAndAttack(0, 0, new int[3], new boolean[M]);
        System.out.println(result);
    }

    private static void batchAndAttack(int count, int position, int[] archers, boolean[] visited) {
        if (count == 3) {
            List<Point> copy = copyEnemies(); // 각 공격마다 적 위치 정보 카피본
            attack(archers, copy);
            return;
        }

        for (int index = position; index < M; index++) {
            if (!visited[index]) { // 궁수가 없는 자리에
                archers[count] = index; // 궁수 배치
                visited[index] = true; // 궁수 배치 표시
                batchAndAttack(count + 1, index + 1, archers, visited); // 다음 궁수 배치
                visited[index] = false; // rollback
            }
        }
    }

    private static List<Point> copyEnemies() {
        List<Point> result = new ArrayList<>();

        for (Point enemy : enemies) {
            result.add(new Point(enemy.x, enemy.y));
        }

        return result;
    }

    private static void attack(int[] archers, List<Point> enemies) {
        int archerRow = N; // 궁수 row 기준 -> 공격 종료되면 1칸씩 위로 이동
        int count = 0;

        while (archerRow > 0) {
            // 1. 모든 적에 대한 distance 판단 후 target 설정
            Set<Point> targets = new HashSet<>();

            for (int archerColumn : archers) {
                queue = new PriorityQueue<>((o1, o2) -> {
                    // 1. 거리 순
                    if (o1.distance < o2.distance) {
                        return -1;
                    } else if (o1.distance > o2.distance) {
                        return 1;
                    } else {
                        // 2. 왼쪽
                        return Integer.compare(o1.y, o2.y);
                    }
                });

                for (Point enemy : enemies) {
                    if (enemy.x >= archerRow) { // 적이 궁수보다 밑에 있으면 공격 불가능
                        continue;
                    }

                    int dr = Math.abs(enemy.x - archerRow);
                    int dc = Math.abs(enemy.y - archerColumn);

                    int distance = dr + dc;
                    if (distance <= D) { // 공격 가능한 범위라면
                        queue.offer(new Point(enemy.x, enemy.y, distance));
                    }
                }

                if (!queue.isEmpty()) { // 후부군이 있으면
                    targets.add(queue.poll()); // 타겟 설정
                }
            }

            // 2. 공격
            count += targets.size();

            // 3. 공격한 enemy 제거
            List<Point> remove = new ArrayList<>();
            for (Point target : targets) {
                for (Point enemy : enemies) {
                    if (target.equals(enemy)) {
                        remove.add(enemy);
                    }
                }
            }

            enemies.removeIf(remove::contains);

            // 4. 궁수 up
            archerRow--;
        }

        result = Math.max(result, count);
    }
}

/*
2 4 2
1 1 1 1
0 1 1 0
>> 5
5 5 3
1 1 1 0 1
0 1 1 0 0
1 1 1 0 0
0 1 1 0 0
1 1 1 0 0
>> 13
2 7 2
0 0 1 0 1 0 1
1 0 1 0 1 0 0
>> 5
2 4 2
1 1 1 1
0 1 1 0
>> 5
9 10 4
1 0 0 1 0 1 0 1 1 0
0 0 0 1 0 0 0 1 0 0
0 1 0 0 1 0 0 1 1 1
0 0 1 1 0 1 0 1 1 0
0 1 1 0 0 0 0 1 0 1
0 1 1 1 0 1 0 1 0 0
0 0 0 0 0 0 0 1 0 0
1 1 1 1 1 1 1 1 0 1
0 1 1 0 1 1 0 1 1 0
>> 26
 */