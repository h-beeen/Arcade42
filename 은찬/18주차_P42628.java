import java.io.*;
import java.util.*;

public class Main {

    public int[] solution(String[] operations) {
        int[] answer = new int[2];

        List<Integer> list = new ArrayList<>();

        for (String operation : operations){
            if (operation.equals("D 1")){
                if (!list.isEmpty()){
                    list.remove(list.size() - 1);
                }
            } else if (operation.equals("D -1")){
                if (!list.isEmpty()){
                    list.remove(0);
                }
            }
            else {
                String[] split = operation.split(" ");
                int num = Integer.parseInt(split[1]);
                list.add(num);
                list.sort(Integer::compare);
            }
        }

        if (!list.isEmpty()){
            answer[0] = list.get(list.size() - 1);
            answer[1] = list.get(0);
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        br.close();
        bw.close();
    }