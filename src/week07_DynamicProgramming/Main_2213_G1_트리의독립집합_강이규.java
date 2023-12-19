package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2213_G1_트리의독립집합_강이규 {

    /*
    풀이 :
    1. max값 찾기
        - cur를 선택하면, adj는 선택할 수 없음
        - dp : dp[노드 인덱스][선택여부]
        - 점화식 :
            들어온 곳이 아닌 모든 adj들에 대해,
            dp[cur][0] = sum( max(dp[adj][0], dp[adj][1]) )
            dp[cur][1] = sum( dp[adj][0]) ) + cur 가중치

        +) 시작 노드도 선택한 경우와 선택X 인 경우를 둘 다 보게 되므로, 아무 리프노드에서나 시작해도 된다.

    2. 리스트 출력
        시작 노드부터 재귀
        - cur 선택한 경우 -> adj 선택 X로 재귀
        - cur 선택 X -> (adj, 선택O) 와 (adj, 선택X) 중 값이 큰 쪽으로 재귀
     */

    static int N;
    static int[] weights;
    static boolean[] visited;
    static List<Integer>[] adjList;
    static int[][] dp; // val <= 1억
    static StringBuilder sb;
    static List<Integer> res;

    public static void main(String[] args) throws IOException {
        init();
        // 리프인 아무 노드에서나 시작
        for (int i = 1; i <= N; i++) {
            if (adjList[i].size() == 1) {
                // dp 테이블 작성
                dfs(i);
                // max값 & 리스트 찾기
                if (dp[i][0] > dp[i][1]) {
                    sb.append(dp[i][0]).append("\n");
                    print(i, false);
                } else {
                    sb.append(dp[i][1]).append("\n");
                    print(i, true);
                }
                break;
            }
        }
        // 리스트 오름차순 출력
        Collections.sort(res);
        for (int i : res) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        weights = new int[N+1];
        visited = new boolean[N+1];
        adjList = new ArrayList[N+1];
        dp = new int[N+1][2];
        sb = new StringBuilder();
        res = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }

        // weights
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }
        // edges
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            adjList[start].add(end);
            adjList[end].add(start);
        }
    }

    private static void dfs(int cur) {
        visited[cur] = true;
        dp[cur][1] = weights[cur];

        for (int adj : adjList[cur]) {
            if (visited[adj]) continue;
            // adj의 dp테이블을 채워야 하므로, 재귀 먼저
            dfs(adj);
            // 점화식대로 실행
            dp[cur][0] += Math.max(dp[adj][0], dp[adj][1]);
            dp[cur][1] += dp[adj][0];
        }
        visited[cur] = false; // 리스트 출력 시 재활용하기
    }

    private static void print(int cur, boolean selected) {
        visited[cur] = true;
        if (selected) {
            res.add(cur);
            for (int adj : adjList[cur]) {
                if (visited[adj]) continue;
                print(adj, false);
            }
        } else {
            for (int adj : adjList[cur]) {
                if (visited[adj]) continue;
                if (dp[adj][0] > dp[adj][1])
                    print(adj, false);
                else
                    print(adj, true);
            }
        }
    }
}
