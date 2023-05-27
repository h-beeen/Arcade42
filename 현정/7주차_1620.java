import java.io.*;
import java.util.*;

public class BJ_1620 {
    static int n;
    static int m;

    //해시맵 두 개 선언
    // dictionary는 <키 밸류>
    // dictionaryR은 <밸류 키>
    static HashMap<Integer, String> dictionary = new HashMap<Integer, String>();
    static HashMap<String, Integer> dictionaryR = new HashMap<String, Integer>();

    static String[] quiz;

    // 숫자인지 판별하는 함수
    static boolean isNumber(String str) {
        if(str.isEmpty()) return false;
        // str 값이 integer 인 경우 true 리턴
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            // 아닌경우 false 리턴
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        quiz = new String[m];

        // dictionary에 값 입력받기
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            dictionary.put(i + 1, name);
            dictionaryR.put(name, i + 1);
        }

        // 문제 입력받기
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            quiz[i] = st.nextToken();
        }

        // 문제가 숫자라면 밸류를 키로 저장한 dictionaryR에서 밸류 가져오기
        // 그렇지 않으면 dictionary에서 밸류 가져오기
        for (int i = 0; i < m; i++) {
            if (!isNumber(quiz[i])) {
                System.out.println(dictionaryR.get(quiz[i]));
            }
            else System.out.println(dictionary.get(Integer.parseInt(quiz[i])));

        }
    }
}
