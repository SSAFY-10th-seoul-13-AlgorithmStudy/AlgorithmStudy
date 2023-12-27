package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2011_G5_암호코드_강이규 {

    /*
    풀이 :
    각 idx에서, 가능한 경우 =
        1) 현재 숫자만 알파벳으로 바꾸거나,
        2) 현재 + 다음 숫자를 알파벳으로 바꾸거나 (26 이하라면)
        +) 현재가 0이면, 그 뒤로 가능한 경우가 없으므로 0 리턴
     */
    static int[] input;
    static int len;
    static int lastIdx;
    static long[] dp;
    static final int MOD = 1_000_000;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(recur(0));
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        len = line.length();
        lastIdx = len - 1;

        input = new int[len];
        dp = new long[len];
        for (int i = 0; i < len; i++) {
            input[i] = line.charAt(i) - '0';
        }
    }

    private static long recur(int idx) {
        // 끝까지 갔을 때
        if (idx > lastIdx) return 1;
        if (idx == lastIdx && input[idx] != 0) return 1;

        int cur = input[idx];
        if (dp[idx] != 0) return dp[idx]; // 이미 방문한 경우
        if (cur == 0) return 0;
        // 첫 방문
        dp[idx] = recur(idx+1);
        dp[idx] %= MOD;
        if (cur < 2 || (cur == 2 && input[idx+1] < 7)) {
            dp[idx] += recur(idx + 2);
        }
        dp[idx] %= MOD;
        return dp[idx];
    }
}
