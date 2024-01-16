package week11_BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1450_G1_냅색문제_강이규 {

    static int N, C;
    static int[] arr;
    static List<Long> lList;
    static List<Long> rList;

    public static void main(String[] args) throws IOException {
        init();
        // left
        int lEnd = N / 2;
        int rEnd = N;
        comb(0L, 0, lEnd);
        comb(0L, lEnd, rEnd);
        Collections.sort(rList);
        int res = 0;
        for (long i : lList) {
            res += binSearch(C - i) + 1;
        }
        System.out.println(res);
    }

    private static int binSearch(long x) {
        if (x < 0) return 0;
        int left = 0;
        int right = rList.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (rList.get(mid) <= x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    private static void comb(Long cur, int start, int end) {
        if (cur > C) return;
        if (start == end) {
            if (end != N) lList.add(cur);
            else rList.add(cur);
            return;
        }
        comb(cur + arr[start], start+1, end);
        comb(cur, start+1, end);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        lList = new ArrayList<>();
        rList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
