package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_14476_G2_최대공약수하나빼기_강이규 {
    /**
     * arr[k]가 빠졌을 때의 gcd = gcd(k-1까지의 gcd, k+1부터의 gcd)
     */

    static int N;
    static int[] arr;
    static int[] lgcd;
    static int[] rgcd;


    public static void main(String[] args) throws IOException {
        init();

        findRes();
    }

    private static void findRes() {
        int res = -1;
        int maxGcd = -1;

        for (int i = 1; i <= N; i++) {
            int cur = gcd(lgcd[i-1], rgcd[i+1]);
            if (cur > maxGcd) {
                if (arr[i] % cur == 0) continue;
                maxGcd = cur;
                res = i;
            }
        }
        System.out.println(res < 0 ? -1 : (maxGcd + " " + arr[res]));
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N+2];
        lgcd = new int[N+2];
        rgcd = new int[N+2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            lgcd[i] = gcd(arr[i], lgcd[i-1]);
        }

        for (int i = N; i > 0; i--) {
            rgcd[i] = gcd(rgcd[i+1], arr[i]);
        }
    }

    private static int gcd(int a, int b) {
        return b != 0 ? gcd(b, a%b) : a;
    }
}