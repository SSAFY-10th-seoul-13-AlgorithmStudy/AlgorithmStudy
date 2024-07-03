package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1222_G2_홍준프로그래밍대회_강이규 {

    static int N;
    static int[] arr;
    static int[] count;
    static long max = 0;

    public static void main(String[] args) throws IOException {
        init();
        printRes();
    }

    private static void printRes() {
        for (int i = 1; i < 2_000_001; i++) {
            if (count[i] < 2) continue;
            max = Math.max((long)count[i] * i, max);
        }
        System.out.println(max);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        count = new int[2_000_001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            updateCount(Integer.parseInt(st.nextToken()));
        }
    }

    private static void updateCount(int x) {
//        int end = (int)Math.round(Math.sqrt(x)) + 1; // 한 약수 페어들이 2번 들어가는 경우가 생긴다.
        double end = Math.sqrt(x);
        for (int i = 1; i <= end; i++) {
            if (x % i != 0) continue;
            count[i]++; // x는 i로 나뉘어 떨어짐
            int tmp = x / i;
            if (tmp != i) count[tmp]++; // tmp로도 나뉘어 떨어짐
        }
    }


}
