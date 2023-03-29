import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_11399 {
    static int[] line;
    static int n;

    static int findMin() {
        // 배열을 오름차순으로 정렬
        Arrays.sort(line);
        int result = 0;
        //1번에서 n번까지 배열값 더하기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                result += line[j];
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        line = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            line[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(findMin());

    }

}
