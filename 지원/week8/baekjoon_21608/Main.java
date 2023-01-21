package week8.baekjoon_21608;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 21608번: 상어 초등학교
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static class Seat { // (x, y) 자리 정보
        int x;
        int y;
        int likeCount;
        int emptyCount;

        public Seat(int x, int y, int likeCount, int emptyCount) {
            this.x = x;
            this.y = y;
            this.likeCount = likeCount;
            this.emptyCount = emptyCount;
        }
    }

    static int N;
    static int students;
    static int[][] map;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static Map<Integer, List<Integer>> favoriteMap = new LinkedHashMap<>(); // Input 순서대로
    static PriorityQueue<Seat> stepQueue = new PriorityQueue<>((o1, o2) -> { // 매 스텝마다 일회성 큐
        // 1. 좋아하는 친구
        if (o1.likeCount > o2.likeCount) {
            return -1;
        } else if (o1.likeCount < o2.likeCount) {
            return 1;
        } else {
            // 2. 비어있는 자리
            if (o1.emptyCount > o2.emptyCount) {
                return -1;
            } else if (o1.emptyCount < o2.emptyCount) {
                return 1;
            } else {
                // 3. x 좌표
                return Integer.compare(o1.x, o2.x);
            }
        }
    });
    final static int[] score = {0, 1, 10, 100, 1000}; // 점수 변환

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        students = N * N;
        map = new int[N][N];

        for (int i = 0; i < students; i++) {
            applyFavoriteMap(br.readLine().split(" "));
        }

        for (Integer student : favoriteMap.keySet()) {
            seatAssignmentProcess(student);
        }

        System.out.println(getResult());
    }

    private static void applyFavoriteMap(String[] like) {
        // 학생
        int student = Integer.parseInt(like[0]);

        // 좋아하는 친구 리스트
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < like.length; i++) {
            list.add(Integer.parseInt(like[i]));
        }

        favoriteMap.put(student, list);
    }

    private static void seatAssignmentProcess(Integer student) {
        // 각 student의 자리 배정할때마다 stepQueue clear
        stepQueue.clear();

        for (int cx = 0; cx < N; cx++) {
            for (int cy = 0; cy < N; cy++) {
                // 현재 위치가 0이 아니면 이미 배정 완료된 좌석
                if (map[cx][cy] != 0) {
                    continue;
                }

                int likeCount = 0;
                int emptyCount = 0;
                for (int i = 0; i < 4; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];
                    
                    // (nx, ny)가 map 범위 조건을 만족하면
                    if (isRange(nx, ny)) {
                        // 비어있는 자리면
                        if (map[nx][ny] == 0) {
                            emptyCount++;
                        }

                        // 좋아하는 친구가 존재하면
                        if (existsFavoriteFriend(student, nx, ny)) {
                            likeCount++;
                        }
                    }
                }

                stepQueue.offer(new Seat(cx, cy, likeCount, emptyCount));
            }
        }

        Seat findSeat = stepQueue.poll();
        map[findSeat.x][findSeat.y] = student;
    }

    private static boolean isRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static boolean existsFavoriteFriend(Integer student, int x, int y) {
        return favoriteMap.get(student).contains(map[x][y]);

    }

    private static int getResult() {
        int result = 0;

        for (int cx = 0; cx < N; cx++) {
            for (int cy = 0; cy < N; cy++) {
                int count = 0;
                int student = map[cx][cy];

                for (int i = 0; i < dx.length; i++) {
                    int nx = cx + dx[i];
                    int ny = cy + dy[i];
                    if (isRange(nx, ny) && existsFavoriteFriend(student, nx, ny)) {
                        count++;
                    }
                }

                result += score[count];
            }
        }

        return result;
    }
}