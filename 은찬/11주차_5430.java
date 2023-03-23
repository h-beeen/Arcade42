import java.io.*;
import java.util.*;

public class Main {
    static int n;

    static void run(char[] c, int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int a : arr) list.add(a);
        boolean isReversed = false;
        for (char command : c) {
            if (command == 'R') {
                isReversed = !isReversed;
            }
            if (command == 'D') {
                if (isReversed) {
                    if (list.isEmpty()) {
                        System.out.println("error");
                        return;
                    }
                    list.remove(list.size() - 1);
                } else {
                    if (list.isEmpty()) {
                        System.out.println("error");
                        return;
                    }
                    list.remove(0);
                }
            }
        }
        StringBuilder result = new StringBuilder("[");
        if (!isReversed) {
            for (int i = 0; i < list.size(); i++) {
                result.append(list.get(i));
                if (i != list.size() - 1) result.append(",");
            }
        } else {
            for (int i = list.size() - 1; i >= 0; i--) {
                result.append(list.get(i));
                if (i != 0) result.append(",");
            }
        }
        result.append("]");
        System.out.println(result);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            String command = br.readLine();
            char[] c = command.toCharArray();
            int m = Integer.parseInt(br.readLine());
            int[] arr = new int[m];
            String str = br.readLine();
            str = str.replace("[", "");
            str = str.replace("]", "");
            String[] split = str.split(",");
            for (int j = 0; j < m; j++) {
                arr[j] = Integer.parseInt(split[j]);
            }
            run(c, arr);
        }

        br.close();
    }
}
