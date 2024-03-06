package week15_TwoPointer;
import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];

        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        for (String s : gems) {
            set.add(s);
        }

        int n = gems.length;

        int distance = Integer.MAX_VALUE;

        int left = 0;
        int right = 0;

        int start = 0;
        int end = 0;

        while (true) {

            if (set.size() == map.size()) {
                map.put(gems[left], map.get(gems[left])-1);

                if (map.get(gems[left]) == 0) {
                    map.remove(gems[left]);
                }
                
                left++;
            } else if (right == n) {
                break;
            } else {
                map.put(gems[right], map.getOrDefault(gems[right], 0) + 1);
                right++;
            }

            if (map.size() == set.size()) {
                if (right-left < distance){
                    distance = right-left;
                    start = left+1;
                    end = right;
                }
            }

        }

        answer[0] = start;
        answer[1] = end;

        return answer;
    }
}