import java.io.*;
import java.util.*;

public class BJ_19583 {

    static String s;
    static String e;
    static String q;

    static int cnt = 0;

    static Set<String> user = new HashSet<>();
    static Set<String> attend = new HashSet<>();

    /*
        compareTo()함수란?
        두개의 값을 비교하여 int값으로 반환해주는 함수

        숫자 비교의 경우 크면 1, 같으면 0, 작으면 -1 리턴
        문자열 비교의 경우 같으면 0, 그 외 양수/음수 값으로 리턴
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        s = st.nextToken();
        e = st.nextToken();
        q = st.nextToken();

        String input;
        // 인풋이 null이 들어올 떄 까지 입력 받기
        while ((input = br.readLine()) != null) {
            String[] inputArr = input.split(" ");
            String time = inputArr[0];
            String name = inputArr[1];
            //총회 시작전 & 시작 떄 채팅한 인원에 대한 이름 추가
            if (time.compareTo(s) <= 0) {
                user.add(name);
            }
            //총회 스트리밍 끝나고, 총회 끝난 이후에 채팅 친 인원 이름 추가
            else if (time.compareTo(e) >= 0 && time.compareTo(q) <= 0) {
                attend.add(name);
            }
        }

        //입장 확인된 사람들 중 끝까지 있었던 사람들 검색해서 cnt++
        for (String s : user) {
            if(attend.contains(s))
                cnt++;
        }

        System.out.println(cnt);

    }
}
