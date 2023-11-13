package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_20366_G3_같이눈사람만들래_강이규 {

    static int N;
    static int[] arr;
    static int minGap = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        for (int i = 0; i < N; i++) {
            for (int j = i+3; j < N; j++) {
                int h1 = arr[i] + arr[j];
                int left = i+1, right = j-1;
                while (left < right) {
                    int h2 = arr[left] + arr[right];

                    int gap = h2 - h1;
                    minGap = Math.min(minGap, Math.abs(gap));

                    if (gap > 0) right--;
                    else left++;
                }
            }
        }
        System.out.println(minGap);
    }
}
