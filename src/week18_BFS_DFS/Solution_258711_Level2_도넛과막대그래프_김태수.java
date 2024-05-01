import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] out = new int[1_000_001];
        int[] in = new int[1_000_001];
        int minVal = 1_000_001;
        int maxVal = 0;
        
        for(int i = 0, end = edges.length ; i < end;i++){
            int[] temp = edges[i];
            out[temp[0]]++;
            in[temp[1]]++;
            minVal = Math.min(Math.min(temp[0],temp[1]), minVal);
            maxVal = Math.max(Math.max(temp[0],temp[1]), maxVal);
        }
        int maxCount = -1;
        int maxNode = -1;
        int graph2 = 0;
        int graph3 = 0;
        for(int i = minVal ; i <= maxVal;i++){
            if(in[i] == 0 && out[i] == 0) continue; // ?????
            if(out[i] == 0 ) graph2++;
            else if(out[i] == 2 && in[i] >= 2){
                graph3++;
            } 
            if(maxCount < out[i] && in[i] == 0){
                maxCount = out[i];
                maxNode = i;
            }
        }
        
        return new int[] {maxNode,(maxCount - graph2 - graph3),graph2, graph3};
    }
}