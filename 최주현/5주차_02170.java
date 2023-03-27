import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] drawLines;
    static final int x = 0, y = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bf.readLine());

        drawLines = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            drawLines[i][x] = Integer.parseInt(st.nextToken());
            drawLines[i][y] = Integer.parseInt(st.nextToken());
        }
//        ~~ 여기까지 입력 파트 ~~

//        2차원 배열 정렬해주는 클래스, 오름차순 정렬
        Arrays.sort(drawLines, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return Integer.compare(o1[1],o2[1]);
            } else {
                return Integer.compare(o1[0],o2[0]);
            }
        });

//        그리디
        int totalLength = drawLines[0][y] - drawLines[0][x];
        int preX = drawLines[0][x];
        int preY = drawLines[0][y];
//            이전 선과 지금 선을 비교해서
        for (int i = 1; i < n; i++) {
//            선이 완전히 겹치는 경우 continue
            if(drawLines[i][x] >= preX && drawLines[i][y] <= preY) continue;
//            지금 선 시점이 이전 선 종점보다 큰 경우
//            총길이 += 지금 선 길이
            if (drawLines[i][x] > preY) {
                totalLength += drawLines[i][y] - drawLines[i][x];
//            지금 선 시점이 이전 선 종점보다 작거나 같은 경우
//            총길이 += 지금 선 종점 - 이전 선 종점
            } else {
                totalLength += drawLines[i][y] - preY;
            }
            preX = drawLines[i][x];
            preY = drawLines[i][y];
        }
        System.out.println(totalLength);
    }
}
