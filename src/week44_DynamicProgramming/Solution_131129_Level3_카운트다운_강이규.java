package programmers;

import java.util.Arrays;

public class Solution_131129_Level3_카운트다운_강이규 {

    static int target;
    static Status[] dp;

    public int[] solution(int target) {
        init(target);
        dp();
        return new int[]{dp[target].count, dp[target].singleOrBool};
    }

    private void dp() {
        for (int i = 21; i <= target; i++) {
            // single
            for (int j = 1; j <= 20; j++) {
                // 여기선 항상 i > j이다.
                Status pre = dp[i - j];
                compareAndUpdate(i, pre.count + 1, pre.singleOrBool + 1);
            }
            // bool
            if (i >= 50) {
                Status pre = dp[i - 50];
                compareAndUpdate(i, pre.count + 1, pre.singleOrBool + 1);
            }
            // 더블, 트리플
            for (int j = 1; j <= 20; j++) {
                for (int k = 2; k <= 3; k++) {
                    int curScore = j * k;
                    if (i < curScore) continue;
                    Status pre = dp[i - curScore];
                    compareAndUpdate(i, pre.count + 1, pre.singleOrBool);
                }
            }

        }
    }

    // count가 적은 거, 같다면 single or bool 개수가 큰 것을 남긴다.
    private void compareAndUpdate(int idx, int count, int sinOrBool) {
        Status s = dp[idx];
        if (count > s.count) return;
        if (count == s.count && sinOrBool <= s.singleOrBool) return;
        // update
        s.count = count;
        s.singleOrBool = sinOrBool;
    }

    private void init(int target) {
        this.target = target;

        dp = new Status[target + 1];
        Arrays.fill(dp, new Status(100_001, 0));
        for (int i = 0; i <= target; i++) {
            dp[i] = new Status(100_001, 0);
        }

        dp[0].count = 0; // 0, 0
        // 1 ~ 20 => 1 single
        for (int i = 1; i <= 20; i++) {
            if (i > target) break;
            dp[i].count = 1;
            dp[i].singleOrBool = 1;
        }
    }

    static class Status {
        int count;
        int singleOrBool;
        public Status(int count, int singleOrBool) {
            this.count = count;
            this.singleOrBool = singleOrBool;
        }
    }
}
