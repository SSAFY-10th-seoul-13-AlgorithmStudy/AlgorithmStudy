package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1135_G2_뉴스전하기_강이규 {

    static int N;
    static List<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(dfs(0) - 1); // 민식이한테 연락하는 비용은 제외
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjList = new ArrayList[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 민식이
        st.nextToken();
        adjList[0] = new ArrayList<>();
        // 나머지; 상사 adjList에 추가하고, 본인 부하 목록 담을 List 생성
        for (int i = 1; i < N; i++) {
            int sup = Integer.parseInt(st.nextToken());
            adjList[sup].add(i);
            adjList[i] = new ArrayList<>();
        }
    }

    /**
     * @return (자신 포함) 서브트리를 처리하는데 드는 시간
     */
    private static int dfs(int cur) {
        int subLen = adjList[cur].size();
        if (subLen == 0) return 1; // 리프 노드

        int minCost = subLen; // 가능한 cost의 최소값 : 부하들이 모두 리프 노드일 때
        Integer[] subCosts = new Integer[subLen];


        for (int i = 0; i < subLen; i++) {
            int sub = adjList[cur].get(i);
            subCosts[i] = dfs(sub);
        }

        Arrays.sort(subCosts, Collections.reverseOrder()); // 오래 걸리는 쪽부터 돈다.

        for (int i = 0; i < subLen; i++) {
            minCost = Math.max(minCost, subCosts[i] + i); // 같은 값이 있거나, 값에 비해 subLen이 매우 긴 반례 해결
        }
        return minCost + 1; // 자기 자신 포함
    }
}
