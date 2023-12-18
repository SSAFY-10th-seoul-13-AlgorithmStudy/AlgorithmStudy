package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_12015_G2_가장긴증가하는부분수열2_강이규 {
    /*
    궁금한 점 :
    sorted를 리스트로 할때랑 배열로 할 때, 크게(650 -> 450ms) 차이난다.
    리스트 초기 사이즈를 지정하고 해도 같음
    add나 set에 진짜 몇가지 추가 동작(배열에서 값 가져와서 리턴 등, 사소한거)이
    몇개씩 더 있던데, N이 큰 만큼 그것들 때문인지?
     */
    static int N;
    static int[] sorted;
    static int idx = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        sorted = new int[N];
        st = new StringTokenizer(br.readLine());

        sorted[idx++] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) {
            int cur = Integer.parseInt(st.nextToken());
            if (cur <= sorted[idx-1])
                sorted[binSearch(cur)] = cur;
            else
                sorted[idx++] = cur;
        }
        System.out.println(idx);
    }


    private static int binSearch(int value) {
        int s = 0;
        int e = idx - 1;
        while (s < e) {
            int mid = (s + e) / 2;
            int midVal = sorted[mid];
            // left
            if (value <= midVal) // sorted 안의 값이 크거나 혹은 같더라도, 해당 자리가 들어갈 위치가 됨
                e = mid;
            // right
            else
                s = mid + 1;
        }
        return e;
    }

}
