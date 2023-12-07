package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2263_G1_트리의순회_강이규 {

    /*
    풀이 :
    - postOrder 에서는 루트가 맨 뒤에 있으므로, 루트를 알 수 있다.
    - inOrder 에서는 루트 기준 양쪽 서브트리의 노드 수를 알 수 있다.
    - inOrder 에서 얻은 정보로 postOrder에서 양쪽 서브트리의 루트를 알 수 있다.
    위 3단계를 반복하며 루트를 출력한다.
     */

    static int N;
    static int[] inOrder;
    static int[] postOrder;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();
        recur(1, N, 1, N);
        System.out.println(sb);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        inOrder = new int[N+1];
        postOrder = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            inOrder[i] = Integer.parseInt(st.nextToken());
            postOrder[i] = Integer.parseInt(st2.nextToken());
        }
    }

    private static void recur(int is, int ie, int ps, int pe) {
        if (ps > pe) return;
        int root = postOrder[pe];
        // print preOrder
        sb.append(root).append(" ");

        // find left subtree size
        int rootIdx = 0;
        for (int i = 1; i <= N; i++) {
            if (inOrder[i] == root) {
                rootIdx = i;
                break;
            }
        }
        int lLen = rootIdx - is;
        int rLen = ie - rootIdx;

        // left recur
        recur(is, rootIdx-1, ps, ps+lLen-1);
        // right recur
        recur(rootIdx+1, ie, pe-rLen, pe-1);
    }
}
