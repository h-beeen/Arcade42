import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
# BJ 11399 - ATM


총 시간  : 이번 사람이 걸리는 시간의 총합
이번 사람이 걸리는 시간 : 이전까지 걸린 시간 + 지금 사람 수행시간
*/

public class Main {
    static StringTokenizer st;
    static int n;
    static int[] pTime;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

//      입력파트
        n= Integer.parseInt(bf.readLine());

        st = new StringTokenizer(bf.readLine());
        pTime = new int[n];
        for (int i = 0; i < n; i++) {
            pTime[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(pTime);

        int totalTime = 0;
        int beforeTime = 0;

        for (int i = 0; i < n; i++) {
            totalTime += beforeTime + pTime[i];
            beforeTime += pTime[i];

        }
        System.out.println(totalTime);
    }
}
