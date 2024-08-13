package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2550_G3_전구_강이규 {

    static int N;
    static int[] switches;
    static int[] dp;
    static List<Integer> lis;
    static Map<Integer, Integer> bulbNumToBulbIdx = new HashMap<>();
    static Map<Integer, Integer> switchIdxToSwitchNum = new HashMap<>();

    public static void main(String[] args) throws IOException {
        init();
        dp();
        printLis();
    }

    private static void printLis() {
        int[] switchNums = new int[lis.size()];
        int lastIdx = switchNums.length - 1;

        for (int i = N - 1; i >= 0; i--) {
            if (dp[i] == lastIdx) {
                switchNums[lastIdx] = switchIdxToSwitchNum.get(i);
                lastIdx--;
            }
        }
        Arrays.sort(switchNums);
        StringBuilder sb = new StringBuilder();
        sb.append(switchNums.length).append("\n");
        for (int switchNum : switchNums) {
            sb.append(switchNum).append(" ");
        }
        System.out.println(sb);
    }

    private static void dp() {
        lis.add(switches[0]);
        dp[0] = 0;
        int lastIdx = 0;

        // 해당 원소가 들어간 (LIS에서의)위치를 저장한다.
        for (int i = 1; i < N; i++) {
            int cur = switches[i];
            if (cur > lis.get(lastIdx)) {
                lis.add(cur);
                dp[i] = ++lastIdx;
            } else {
                int idx = binarySearch(cur);
                lis.set(idx, cur);
                dp[i] = idx;
            }
        }
    }

    private static int binarySearch(int x) {
        int left = 0, right = lis.size() - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;

            if (lis.get(mid) < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        switches = new int[N];

        String switchNumStr = br.readLine();
        String bulbNumStr = br.readLine();

        // 전구 번호 저장
        st = new StringTokenizer(bulbNumStr);
        for (int i = 0; i < N; i++) {
            int bulbNum = Integer.parseInt(st.nextToken());
            bulbNumToBulbIdx.put(bulbNum, i);
        }
        // 스위치번호를 전구 인덱스로 파싱
        st = new StringTokenizer(switchNumStr);
        for (int i = 0; i < N; i++) {
            int switchNum = Integer.parseInt(st.nextToken());
            switches[i] = bulbNumToBulbIdx.get(switchNum);
            switchIdxToSwitchNum.put(i, switchNum);
        }

        dp = new int[N];
        lis = new ArrayList<>();
    }
}