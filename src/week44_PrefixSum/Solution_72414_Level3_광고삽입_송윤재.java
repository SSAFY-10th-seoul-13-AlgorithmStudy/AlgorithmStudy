import java.util.*;

class Solution {
    static long[] prefixSum;
    static int playTime, advTime;

    // 시간 문자열을 초 단위로 변환
    static int timeToSeconds(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 3600 + Integer.parseInt(parts[1]) * 60 + Integer.parseInt(parts[2]);
    }

    // 초 단위를 시간 문자열로 변환
    static String secondsToTime(int seconds) {
        int h = seconds / 3600;
        int m = (seconds % 3600) / 60;
        int s = seconds % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    public String solution(String play_time, String adv_time, String[] logs) {
        playTime = timeToSeconds(play_time);
        advTime = timeToSeconds(adv_time);

        // 광고 시간이 재생 시간보다 긴 경우
        if (advTime > playTime) {
            return "00:00:00";
        }

        prefixSum = new long[playTime + 1];

        // 로그를 기반으로 시청 기록 반영
        for (String log : logs) {
            String[] times = log.split("-");
            int start = timeToSeconds(times[0]);
            int end = timeToSeconds(times[1]);
            prefixSum[start] += 1;
            if (end < prefixSum.length) {
                prefixSum[end] -= 1;
            }
        }

        // 누적 시청 시간 계산
        for (int i = 1; i <= playTime; i++) {
            prefixSum[i] += prefixSum[i - 1];
        }

        // 누적 합 계산
        for (int i = 1; i <= playTime; i++) {
            prefixSum[i] += prefixSum[i - 1];
        }

        // 광고 삽입 위치 탐색
        long maxView = 0;
        int maxStart = 0;

        for (int start = 0; start + advTime <= playTime; start++) {
            int end = start + advTime;
            long currentView = prefixSum[end - 1] - (start == 0 ? 0 : prefixSum[start - 1]);
            if (currentView > maxView) {
                maxView = currentView;
                maxStart = start;
            }
        }

        return secondsToTime(maxStart);
    }
}
