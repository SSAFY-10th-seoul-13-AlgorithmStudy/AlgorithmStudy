import java.util.*;

class Solution {
    static boolean v[][];
    static int[][] direction = {
        {1,0},
        {0,1},
        {0,-1},
        {-1,0}
    };
    static boolean flag;
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        Arrays.fill(answer,1);
        for(int k = 0; k< 5;k++){
            for(int i = 0 ; i< 5;i++){
                top:
                for(int j = 0; j< 5;j++){
                    //System.out.println(places[k][i].charAt(j));
                    if(places[k][i].charAt(j) == 'P'){
                        v = new boolean[5][5];
                        flag = true;
                        dfs(places[k],i,j,0);
                        if(!flag){
                            answer[k] = 0;
                            break top;
                        }
                    } 
                }
            }    
        }
        return answer;
    }
    
    public void dfs(String[] places,int x, int y, int depth){
        if(depth == 2){
            return;
        }
        v[x][y] = true;
        for(int[] dir:direction){
            int dx = x + dir[0];
            int dy = y + dir[1];
            if(dx < 0 || dx >= 5 || dy < 0 || dy >= 5 || v[dx][dy]) continue;
            if(places[dx].charAt(dy) == 'X') continue;
            else if(places[dx].charAt(dy) == 'P'){
                flag = false;
                return;
            }
            else{
                dfs(places, dx,dy,depth+1);
            }
        }
        
    }
}