package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main_2295_G4_세수의합_강이규 {

    static int N;
    static int[] arr;
    static List<Integer> sumXY;


    /**
     * 세 수의 합
     * k로 반복문
     * x + y 값 저장
     * input에서 k - (x+y) 찾기(이분탐색)
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        sumXY = new ArrayList<>(N*(N+1)/2+1);

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            for (int j = 0; j <= i; j++) {
                sumXY.add(arr[i]+arr[j]);
            }
        }

        Collections.sort(sumXY);

        Arrays.sort(arr);

        System.out.println(sumXY);
        int len = sumXY.size();
        for (int k = N-1; k >= 0; k--) {
            for (int i = 0; i < len; i++) {
                int sum = sumXY.get(i);
                if (sum > arr[k]) break;
                int gap = arr[k] - sum;
                if (Arrays.binarySearch(arr, gap) >= 0) {
                    System.out.println(arr[k]);
                    System.exit(0);
                }
            }
        }
    }
}
