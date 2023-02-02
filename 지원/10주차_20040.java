import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 백준 20040번 사이클 게임 : https://www.acmicpc.net/problem/20040

public class Main {
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int M;
    static int[] parent; // 부모 노드 값
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i; // init
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (isCycleMade(A, B)) {
                answer = i + 1;
                break;
            }
        }

        System.out.println(answer);
    }

    private static boolean isCycleMade(int a, int b) {
        int parentA = getParentNodeValue(a);
        int parentB = getParentNodeValue(b);

        if (parentA == parentB) {
            return true;
        } else {
            unionParent(parentA, parentB);
            return false;
        }
    }

    private static int getParentNodeValue(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = getParentNodeValue(parent[x]);
    }

    private static void unionParent(int parentA, int parentB) {
        if (parentA < parentB) {
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }
    }
}

/*
6 5
0 1
1 2
2 3
5 4
0 4
>> 0

6 5
0 1
1 2
1 3
0 3
4 5
>> 4
 */