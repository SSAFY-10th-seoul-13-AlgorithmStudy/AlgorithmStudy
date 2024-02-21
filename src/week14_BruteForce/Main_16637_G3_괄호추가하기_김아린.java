import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer> list;
    static ArrayList<Character> operator;
    static int N;
    static long ans = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        list = new ArrayList<Integer>();
        operator = new ArrayList<Character>();

        String input = br.readLine();
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                list.add(input.charAt(i) - '0');
            } else {
                operator.add(input.charAt(i));
            }
        }
        // 완탐
        dfs(0, list.get(0));
        System.out.println(ans);
    }

    private static void dfs(int opIdx, int sum) {
        if (opIdx >= operator.size()) {
            ans = Math.max(ans, sum);
            return;
        }

      // 1. 괄호를 사용하지 않는 경우
      // 현재 연산자와 다음 숫자를 이용해 계산
        int withoutBracket = calc(operator.get(opIdx), sum, list.get(opIdx + 1));
      // 다음으로 넘어감
        dfs(opIdx + 1, withoutBracket);

      // 2. 괄호를 사용하는 경우 (다음 연산자가 있어야 함)
        if (opIdx < operator.size() - 1) {
          // 다음 연산자와 그 다음 숫자를 이용해 먼저 계산
            int calcInBracket = calc(operator.get(opIdx + 1), list.get(opIdx + 1), list.get(opIdx + 2));
          // 현재 연산자와 괄호 계산 결과를 이용해 계산
            int withBracket = calc(operator.get(opIdx), sum, calcInBracket);
          // 다음 연산자로 넘어감
            dfs(opIdx + 2, withBracket);
        }
    }

    private static int calc(char op, int a, int b) {
        switch (op) {
        case '+':
            return a + b;
        case '-':
            return a - b;
        case '*':
            return a * b;
        }
        return 0;
    }
}
