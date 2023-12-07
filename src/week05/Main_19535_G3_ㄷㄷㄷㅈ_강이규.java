package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_19535_G3_ㄷㄷㄷㅈ_강이규 {

    /*
    풀이 :
    1. ㄷ자 트리 구하기
        간선 하나씩 순회(=인접리스트 2차원 순회)하면서, 해당 간선을 가운데 간선으로 하는 ㄷ자를 구한다.
         = 현재 선택한 간선이 a-b를 잇는 간선일 때, (inDegrees[a] - 1) * (inDegrees[b] - 1) 이다.
         - 리프 노드가 포함되면 한쪽 값이 0일 것이므로, 자동으로 제외된다.
         - edges는 같은 경우를 두번 진행하는 것(ex. 2-3, 3-2) 방지를 위해 사용

    2. ㅈ자 트리 구하기
        노드 순회하면서, inDegrees[node] C 3
     */

    static long dCnt = 0L;
    static long gCnt = 0L;
    static int N;
    static List<Edge> edges;
    static long[] inDegrees;

    public static void main(String[] args) throws IOException {
        init();
        countD();
        countG();

        // 계산
        gCnt *= 3;
        if (dCnt > gCnt) System.out.println("D");
        else if (gCnt > dCnt) System.out.println("G");
        else System.out.println("DUDUDUNGA");
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        edges = new ArrayList<>(N-1);

        inDegrees = new long[N+1];

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            edges.add(new Edge(a, b));
            inDegrees[a]++;
            inDegrees[b]++;
        }
    }

    private static void countD() {
        for (Edge e : edges) {
            dCnt += (inDegrees[e.a] - 1) * (inDegrees[e.b] - 1);
        }
    }

    private static void countG() {
        for (int i = 1; i <= N; i++) {
            long cur = inDegrees[i];
            if (cur < 3) continue;
            gCnt += (cur * (cur - 1) * (cur - 2)) / 6;
        }
    }

    static class Edge {
        int a, b;
        Edge(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
