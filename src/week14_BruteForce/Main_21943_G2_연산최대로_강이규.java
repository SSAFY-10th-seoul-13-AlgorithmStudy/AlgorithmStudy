package week14_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_21943_G2_연산최대로_강이규 {

    static int N, M, P, Q;
    static int[] input;
    static int[] nums;
    static int max = Integer.MIN_VALUE;
    static int[] selected;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        init();
        makeInput(0);
        System.out.println(max);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = N - 1;
        input = new int[N];
        nums = new int[N];
        visited = new boolean[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        selected = new int[Q];
    }

    static void makeInput(int depth) {
        if (depth == N) {
            selectQIdx(0, 0);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                input[depth] = nums[i];
                visited[i] = true;
                makeInput(depth+1);
                visited[i] = false;
            }
        }
    }

    static void selectQIdx(int start, int depth) {
        if (depth == Q) {
            calc();
            return;
        }
        for (int i = start; i < M; i++) {
            selected[depth] = i;
            selectQIdx(i+1, depth+1);
        }
    }

    static void calc() {
        int res = 1;
        int inBracket = 0;
        int inputPtr = 0;
        int selectedPtr = 0;
        // 마지막 - 1 괄호까지
        while (selectedPtr < Q) {
            inBracket += input[inputPtr];
            if (inputPtr == selected[selectedPtr]) {
                res *= inBracket;
                inBracket = 0;
                selectedPtr++;
            }
            inputPtr++;
        }
        // 마지막
        while (inputPtr < N) {
            inBracket += input[inputPtr++];
        }
        res *= inBracket;
        max = Math.max(max, res);
    }
}