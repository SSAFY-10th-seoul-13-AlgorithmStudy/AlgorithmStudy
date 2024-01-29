package week12_BinarySearch;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2473_G3_세용액_강이규 {

    static int N;
    static int[] arr;
    static long min;
    static int n1, n2, n3;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        for (int i = 0, end = N-2; i < N; i++) { // 최대값 : n2, n3을 지정할 수 있는 지점까지
            binSearch(i);
        }
        System.out.println(arr[n1] + " " + arr[n2] + " " + arr[n3]);
    }

    private static void binSearch(int x) {
        int l = x+1, r = N-1;
        while (l < r) {
            long sum = (long)arr[x] + arr[l] + arr[r];
            // min 갱신
            if (Math.abs(sum) < min) { // 절대값은 min계산 때만
                min = Math.abs(sum);
                n1 = x;
                n2 = l;
                n3 = r;
            }
            // 이분탐색
            if (sum > 0) {
                r--;
            } else if (sum < 0) {
                l++;
            } else {
                min = sum;
                n1 = x;
                n2 = l;
                n3 = r;
                break;
            }
        }
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
        Arrays.sort(arr);

        min = Long.MAX_VALUE;
    }
}
