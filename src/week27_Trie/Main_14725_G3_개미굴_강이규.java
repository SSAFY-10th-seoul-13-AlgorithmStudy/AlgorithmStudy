package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_14725_G3_개미굴_강이규 {

    static int N;
    static Node root = new Node("");
    static final String depthPrefix = "--";
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        // 입력받기
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());

            Node cur = root;
            while (d-- > 0) {
                String tk = st.nextToken();
                if (!cur.children.containsKey(tk)) {
                    cur.children.put(tk, new Node(tk));
                }
                cur = cur.children.get(tk);
            }
        }

        // 쓰기 - dfs
        Collection<Node> firsts = root.children.values();
        for (Node next : firsts) {
            makeRes(next, 0);
        }

        System.out.println(sb);
    }

    private static void makeRes(Node cur, int depth) {
        sb.append(repeat(depthPrefix, depth)).append(cur.s).append("\n");

        /*
        children을 Map으로 하고, keySet을 받아 정렬하는 경우
        Set이 아닌 다른 Collection으로 변경하는 데 추가로 (O(N), N = children 길이) 만큼 연산이 생기므로
        TreeMap을 사용
         */
        Collection<Node> children = cur.children.values();
        for (Node next : children) {
            makeRes(next, depth+1);
        }
    }

    // string.repeat은 자바 11부터
    private static String repeat(String s, int cnt) {
        StringBuilder localSb = new StringBuilder();
        while (cnt-- > 0) {
            localSb.append(depthPrefix);
        }
        return localSb.toString();
    }

    static class Node {
        String s;
        TreeMap<String, Node> children;

        public Node(String s) {
            this.s = s;
            this.children = new TreeMap<>();
        }
    }
}
