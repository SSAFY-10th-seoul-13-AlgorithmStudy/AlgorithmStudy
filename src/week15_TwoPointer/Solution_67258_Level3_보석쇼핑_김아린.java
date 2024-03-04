import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        // set으로 보석이 뭐뭐 있는지 파악
        HashSet<String> set = new HashSet<>();
        for (String s : gems) {
            set.add(s);
        }
        
        if(set.size() == 1)  {
            answer = new int[]{1, 1};
            return answer;
        }
        
        // 보석 종류와 개수를 담을 map 만들기. 있으면 ++ 없으면 -- 
        HashMap<String, Integer> map = new HashMap<>();
        
        // 투포인터로 left, right인데... 일단 right를 늘려주고, 전체 보석이 들어오면? left를 늘려준다
        int left = 0, right = 0;
        int ansLeft = 0, ansRight = 0;
        int dist = Integer.MAX_VALUE;
        
        int size = gems.length;
        
        while(true) {
            if (set.size() == map.size()) { // set과 map의 크기가 같으면 완성한거임
                map.put(gems[left], map.get(gems[left]) - 1);
                
                if (right - left < dist) {
                    dist = right - left;
                    ansRight = right;
                    ansLeft = left;
                }
                
                //만약.. 보석 다 떨어지면??
                if(map.get(gems[left]) == 0) {
                    map.remove(gems[left]);
                }
                left++;
                
            } else if (right == size) {
              break;
            } else { //완성 안됐으니
                if(map.containsKey(gems[right])) { //진작 있으면?
                    map.put(gems[right], map.get(gems[right]) + 1);
                } else {
                    map.put(gems[right], 1);
                }
                
                //넣어줬으니
                right++;
            }
                        
        }
               
        answer[0] = ansLeft + 1;
        answer[1] = ansRight;
        
        return answer;
    }
}
