package week11_BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2143_G3_두배열의합_강이규 {

    static int T, N, M;
    static int[] arrA;
    static int[] arrB;
    static List<Integer> gapA;
    static Map<Integer, Integer> gapBCnt;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        long res = 0;
        for (int i = 0, size = gapA.size(); i < size; i++) {
            res += gapBCnt.getOrDefault(T - gapA.get(i), 0);
        }
        System.out.println(res);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        N = Integer.parseInt(br.readLine());
        arrA = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            arrA[i] = arrA[i-1] + Integer.parseInt(st.nextToken());

        }

        M = Integer.parseInt(br.readLine());
        arrB = new int[M+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            arrB[i] = arrB[i-1] + Integer.parseInt(st.nextToken());
        }

        gapA = new ArrayList<>();
        gapBCnt = new HashMap<>();

        for (int i = 0; i <= N; i++) {
            for (int j = i+1; j <= N; j++) {
                gapA.add(arrA[j] - arrA[i]);

            }
        }

        for (int i = 0; i <= M; i++) {
            for (int j = i+1; j <= M; j++) {
                int key = arrB[j] - arrB[i];
                gapBCnt.putIfAbsent(key, 0);
                gapBCnt.put(key, gapBCnt.get(key) + 1);
            }
        }
    }
}
