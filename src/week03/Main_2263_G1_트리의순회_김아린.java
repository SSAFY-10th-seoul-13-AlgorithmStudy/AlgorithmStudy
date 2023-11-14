package week03;

import java.io.*;
import java.util.*;

public class Main_2263_G1_트리의순회_김아린 {
    static class Node {
        int value;
        Node left;
        Node right;

        public Node() {
        }
    }

    static Node root;
    static int[] in, post;
    public static void main(String[] args) throws Exception{
    	//어려워...
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = null;

        root = null;
        //인오더(중위순회)
        st = new StringTokenizer(br.readLine());

        in = new int[N+1];
        for (int i = 1; i <= N; i++) {
            in[Integer.parseInt(st.nextToken())] = i;
        }

        //포스트오더(후위순회)
        post = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            post[i] = Integer.parseInt(st.nextToken());
        }
        findTree(1, N, 1, N);


        //근데 중위와 후위순회를 같이 주는 이유가 뭘까????
        // 전위 순회와 중위 순회를 알 때 후위 순회를 구할 수 있지만 전위 순회와 후위 순회를 알 때 중위 순회는 구할 수 없다.
    }

    private static void findTree(int leftIn, int rightIn, int leftPost, int rightPost) {
        if(leftIn > rightIn || leftPost > rightPost) {
            return;
        }

        int root = post[rightPost];
        System.out.print(root + " ");
        int idx = in[root];
        int cnt = idx - leftIn;

        //왼쪽 서브트리
        findTree(leftIn, idx-1, leftPost, (leftPost+cnt) - 1);

        //오른쪽 서브트리
        findTree(idx+1, rightIn, leftPost + cnt, rightPost-1);
    }
}
