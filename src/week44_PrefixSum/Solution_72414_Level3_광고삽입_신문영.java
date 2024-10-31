package week44_PrefixSum;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = parseTime(play_time);
        int advTime = parseTime(adv_time);
        
        long[] sumArr = new long[playTime + 1];
        long[] viewTimeline = new long[playTime];
        
        for (String log : logs) {
            String[] splited = log.split("-");
            int start = parseTime(splited[0]);
            int end = parseTime(splited[1]);
            sumArr[start]++;
            sumArr[end]--;
        }
        
        long sum = 0;
        for (int i = 0; i < playTime; i++) {
            sum += sumArr[i];
            viewTimeline[i] += sum;
        }
        
        // 누적 합 배열에서 구간 합이 제일 큰 구간 찾기
        long view = 0;
        for (int i = 0; i < advTime; i++) {
            view += viewTimeline[i];
        }
        
        long maxView = view;
        int startingTime = 0;
        for (int i = advTime; i < playTime; i++) {
            view += viewTimeline[i];
            view -= viewTimeline[i - advTime];
            
            if (view > maxView) {
                startingTime = i - advTime + 1;
                maxView = view;
            }
        }
        
        int viewSecond = startingTime % 60;
        int viewMinute = (startingTime % 3600) / 60;
        int viewHour = startingTime / 3600;
        
        StringBuilder sb = new StringBuilder();
        if (viewHour < 10) sb.append("0");
        sb.append(viewHour);
        sb.append(":");
        if (viewMinute < 10) sb.append("0");
        sb.append(viewMinute);
        sb.append(":");
        if (viewSecond < 10) sb.append("0");
        sb.append(viewSecond);
        
        return sb.toString();
    }
    
    public int parseTime(String string) {
        return ((string.charAt(0) - '0') * 10 + string.charAt(1) - '0') * 3600 +
            ((string.charAt(3) - '0') * 10 + string.charAt(4) - '0') * 60 +
            (string.charAt(6) - '0') * 10 + string.charAt(7) - '0';
    }
}