package week18_BFS_DFS;

import java.util.*;

public class Solution_258711_Level2_도넛과막대그래프_김희연 {
	
	class Solution {
	    public int[] solution(int[][] edges) {
	        int[] answer = new int[4];
	        
	        List<List<Integer>> graph = new ArrayList<>();
	        
	        Set<Integer> set = new HashSet<>();
	        
	        for(int i=0; i<edges.length; i++){
	            set.add(edges[i][0]);
	            set.add(edges[i][1]);
	        }
	        
	        int max = Collections.max(set);

	        for(int i=0; i<=max; i++){
	            graph.add(new ArrayList<>());
	        }
	        
	        int[] indegree = new int[max+1];
	        int[] outdegree = new int[max+1];
	        
	        for(int i=0; i<edges.length; i++){
	            int a = edges[i][0];
	            int b = edges[i][1];
	            
	            graph.get(a).add(b);
	            
	            indegree[b]++;
	            outdegree[a]++;
	        }
	        
	        for(int i=1, length=indegree.length; i<length; i++){
	            if(indegree[i] == 0 && outdegree[i] > 1){
	                answer[0] = i;
	                break;
	            }
	        }
	        
	        for(int i=1; i<=max; i++){
	            if(indegree[i] > 0 && outdegree[i] == 0){
	                answer[2]++;
	            }
	            else if(indegree[i] > 1 && outdegree[i] == 2){
	                answer[3]++;
	            }
	        }
	        
	        answer[1] = graph.get(answer[0]).size() - answer[2] - answer[3];
	        
	        return answer;
	    }
	}
}
