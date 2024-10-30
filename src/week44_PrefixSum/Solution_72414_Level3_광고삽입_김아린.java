class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        //시작하고 끝나는 지점에서 카운트 올렸다 내렸다 하기
        
        int[] timeTable = new int[360001];
        
        for (String s : logs) {
            String[] tmp = s.split("-");
            int start = convertInt(tmp[0]);
            int end = convertInt(tmp[1]);
            
            timeTable[start]++;
            timeTable[end]--;
        }
        
        int play = convertInt(play_time);
        int adv = convertInt(adv_time);
                
        //누적합
        for (int i = 1; i <= 360000; i++) {
            timeTable[i] += timeTable[i-1];
        }
        
        //첫 번째 광고 구간의 시청 시간 합을 계산 => 기준
        long maxSum = 0;
        long sum = 0;
        
        for (int i = 0; i < adv; i++) {
            sum += timeTable[i];
        }        
        
        maxSum = sum;
        int idx = 0;
        //슬라이딩 윈도우
        for (int i = adv; i < play; i++) {
            sum += timeTable[i] - timeTable[i-adv];
            
            if (maxSum < sum) {
                maxSum = sum;
                //구간 시작 위치 (마지막 종료 시각은 개구간이어서 + 1)
                idx = i - adv + 1;
            }
        }
        
        answer = convertString(idx);
        
        return answer;
    }
    
    public static int convertInt(String time) {
        int second = 60*60;
        int sum = 0;
        String[] tmp = time.split(":");
        for (int i = 0; i < 3; i++) {
            sum += second * Integer.parseInt(tmp[i]);
            second /= 60;
        }
        
        return sum;
    }
    
    public static String convertString(int time) {
        //시
        int hour = time / (60*60);
        time -= hour * (60*60);
        
        String hs = String.valueOf(hour);
        hs = hour < 10 ? "0" + hs : hs;
        
        //분
        int min = time / 60;
        time -= min * 60;
        
        String ms = String.valueOf(min);
        ms = min < 10 ? "0" + ms : ms;
        
        //초
        String ss = String.valueOf(time);
        ss = time < 10 ? "0" + ss : ss;
        
        return hs + ":" + ms + ":" + ss;
    }
}
