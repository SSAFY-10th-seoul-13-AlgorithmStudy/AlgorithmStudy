import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        //indegree, outdegree를 통해 구할 수 있다
        //outdegree가 2 이상이다? 8 아니면 시작 노드 
        //근데 indegree가 0이다? 시작노드 / 아니다? 8
        //outdegree가 0이다? => 막대
        HashMap<Integer, Integer> idMap = new HashMap<>();
        HashMap<Integer, Integer> odMap = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        
        for (int[] edge : edges) {
            odMap.put(edge[0], odMap.getOrDefault(edge[0], 0) + 1); 
            idMap.put(edge[1], idMap.getOrDefault(edge[1], 0) + 1);
            
            set.add(edge[0]);
            set.add(edge[1]);
        }
        
        for (int node : set) {
            if(odMap.getOrDefault(node, 0) >= 2 && idMap.getOrDefault(node, 0) == 0) {
                answer[0] = node;
            }
            else if (odMap.getOrDefault(node, 0) >= 2) {
                answer[3]++;
            }
            else if (odMap.getOrDefault(node, 0) == 0) {
                answer[2]++;
            }
        }
        
        answer[1] = odMap.get(answer[0]) - answer[2] - answer[3];
        
        return answer;
    }
}
