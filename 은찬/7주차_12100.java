import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int max = Integer.MIN_VALUE;
    static void dfs(int level, int[][] map, int k) {
        if (level == 5) return;
        int[][] arr = new int[n][n];
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                arr[y][x] = map[y][x];
            }
        }

        move(arr, k);
        for (int i = 1; i <= 4; i++) {
            dfs(level + 1, arr, i);
        }
    }

    static void move(int[][] arr, int k) {
        if (k == 1) {
            for (int x = 0; x < n; x++) {
                Stack<Integer> stack = new Stack<>();
                boolean db = false;
                for (int y = 0; y < n; y++) {
                    if (!stack.isEmpty() && stack.peek() == arr[y][x] && !db) {
                        int p = stack.pop();
                        stack.push(p + arr[y][x]);
                        db = true;
                    } else if (arr[y][x] != 0) {
                        stack.push(arr[y][x]);
                        db = false;
                    }
                }
                int t = 0;
                for (int p : stack) {
                    max = Math.max(max, p);
                    arr[t][x] = p;
                    t++;
                }
                for (int y = t; y < n; y++) {
                    arr[y][x] = 0;
                }
            }
        } else if (k == 2) {
            for (int y = 0; y < n; y++) {
                Stack<Integer> stack = new Stack<>();
                boolean db = false;
                for (int x = 0; x < n; x++) {
                    if (!stack.isEmpty() && stack.peek() == arr[y][x] && !db) {
                        int p = stack.pop();
                        stack.push(p + arr[y][x]);
                        db = true;
                    } else if (arr[y][x] != 0) {
                        stack.push(arr[y][x]);
                        db = false;
                    }
                }
                int t = 0;
                for (int p : stack) {
                    max = Math.max(max, p);
                    arr[y][t] = p;
                    t++;
                }
                for (int x = t; x < n; x++) {
                    arr[y][x] = 0;
                }
            }
        } else if (k == 3) {
            for (int x = 0; x < n; x++) {
                Stack<Integer> stack = new Stack<>();
                boolean db = false;
                for (int y = n - 1; y >= 0; y--) {
                    if (!stack.isEmpty() && stack.peek() == arr[y][x] && !db) {
                        int p = stack.pop();
                        stack.push(p + arr[y][x]);
                        db = true;
                    } else if (arr[y][x] != 0) {
                        stack.push(arr[y][x]);
                        db = false;
                    }
                }
                int t = n - 1;
                for (int p : stack) {
                    max = Math.max(max, p);
                    arr[t][x] = p;
                    t--;
                }
                for (int y = t; y >= 0; y--) {
                    arr[y][x] = 0;
                }
            }
        } else if (k == 4) {
            for (int y = 0; y < n; y++) {
                Stack<Integer> stack = new Stack<>();
                boolean db = true;
                for (int x = n - 1; x >= 0; x--) {
                    if (!stack.isEmpty() && stack.peek() == arr[y][x] && !db) {
                        int p = stack.pop();
                        stack.push(p + arr[y][x]);
                        db = true;
                    } else if (arr[y][x] != 0) {
                        stack.push(arr[y][x]);
                        db = false;
                    }
                }
                int t = n - 1;
                for (int p : stack) {
                    max = Math.max(max, p);
                    arr[y][t] = p;
                    t--;
                }
                for (int x = t; x >= 0; x--) {
                    arr[y][x] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        for (int y = 0; y < n; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < n; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= 4; i++) {
            dfs(0, map, i);
        }

        bw.write(String.valueOf(max));

        br.close();
        bw.close();
    }
}