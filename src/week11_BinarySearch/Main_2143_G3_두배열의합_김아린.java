package week11_BinarySearch;

import java.io.*;
import java.util.*;

public class Main_2143_G3_두배열의합_김아린 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] B = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        // A와 B의 모든 부분 합을 구할..
        long[] sumA = sum(A);
        long[] sumB = sum(B);

        // 이분 탐색을 위해 오름차순 정렬
        Arrays.sort(sumA);
        Arrays.sort(sumB);

        // 경우의 수 찾기
        long count = 0;
        int start = 0;
        int end = sumB.length - 1;
        while (start < sumA.length && end >= 0) {
            long temp = sumA[start] + sumB[end];
            if (temp == T) { // 목표 합과 같으면
                long countA = 0;
                long countB = 0;
                long tempA = sumA[start];
                long tempB = sumB[end];
                // 같은 부분합의 개수를 세기
                while (start < sumA.length && sumA[start] == tempA) {
                    start++;
                    countA++;
                }
                while (end >= 0 && sumB[end] == tempB) {
                    end--;
                    countB++;
                }
                count += (countA * countB); // 경우의 수를 더해주는데?
            } else if (temp < T) { // 목표 합보다 작으면
                start++; // start를 증가
            } else { // 목표 합보다 크면
                end--; // end를 감소
            }
        }

        System.out.println(count); 
    }
    
    private static long[] sum(int[] arr) {
        int length = arr.length;
        long[] sum = new long[length * (length + 1) / 2];

        int idx = 0;
        for (int i = 0; i < length; i++) {
            int tmp = 0;
            for (int j = i; j < length; j++) {
                tmp += arr[j];
                sum[idx++] = tmp;
            }
        }

        return sum;
    }
}