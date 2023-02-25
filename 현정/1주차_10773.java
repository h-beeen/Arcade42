import java.io.*;
import java.util.ArrayList;

public class BJ_10773 {
    ArrayList<Integer> arrayList = new ArrayList<>();

    void push(int x){
        arrayList.add(x);
    }

    void pop(){
        arrayList.remove(arrayList.size()-1);
    }

    int size(){
       return arrayList.size();
    }

    int top(int i){
        return arrayList.get(i);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BJ_10773 m = new BJ_10773();

        int sum = 0;

        int n = Integer.parseInt(br.readLine());
        for (int i=0;i<n;i++){
            int x = Integer.parseInt(br.readLine());
            if(x==0)
                m.pop();
            else m.push(x);
        }
        for(int i=0;i<m.size();i++){
            sum += m.top(i);
        }
        System.out.println(sum);
    }
}
