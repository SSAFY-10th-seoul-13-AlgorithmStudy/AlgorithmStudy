package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_25381_G3_ABBC_강이규 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();

        int N = arr.length;
        int result = 0;

        int aCnt = 0;
        Stack<Integer> cPos = new Stack<>();

        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] == 'C') cPos.push(i);
        }

        for (int i = 0; i < N; i++) {
            if (arr[i] == 'A') {
                aCnt++;
                continue;
            }
            if (arr[i] != 'B') continue;
            while (!cPos.isEmpty() && cPos.peek() < i) {
                cPos.pop();
            }
            if (!cPos.isEmpty()) {
                cPos.pop();
                result++; // 뒤의 C랑 매칭
                continue;
            }
            // 매칭되는 C를 못 찾았다면
            if (aCnt > 0) {
                // 앞에 남은 a가 있다면, 매칭
                result++;
                aCnt--;
            }
        }
        System.out.println(result);
    }
}