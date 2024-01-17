package week10_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_16638_G1_괄호추가하기2_김태수 {
	static int N, op, ans = Integer.MIN_VALUE;
    static char[] exp;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        exp = br.readLine().toCharArray();
        op = N / 2;

        if (N == 1) System.out.println(exp[0]);
        else {
            dfs(0, "");
            System.out.println(ans);
        }
    }

    public static void dfs(int n, String str) {
        if (n >= op) {
            char c = str.charAt(str.length() - 1);
            if (c != ')') str += exp[N - 1];

            int val = calc(str.toCharArray());
            if (ans < val) ans = val;

            return;
        }

        StringBuilder tmp1 = new StringBuilder(str);
        tmp1.append(exp[2 * n]).append(exp[2 * n + 1]);
        dfs(n + 1, tmp1.toString());

        StringBuilder tmp2 = new StringBuilder(str);
        tmp2.append('(');
        tmp2.append(exp[2 * n]).append(exp[2 * n + 1]).append(exp[2 * n + 2]);
        tmp2.append(')');
        if (n + 1 < op) tmp2.append(exp[2 * n + 3]);
        dfs(n + 2, tmp2.toString());
    }

    public static int calc(char[] str) {
        Stack<Character> opStack = new Stack<>();
        Stack<Integer> numStack = new Stack<>();

        top: 
        for (int i = 0; i < str.length; i++) {
            if ('0' <= str[i] && str[i] <= '9') {
                numStack.push(str[i] - '0');
            } else {
                if (str[i] == '(') opStack.push(str[i]);
                else if (str[i] == ')') {
                    while (opStack.peek() != '(') {
                        int n1 = numStack.pop();
                        int n2 = numStack.pop();
                        numStack.push(calc(n2, n1, opStack.pop()));
                    }
                    opStack.pop();
                } else {
                    boolean in = false;
                    while (!in) {
                        if (opStack.size() == 0) {
                            opStack.push(str[i]);
                            continue top;
                        }
                        char c = opStack.peek();
                        if (c == '(' || (c != '*' && str[i] == '*')) {
                            opStack.push(str[i]);
                            in = true;
                        } else {
                            int n1 = numStack.pop();
                            int n2 = numStack.pop();
                            numStack.push(calc(n2, n1, opStack.pop()));
                        }
                    }
                }
            }
        }

        while (!opStack.isEmpty()) {
            int n1 = numStack.pop();
            int n2 = numStack.pop();
            numStack.push(calc(n2, n1, opStack.pop()));
        }

        return numStack.pop();
    }

    public static int calc(int n1, int n2, char op) {
        switch (op) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
            default:
                return 0;
        }
    }
}
