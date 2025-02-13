package BOJ;

import java.io.*;
import java.util.*;

public class Main_2352_G2_반도체설계_강이규 {
    static int N;
    static int[] arr;
    static List<Integer> lis;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(lis());
    }

    private static int lis() {
        for (int i = 0; i < N; i++) {
            int cur = arr[i];
            if (lis.isEmpty() || cur > lis.get(lis.size() - 1)) lis.add(cur);
            else {
                int idx = Collections.binarySearch(lis, cur);
                lis.set(-idx - 1, cur);
            }
        }
        return lis.size();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis = new ArrayList<>();
    }

//    // upper bound
//    private static int binarySearch(List<Integer> list, int x) {
//        int l = 0, r = list.size();
//
//        while (l < r) {
//            int mid = (l + r) >> 1;
//            System.out.println(mid);
//
//            // 입력값이 같은 경우는 없음
//            if (x < arr[mid]) {
//                r = mid;
//            } else {
//                l = mid + 1;
//            }
//        }
//        return r;
//    }

}
