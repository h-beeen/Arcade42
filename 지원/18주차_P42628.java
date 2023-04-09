import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

class Solution1 {
    static final String INSERT = "I";
    static PriorityQueue<Integer> tmp;

    public int[] solution(String[] operations) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (String operation : operations) {
            String[] split = operation.split(" ");
            String command = split[0];
            int number = Integer.parseInt(split[1]);

            if (command.equals(INSERT)) {
                queue.offer(number);
            } else {
                if (queue.isEmpty()) { // 삭제 명령어 -> 큐 비어있으면 무시
                    continue;
                }

                if (number < 0) { // 최솟값 삭제
                    queue = makeAsc(queue);
                    queue.poll();
                } else { // 최댓값 삭제
                    queue = makeDesc(queue);
                    queue.poll();
                }
            }
        }

        if (queue.isEmpty()) {
            return new int[]{0, 0};
        } else {
            queue = makeDesc(queue);
            int max = queue.peek();

            queue = makeAsc(queue);
            int min = queue.peek();

            return new int[]{max, min};
        }
    }

    private PriorityQueue<Integer> makeAsc(PriorityQueue<Integer> queue) {
        tmp = new PriorityQueue<>();
        while (!queue.isEmpty()) {
            tmp.offer(queue.poll());
        }
        return tmp;
    }

    private PriorityQueue<Integer> makeDesc(PriorityQueue<Integer> queue) {
        tmp = new PriorityQueue<>(Collections.reverseOrder());
        while (!queue.isEmpty()) {
            tmp.offer(queue.poll());
        }
        return tmp;
    }
}