package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2661_G4_좋은수열_강이규 {
    /*
    백트래킹
    next = 1,2,3 중, 좋은 수열을 만족하는 값만 탐색
    만족 여부 체크에서 여러번의 비교가 필요하지만, 일부는 이전에 이미 체크된 부분
     */

    static int N;
    static int[] selected;

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 1; i <= 3; i++) {
            selected[0] = i;
            dfs(0);
        }
    }

    private static void dfs(int idx) {
        if (idx == N - 1) {
            print();
            System.exit(0);
        }
        System.out.println(Arrays.toString(selected));

        for (int i = 1; i <= 3; i++) {
            selected[idx + 1] = i;
            if (!isValid(idx + 2)) continue;
            dfs(idx + 1);
        }
    }

    private static boolean isValid(int newSelectedSize) {
        int maxSize = newSelectedSize / 2;

        // 자기를 포함하는 길이 (1 ~ maxSize)(*2)짜리 중복 수열이 생기는지 체크
        int nextIdx = newSelectedSize - 1;
        Outer: for (int i = 1; i <= maxSize; i++) {
            for (int j = 0; j < i; j++) {
                if (selected[nextIdx - j] != selected[nextIdx - j - i]) continue Outer;
            }
            return false;
        }
        return true;
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i : selected) {
            sb.append(i);
        }
        System.out.println(sb);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        selected = new int[N];
    }
}
