package week14_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main_16637_G3_괄호추가하기_강이규 {

    static int N;
    static Formula[] input;
    static boolean[] bracket;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        recur(1); // 괄호는 연산자의 우선순위를 변경하므로, 1(연산자)부터 재귀
        System.out.println(max);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        input = new Formula[N];
        bracket = new boolean[N];

        String line = br.readLine();
        for (int i = 0; i < N; i++) {
            char c = line.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                input[i] = new Formula(c, 2);
            } else {
                input[i] = new Formula(c, 0);
            }
        }
    }

    private static void recur(int idx) {
        if (idx >= N) {
            // 계산 & 업데이트
            List<Formula> postfix = convert();
            max = Math.max(max, calculate(postfix));
            return;
        }
        if (idx == 1) {
            bracket[idx] = true;
            recur(idx+2);
            bracket[idx] = false;
        } else {
            if (!bracket[idx-2]) {
                bracket[idx] = true;
                recur(idx+2);
                bracket[idx] = false;
            }
        }
        recur(idx+2);
    }

    private static int calculate(List<Formula> postfix) {
        Stack<Integer> s = new Stack<>();
        int res = 0;
        for (int i = 0; i < N; i++) {
            Formula cur = postfix.get(i);
            if (cur.p == 0) {
                s.push(cur.c - '0');
                continue;
            }
            int n1 = s.pop();
            int n2 = s.pop();
            s.push(operate(n2, n1, cur.c));
        }
        return s.pop();
    }

    private static int operate(int a, int b, char op) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
        }
        return -999; // 나올 일 없음
    }

    /**
     * infix to postfix
     */
    private static List<Formula> convert() {
        Stack<Formula> s = new Stack<>();
        List<Formula> res = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            if (input[i].p == 0) {
                res.add(input[i]);
                continue;
            }
            // 연산자인 경우
            // 괄호 있으면 우선순위 up
            int p = bracket[i] ? 1 : input[i].p;

            while (!s.isEmpty() && s.peek().p <= p) {
                res.add(s.pop());
            }
            s.push(new Formula(input[i].c, p)); // input이 변경되면 안되므로, 재활용 못함
        }
        while (!s.isEmpty()) {
            res.add(s.pop());
        }
        return res;
    }


    /**
     * char c : num or op
     * int p : 우선순위
     */
    static class Formula {
        char c;
        int p;
        Formula(char c, int p) {
            this.c = c;
            this.p = p;
        }
    }
}
