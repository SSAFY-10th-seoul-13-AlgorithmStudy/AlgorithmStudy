package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_16472_G4_고냥이_강이규 {

    static int N;
    static String line;
    static Map<Character, Integer> cnts;
    static int maxLen = 0;

    public static void main(String[] args) throws IOException {
        solve();
    }

    private static void solve() throws IOException {
        // init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        line = br.readLine();
        cnts = new HashMap<>();

        // solve
        // 처음껀 삽입
        int curLen = 1;
        cnts.put(line.charAt(0), 1);
        int l = 0;
        for (int r = 1, end = line.length(); r < end; r++) {
            char c = line.charAt(r);
            if (!cnts.containsKey(c)) {
                // 공간이 만들어질 때까지, left를 옮긴다.
                while (cnts.size() == N) {
                    char left = line.charAt(l++);
                    cnts.put(left, cnts.get(left) - 1);
                    if (cnts.get(left) == 0)
                        cnts.remove(left);
                    curLen--;
                }
                cnts.put(c, 1);
            } else {
                cnts.put(c, cnts.get(c) + 1);
            }
            maxLen = Math.max(++curLen, maxLen);
        }
        System.out.println(maxLen);
    }
}
