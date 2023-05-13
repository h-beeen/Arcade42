// 디스크 컨트롤러 -> https://school.programmers.co.kr/learn/courses/30/lessons/42627

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] jobs) {
        // 1. 요청 시간 기준 오름차순 정렬
        Arrays.sort(jobs, (o1, o2) -> {
            if(o1[0] < o2[0]) {
                return -1;
            } else if(o1[0] > o2[0]) {
                return 1;
            } else {
                return Integer.compare(o1[1], o2[1]);
            }
        });

        // 2. CPU Birst Time 기준 오름차순 정렬 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));

        pq.offer(jobs[0]);
        int currentTime = jobs[0][0]; // 논리적인 시작 시간
        int workTime = 0; // 작업별로 Turnaround Time 누적

        int index = 1;
        while(!pq.isEmpty()) {
            int[] job = pq.poll();
            currentTime += job[1]; // Job Process Time
            workTime += currentTime - job[0]; // Turnaround Time

            // job이 실행중인데 들어오는 새로운 job이 있을 경우
            while(index < jobs.length && jobs[index][0] <= currentTime) {
                pq.offer(jobs[index++]);
            }

            // Ready Queue 비어있는 상황
            if(index < jobs.length && pq.isEmpty()) {
                currentTime = jobs[index][0]; // 현재 시각을 강제로 업데이트
                pq.offer(jobs[index++]);
            }
        }

        return workTime / jobs.length;
    }
}