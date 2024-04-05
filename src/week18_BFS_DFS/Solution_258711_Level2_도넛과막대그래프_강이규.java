package programmers.kakao;

import java.util.*;
import java.io.*;

class Solution_258711_Level2_도넛과막대그래프_강이규 {

    static int[] inDegree;
    static int[] outDegree;
    static int N = 1_000_001;
    static int end;
    static Set<Integer> nodes;

    public int[] solution(int[][] edges) {
        init(edges);
        return solve();
    }

    private int[] solve() {
        int start = -1;
        int barN = 0;
        int donutN = 0;
        int palN = 0;
        int totalN = 0;

        // for (int i = 1; i <= end; i++) {
        for (Integer i : nodes) {
            if (outDegree[i] == 0) {
                barN++;
                continue;
            }
            if (outDegree[i] >= 2) {
                if (inDegree[i] == 0) {
                    start = i;
                    totalN = outDegree[i];
                    continue;
                }
                palN++;
            }
        }
        donutN = totalN - barN - palN;
        return new int[]{start, donutN, barN, palN};
    }

    private void init(int[][] edges) {
        nodes = new HashSet<>();
        inDegree = new int[N];
        outDegree = new int[N];

        for (int[] edge : edges) {
            int s = edge[0];
            int e = edge[1];
            outDegree[s]++;
            inDegree[e]++;

            nodes.add(s);
            nodes.add(e);

            end = Math.max(end, s);
            end = Math.max(end, e);
        }
    }

}