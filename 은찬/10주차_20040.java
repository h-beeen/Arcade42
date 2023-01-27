import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int[] arr;

    static int find(int a) {
        if (a == arr[a]) return a;
        else return arr[a] = find(arr[a]);
    }

    static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa != fb) {
            arr[fa] = fb;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }

        int answer = 0;
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            cnt++;
            if (find(a) == find(b)) {
                answer = cnt;
                break;
            }
            union(a, b);
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}