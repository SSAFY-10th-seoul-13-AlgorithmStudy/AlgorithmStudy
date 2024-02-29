package week15_TwoPointer;

import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];       
        int gems_length = gems.length;
        
        Set<String> all_gems = new HashSet<>();
        
        for(String gem : gems){
            all_gems.add(gem);
        }
        
        int gems_size = all_gems.size();
        
        if(gems_size == 1){ 
            return new int[]{1, 1};
        }
        
        int min_length = Integer.MAX_VALUE;
        int left = 0, right = 1;
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        set.add(gems[left]);
        map.put(gems[left], 1);
        
        while(left < right && right < gems_length){
            if(set.contains(gems[right])){
                int tmp = map.get(gems[right]) + 1;
                map.put(gems[right], tmp);
            } else {
                set.add(gems[right]);
                map.put(gems[right], 1);                
                while(set.size() == gems_size){
                    if(min_length > right - left){
                        answer[0] = left + 1;
                        answer[1] = right + 1;
                        min_length = right - left;
                    }
                    int tmp = map.get(gems[left]) - 1;
                    if(tmp == 0){
                        set.remove(gems[left]);
                        map.remove(gems[left]);
                    } else {
                        map.put(gems[left], tmp);                        
                    }
                    left++;
                } 
            }
            right++;
        }
        
        return answer;
    }
}