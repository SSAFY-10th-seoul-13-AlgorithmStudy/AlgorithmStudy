package week11_BinarySearch;

import java.io.*;
import java.util.*;

public class Main_2473_G3_세용액_김아린 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        long min = Long.MAX_VALUE;
        long[] result = new long[3];
        for (int i = 0; i < N - 2; i++) {
            int start = i + 1;
            int end = N - 1;

            while (start < end) {
                long sum = arr[i] + arr[start] + arr[end];

                if (Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    result[0] = arr[i];
                    result[1] = arr[start];
                    result[2] = arr[end];
                }

                if (sum < 0) {
                    start++;
                } else {
                    end--;
                }
            }
        }

        Arrays.sort(result);
        for (int i = 0; i < 3; i++) {
            System.out.print(result[i] + " ");
        }
    }
}