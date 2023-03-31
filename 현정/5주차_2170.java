import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_2170 {
    static int n;
    static Point[] arr;

    //객체 생성
    static class Point{
        int start; int end;
        public Point(int x,int y) {
            this.start = x;
            this.end = y;
        }
    }

    //연산부분
    static int  calculate() {
        //가장 첫번째 배열의 객체 받아오기
        int s = arr[0].start;
        int e = arr[0].end;
        //초기 길이
        int len = e - s;

        //인덱스 1번부터 확인 시작
        for (int i = 1; i < n; i++) {
            //현재 저장된 끝의 위치보다 다음의 시작하는 점이 더 큰 경우
            // 새로운 길이가 추가되는 것임
            if (e < arr[i].start) {
                s = arr[i].start;
                e = arr[i].end;
                len += e - s;
                continue;
            }
            //현재 끝 위치보다 다음 끝 위치가 큰 경우
            //다음 끝값과 현재끝 값을 빼서 늘어난 길이를 추가해준다.
            //배열을 미리 정렬해두었기 때문에 가장 처음값은 변하지 않음.
            if (e < arr[i].end) {
                len += arr[i].end - e;
                e = arr[i].end;
            }
        }
        // 길이 값 리턴
        return len;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new Point[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[i] = new Point(x, y);
        }

        //입력받은 x,y 값 오름차순으로 배열 정렬
        Arrays.sort(arr, (a, b) -> {
            //시작위치가 같으면 끝값 비교해서 정렬
            if (a.start == b.start) {
                return Integer.compare(a.end, b.end);
            }
            //시작위치 비교 후 정렬
            return Integer.compare(a.start, b.start);
        });

        System.out.println(calculate());

    }
}
