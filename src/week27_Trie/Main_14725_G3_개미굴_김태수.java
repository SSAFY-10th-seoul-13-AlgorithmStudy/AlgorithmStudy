package week27_Trie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_14725_G3_개미굴_김태수 {
    static class Node {
        Map<String, Node> children;

        public Node() {
            this.children = new HashMap<>();
        }
    }

    static int N;
    static Node root = new Node();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            Node cur = root;
            for (int j = 0; j < K; j++) {
                String word = st.nextToken();
                if (!cur.children.containsKey(word)) {
                    cur.children.put(word, new Node());
                }
                cur = cur.children.get(word);
            }
        }
        print("", root);
        System.out.println(sb);
    }

    public static void print(String prefix, Node node) {
        ArrayList<String> list = new ArrayList<>(node.children.keySet());
        Collections.sort(list);
        for (String str : list) {
            sb.append(prefix).append(str).append("\n");
            print(prefix + "--", node.children.get(str));
        }
    }
}
