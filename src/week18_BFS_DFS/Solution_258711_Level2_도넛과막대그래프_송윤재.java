import java.util.*;
class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        Set<Integer> nodes = new HashSet<>();
        int[] inDegree = new int[1000001];
        int[] outDegree = new int[1000001];
        
        for(int[] edge : edges){
            outDegree[edge[0]]++;
            inDegree[edge[1]]++;
            nodes.add(edge[0]);
            nodes.add(edge[1]);
        }
        
        for(int node : nodes){
            if(outDegree[node] == 0) answer[2]++;
            else if(outDegree[node] == 2 && inDegree[node] > 1) answer[3]++;
            else if(inDegree[node] == 0 && outDegree[node] != 1) answer[0] = node;
        }
        answer[1] = outDegree[answer[0]] - answer[2] - answer[3];
        return answer;
    }
}