package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2830_G3_행성X3_강이규 { 
    /*
    이진수의 특정 자리마다 0, 1이 몇개인지 구하고, 마지막에 한번에 구하기
    이진수 i자리로 인해 더해지는 친밀도 = 2^i * 해당 자리 0 개수 * 해당 자리 1 개수
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int IDX_LIMIT = 20;
        int N = Integer.parseInt(br.readLine());

        int[] oneCnt = new int[IDX_LIMIT];
        int[] zeroCnt = new int[IDX_LIMIT];
        long res = 0;

        for (int i = 0; i < N; i++) {
            int cur = Integer.parseInt(br.readLine());

            // 이진수의 각 자리마다 0인지 1인지를 체크해서, cnt 배열 업데이트
            // cur & 2^j != 0 => oneCnt[j]++
            for (int j = 0; j < IDX_LIMIT; j++) {
                if ((cur & (1 << j)) != 0) oneCnt[j]++;
                else zeroCnt[j]++;
            }
        }

        // 결과 구하기
        long decimalVal = 1;
        for (int i = 0; i < IDX_LIMIT; i++) {
            res += decimalVal * oneCnt[i] * zeroCnt[i];
            decimalVal <<= 1;
        }

        System.out.println(res);
    }
}
