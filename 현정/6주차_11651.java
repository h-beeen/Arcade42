import java.io.*;
import java.util.*;

public class BJ_11651 {
    static int n;
    static Point[] arr;

    static class Point{
        int y; int x;
        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new Point[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            arr[i] = new Point(y, x);
        }

        Arrays.sort(arr, (a, b) -> {

            if (a.x == b.x) {
                return Integer.compare(a.y, b.y);
            }
            return Integer.compare(a.x, b.x);
        });

        for(Point p : arr){
            System.out.println(p.y + " " + p.x);
        }

    }

}
