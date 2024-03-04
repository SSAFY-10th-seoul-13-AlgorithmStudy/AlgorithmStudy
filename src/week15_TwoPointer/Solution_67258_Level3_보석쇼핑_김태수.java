package week15_TwoPointer;
import java.util.*;
public class Solution_67258_Level3_보석쇼핑_김태수 {
	public int[] solution(String[] gems) {
        int[] answer = {0, gems.length-1};
        HashSet<String> sett = new HashSet();
        HashMap<String, Integer> map = new HashMap();
        HashSet<String> hasVVS = new HashSet();
        if(gems.length == 1){
            return new int[] {1,1};
        }
        
        for(int i = 0 ; i < gems.length;i++){
            String target = gems[i];
            if(!sett.contains(target)) sett.add(target);
        }
        
        int start = 0;
        for(int end = 0; end < gems.length;end++){
            String temp = gems[end];
            
            if(hasVVS.contains(temp)) map.put(temp,map.get(temp)+1);
            else map.put(temp, 1);
            hasVVS.add(temp);
            
            while(hasVVS.equals(sett)){
                //System.out.println(hasVVS);
                    //System.out.println("1" + map);
                    //System.out.println(gems[start]);
                    temp = gems[start];
                    if(map.get(temp) == 1){
                      map.remove(temp);  
                      hasVVS.remove(temp);
                    } 
                    else map.put(temp,map.get(temp) - 1);
                    
                    //System.out.println(start+" "+end+" "+answer[0]+" "+answer[1]);
                    if(answer[1] - answer [0] > end - start){
                        answer[1] = end;
                        answer[0] = start;
                    }
                start++;
                //System.out.println("2" + map);
                //System.out.println("2" + hasVVS);
            }
            
            //System.out.println(map);
        }
        return new int[] {answer[0]+1, answer[1]+1};
    }
}
