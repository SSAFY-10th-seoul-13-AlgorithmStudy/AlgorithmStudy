package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_9470_G3_Strahler순서_강이규 {

    static int K, M, P;
    static List<Integer>[] inDegree;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            // init
            st = new StringTokenizer(br.readLine());

            K = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());

            inDegree = new ArrayList[M + 1];
            for (int i = 1; i <= M; i++) {
                inDegree[i] = new ArrayList<>();
            }

            dp = new int[M + 1];

            // 간선 정보 받기
            for (int i = 0; i < P; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                inDegree[b].add(a);
            }

            sb.append(K).append(" ").append(dfs(M)).append("\n");
        }
        System.out.print(sb);
    }

    private static int dfs(int cur) {
        if (dp[cur] != 0) return dp[cur];
        if (inDegree[cur].isEmpty()) return dp[cur] = 1;

        int max = 0;
        boolean duplicated = false;

        for (int pre : inDegree[cur]) {
            int preRes = dfs(pre);
            if (preRes > max) {
                max = preRes;
                duplicated = false;
            } else if (preRes == max) {
                duplicated = true;
            }
        }
        return dp[cur] = duplicated ? max + 1 : max;
    }
}
