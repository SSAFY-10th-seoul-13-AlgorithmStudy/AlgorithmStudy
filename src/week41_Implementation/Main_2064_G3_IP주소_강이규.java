package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2064_G3_IP주소_강이규 {

    static int N;
    static int[][] ips;
    static int[] mask;
    static int partIdx = 4; // 왼쪽부터 0
    static int bitIdx = -1; // 오른쪽부터 0, 맨 왼쪽 = 7

    public static void main(String[] args) throws IOException {
        init();
        // 입력값이 다 같을 때는 pass
        if (partIdx != 4) {
            findBitIdx();
        }
        printResult();
    }

    private static void printResult() {
        StringBuilder minAddrStr = new StringBuilder();
        StringBuilder maskStr = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            maskStr.append(mask[i]).append(".");
            minAddrStr.append(ips[0][i] & mask[i]).append(".");
        }
        System.out.println(minAddrStr.substring(0, minAddrStr.length() - 1)
        + "\n" + maskStr.substring(0, maskStr.length() - 1));
    }

    private static void findBitIdx() {
        for (int i = 7; i >= 0; i--) {
            int targetBit = 1 << i;
            for (int j = 1; j < N; j++) {
                if ((ips[j][partIdx] & targetBit) != (ips[0][partIdx] & targetBit)) {
                    bitIdx = j;
                    return;
                }
            }
            // 모든 입력값에서 현재 비트가 같다 = 네트워크 마스크에서도 1이다.
            mask[partIdx] |= 1 << i;
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        ips = new int[N][4];
        mask = new int[4];

        // 파트 특정을 편하게 하기 위해, 첫 입력값은 기록
        st = new StringTokenizer(br.readLine(), ".");
        for (int j = 0; j < 4; j++) {
            ips[0][j] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine(), ".");
            for (int j = 0; j < 4; j++) {
                ips[i][j] = Integer.parseInt(st.nextToken());
                if (j >= partIdx) continue;
                if (ips[i][j] != ips[0][j]) partIdx = j;
            }
        }
        // 네트워크 마스크는 입력값들에서 다른 비트가 발생하는 파트의 왼쪽 파트까진, 모두 255
        for (int i = 0; i < partIdx; i++) {
            mask[i] = 255;
        }
    }

}
