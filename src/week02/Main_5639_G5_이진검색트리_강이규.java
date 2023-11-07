package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_5639_G5_이진검색트리_강이규 {

    static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();

        // 트리 만들기
        Node start = new Node(Integer.parseInt(line));
        Node pre = start;
        while ((line = br.readLine()) != null) {
            if(line.equals("")) break;
            int input = Integer.parseInt(line);
            Node cur = start;

            while (cur != null) {
                pre = cur;
                cur = input < cur.value ? cur.left : cur.right;
            }
            cur = new Node(input);
            if (cur.value < pre.value) pre.left = cur;
            else pre.right = cur;
        }
        // 후위순회
        reverseOrder(start);
        System.out.println(sb);
    }

    static void reverseOrder(Node cur) {
        // return
        if (cur == null) return;
        // left
        reverseOrder(cur.left);
        // right
        reverseOrder(cur.right);
        // cur
        sb.append(cur.value).append("\n");
    }
}