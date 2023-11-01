package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1756_G5_피자굽기_강이규 {

    static int N, D;
    static int[] dows;
    static int[] oven;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        D = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        dows = new int[N];
        oven = new int[D];
        // oven
        st = new StringTokenizer(br.readLine());
        int minWidth = 1_000_000_000;
        for (int i = 0; i < D; i++) {
            int cur = Integer.parseInt(st.nextToken());
            minWidth = Math.min(minWidth, cur);
            oven[i] = minWidth;
        }

        // dow
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            dows[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solve());
    }

    private static int solve() {
        int dowPtr = 0;
        int ovenPtr = D - 1;
        while (dowPtr < N) {
            int dow = dows[dowPtr++];
            while (oven[ovenPtr] < dow)
                if (--ovenPtr == 0) return 0;
            if (--ovenPtr == 0 && dowPtr < N) return 0;
        }
        return ovenPtr+2; // 인덱스 base 맞추기 + 마지막에 추가로 --ovenPtr된 것
    }
}
