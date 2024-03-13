package week17_BFS_DFS;

import java.util.*;
class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        Arrays.fill(answer, 1);
        for(int tc = 0; tc < 5; tc++){
            List<int[]> p_list = new ArrayList<>();
            char map[][] = new char[5][5];
            String[] str = places[tc];
            
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    map[i][j] = str[i].charAt(j);
                    if(map[i][j] == 'P') p_list.add(new int[]{i, j});
                }
            }
            
            for(int[] p : p_list){
                if(!bfs(map, p[0], p[1])){
                    answer[tc] = 0;
                    break;
                }
            }
        }
        
        return answer;
    }
    
    public boolean bfs(char[][] map, int r, int c){
        boolean[][] visited = new boolean[5][5];
        int dr[] = {0, 0, -1, 1};
        int dc[] = {-1, 1, 0, 0};
        
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {r, c});
        visited[r][c] = true;
        int depth = 0;
        while(!que.isEmpty() && depth < 2){
            for(int k = 0, length = que.size(); k < length; k++){
                int[] cur = que.poll();
                for(int i = 0; i < 4; i++){
                    int nr = cur[0] + dr[i];
                    int nc = cur[1] + dc[i];
                    if(nr < 0 || nr >= 5 || nc < 0 || nc >= 5 
                       || map[nr][nc] == 'X' || visited[nr][nc]) continue;
                    if(map[nr][nc] == 'P') return false;
                    que.offer(new int[] {nr, nc});
                    visited[nr][nc] = true;                    
                }
            }
            depth++;
        }
        return true;
    }
}