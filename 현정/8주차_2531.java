import java.io.*;
import java.util.*;

public class BJ_2531 {
    static int n;
    static int d;
    static int k;
    static int c;

    static int[] arr;

    static int max = 0;
    static int start = 0;
    static int end;
    static HashSet<Integer> dish = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new int[n];
        end = k;

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        while (start < n) {
            if (end > n) {
                for (int i = start; i < n; i++) {
                    dish.add(arr[i]);
                }
                for (int i = 0; i < end - n; i++) {
                    dish.add(arr[i]);
                }
            }
            else {
            for (int i = start; i < end; i++) {
                dish.add(arr[i]);
                }
            }

            if (max <= dish.size()) {
                if (dish.contains(c))
                    max = dish.size();
                else max = dish.size() + 1;
            }
            dish.clear();
            start++;
            end++;
        }

        System.out.println(max);
    }
}
