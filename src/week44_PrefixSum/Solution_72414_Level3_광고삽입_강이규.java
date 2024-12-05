package programmers;

public class Solution_72414_Level3_광고삽입_강이규 {
    static long[] watchers;
    static int pTimeInt;
    static int aTimeInt;

    public String solution(String play_time, String adv_time, String[] logs) {
        // init
        pTimeInt = timeToInt(play_time);
        aTimeInt = timeToInt(adv_time);

        watchers = new long[360_001];
        markLogs(logs);

        prefixSum();
        prefixSum();

        return findAnswer();
    }

    private static String findAnswer() {
        long max = watchers[aTimeInt];
        int optSTime = 0;
        for (int i = aTimeInt; i < pTimeInt; i++) {
            long cur = watchers[i] - watchers[i - aTimeInt];
            if (cur > max) {
                max = cur;
                optSTime = i - aTimeInt + 1;
            }
        }
        return intToTime(optSTime);
    }

    private static void prefixSum() {
        for (int i = 1; i <= 360_000; i++) {
            watchers[i] += watchers[i-1];
        }
    }

    private static void markLogs(String[] logs) {
        for (int i = 0, end = logs.length; i < end; i++) {
            String log = logs[i];

            String sTimeStr = log.substring(0, 8);
            String eTimeStr = log.substring(9);

            int sTime = timeToInt(sTimeStr);
            int eTime = timeToInt(eTimeStr);

            // marking
            watchers[sTime]++;
            watchers[eTime]--;
        }
    }


    private static int timeToInt(String time) {
        int h = Integer.parseInt(time.substring(0, 2));
        int m = Integer.parseInt(time.substring(3, 5));
        int s = Integer.parseInt(time.substring(6));
        return h * 3600 + m * 60 + s;
    }

    private static String intToTime(int timeInt) {
        int h = timeInt / 3600;
        timeInt %= 3600;
        int m = timeInt / 60;
        timeInt %= 60;
        int s = timeInt;
        String hStr = (h / 10 != 0 ? "" : "0") + h;
        String mStr = (m / 10 != 0 ? "" : "0") + m;
        String sStr = (s / 10 != 0 ? "" : "0") + s;
        return hStr + ":" + mStr + ":" + sStr;
    }
}
