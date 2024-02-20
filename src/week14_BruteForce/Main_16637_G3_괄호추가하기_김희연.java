import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<Integer> arr;
    static List<Character> opr;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new ArrayList<>();
        opr = new ArrayList<>();

        String str = br.readLine();
        for(int i=0; i<n; i++){
            char c = str.charAt(i);
            if(i%2 == 0){
                arr.add(c-'0');
            } else{
                opr.add(c);
            }
        }

        dfs(0, arr.get(0));

        System.out.println(max);
    }

    static void dfs(int depth, int num){
        if(depth >= opr.size()){
            max = Math.max(max, num);
            return;
        }

        // 괄호 X
        int result = calc(opr.get(depth), num, arr.get(depth+1));
        dfs(depth+1, result);

        // 괄호 O
        if(depth+1 < opr.size()){
            result = calc(opr.get(depth+1), arr.get(depth+1), arr.get(depth+2));
            dfs(depth+2, calc(opr.get(depth), num, result));
        }
    }

    static int calc(char op, int a, int b) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return -1;
    }
}
