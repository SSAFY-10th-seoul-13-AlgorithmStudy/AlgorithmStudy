package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Main_16638_G1_괄호추가하기2_강이규 {

    static int N;
    static Formula[] input;
    static boolean[] bracket;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        recur(1);
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
            if (c == '+' || c == '-') {
                input[i] = new Formula(c, 3);
            } else if (c == '*') {
                input[i] = new Formula(c, 2);
            } else {
                input[i] = new Formula(c, 0);
            }
        }
    }

    private static void recur(int idx) {
        if (idx >= N) {
            List<Formula> converted = convert();
            int res = calculate(converted);
            max = Math.max(max, res);
            return;
        }
        if (idx == 1) {
            bracket[idx] = true;
            recur(idx+2);
            bracket[idx] = false;
        } else {
            if (!bracket[idx-2]) { // 괄호 가능한 경우, 치고 재귀
                bracket[idx] = true;
                recur(idx+2);
                bracket[idx] = false;
            }

        }
        recur(idx+2);
    }

    private static List<Formula> convert() {
        List<Formula> res = new ArrayList<>();
        Stack<Formula> s = new Stack<>();

        for (int i = 0; i < N; i++) {
            if (input[i].p == 0) {
                res.add(input[i]);
                continue;
            }
            int p = bracket[i] ? 1 : input[i].p;

            while (!s.isEmpty() && s.peek().p <= p) {
                res.add(s.pop());
            }
            s.push(new Formula(input[i].c, p));
        }
        while (!s.isEmpty()) {
            res.add(s.pop());
        }
        return res;
    }

    private static int calculate(List<Formula> converted) {
        Stack<Integer> s = new Stack<>();
        int res = 0;
        for (int i = 0; i < N; i++) {
            Formula f = converted.get(i);
            if (f.p == 0) {
                s.push(f.c - '0');
                continue;
            }
            int num1 = s.pop();
            int num2 = s.pop();
            res = operate(num2, num1, f.c);
            s.push(res);
        }
        return s.pop();
    }

    private static int operate(int a, int b, char op) {
        int res = 0;
        switch (op) {
            case '+':
                res = a + b;
                break;
            case '-':
                res = a - b;
                break;
            case '*':
                res = a * b;
                break;
        }
        return res;
    }

    static class Formula {
        char c;
        int p;
        Formula(char c, int p) {
            this.c = c;
            this.p = p;
        }
    }
}
