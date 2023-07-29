import java.io.*;
import java.util.*;

public class BJ_10815 {
    static int n;
    static int m;
    static int start;
    static int end;
    static int mid;

    static int[] have;
    static int[] cards;

    static int[] answer;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //상근이가 보유한 숫자카드 입력
        n = Integer.parseInt(br.readLine());
        have = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            have[i] = Integer.parseInt(st.nextToken());
        }
        //보유한 숫자카드 정렬
        Arrays.sort(have);

        //숫자카드 갯수 입력
        m = Integer.parseInt(br.readLine());
        cards = new int[m];
        //정답 배열 선언
        answer = new int[m];
        //숫자카드 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        //보유한 카드를 이분탐색하며 cards만큼 for문 사용
        for (int i = 0; i < m; i++) {
            //key값으로 카드들 값 사용
            int key = cards[i];

            start = 0;
            end = n - 1;

            while(start <= end){
                mid = (start + end) / 2;
                //key 값이 가지고 있는 값보다 작으면 왼쪽 탐색
                if (have[mid] > key) {
                    end = mid - 1;
                }
                //key 값이 가지고 있는 값보다 크면 오른쪽 탐색
                else if (have[mid] < key) {
                    start = mid + 1;
                }
                //가지고 있는 경우 정답 배열의 i번째 값 1로 변경하고 while문 종료
                else {
                    answer[i] = 1;
                    break;
                }
            }
        }
        //정답 출력
        for (int i = 0; i < m; i++) {
            System.out.println(answer[i]);
        }
    }
}
