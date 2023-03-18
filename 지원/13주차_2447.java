import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Retry1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static char[][] starts;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        initStars(N);
        recursion(0, 0, N);

        StringBuilder result = new StringBuilder();
        for (char[] rowList : starts) {
            for (char value : rowList) {
                result.append(value);
            }
            result.append("\n");
        }
        System.out.println(result);
    }

    private static void initStars(int n) {
        starts = new char[n][n];
        for (char[] rowList : starts) {
            Arrays.fill(rowList, ' '); // 공백 초기화
        }
    }

    /*
    a(0, 0, 9)
    - a(0, 0, 3)
    - a(0, 3, 3)
    - a(0, 6, 3)
    - a(3, 0, 3)
    - a(3, 3, 3)
    - a(3, 6, 3)
    - a(6, 0, 3)
    - a(6, 3, 3)
    - a(6, 6, 3)
    1. 전체 9x9 기준 3x3 블록 9개로 나누기
    2. 각 block의 시작점을 기준으로 블록이 1이 될때까지 recursion
     */
    private static void recursion(int x, int y, int blockSize) {
        if (blockSize == 1) { // 더이상 recursion으로 돌릴 blockSize가 아닐 경우
            starts[x][y] = '*'; // 별 찍기
            return;
        }

        int nextBlockSize = blockSize / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isNotCenter(i, j)) { // 중앙 비우기
                    recursion(x + (i * nextBlockSize), y + (j * nextBlockSize), nextBlockSize);
                }
            }
        }
    }

    private static boolean isNotCenter(int x, int y) {
        return !(x == 1 && y == 1);
    }
}