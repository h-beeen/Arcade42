import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static StringTokenizer st;
    static int n, k;
    static int[] coins;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(bf.readLine());

//      입력파트
        n= Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        coins = new int[n];
        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(bf.readLine());
        }

        int coinCount = 0; // 동전 갯수

        int sum = 0; //
//       그리디
        while(sum < k){  //
            //      총합에 넣을수 있는 동전 중 가장 비싼 동전을 넣음
            for (int i = n-1; i >= 0; i--) {
                if (sum + coins[i] <= k) {
                    sum += coins[i];
                    coinCount++;

                    
                    break;
                }
            }
        }

        System.out.println(coinCount);

    }
}
