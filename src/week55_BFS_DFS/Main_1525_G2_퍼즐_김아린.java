import java.io.*;
import java.util.*;

public class Main {
    
    static class Pair {
        int dice;
        int idx;
        int cnt;
        
        public Pair(int dice, int idx, int cnt) {
            this.dice = dice;
            this.idx = idx;
            this.cnt = cnt;
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
    
        Set<Integer> set = new HashSet<>();
        int zeroIdx = 0;
        int move = Integer.MAX_VALUE;
        int ans = 123456780;
        
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                // arr[i*3+j] = st.nextToken();
                sb.append(st.nextToken());
                if(sb.charAt(i*3+j) == '0')
                    zeroIdx = i*3 + j;
            }
        }
        
        int input = Integer.parseInt(sb.toString());
        
        if(input == ans) {
            System.out.println(0);
            return;
        }
        
        //0 위치에 따른 상하좌우 위치 idx때려넣기
        ArrayList<Integer>[] list = new ArrayList[10];
        list[0] = new ArrayList<Integer>(Arrays.asList(1, 3));
        list[1] = new ArrayList<Integer>(Arrays.asList(-1, 1, 3));
        list[2] = new ArrayList<Integer>(Arrays.asList(-1, 3));
        list[3] = new ArrayList<Integer>(Arrays.asList(-3, 1, 3));
        list[4] = new ArrayList<Integer>(Arrays.asList(-3, -1, 1, 3));
        list[5] = new ArrayList<Integer>(Arrays.asList(-3, -1, 3));
        list[6] = new ArrayList<Integer>(Arrays.asList(-3, 1));
        list[7] = new ArrayList<Integer>(Arrays.asList(-3, -1, 1));
        list[8] = new ArrayList<Integer>(Arrays.asList(-3, -1));
        
        //zeroIdx 0부터 시작
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(input, zeroIdx, 0));
        set.add(input);
            
        while(!q.isEmpty()) {
            Pair now = q.poll();
            
            int zero = now.idx;
            int cnt = now.cnt;
            int dice = now.dice;
            
            for(int idx : list[zero]) {
                int midx = idx + zero;
                
                int newDice = swap(dice, zero, midx);
                
                if(set.contains(newDice)) continue;
                if(newDice == ans) {
                    move = Math.min(move, cnt+1);
                    break;
                }
                
                set.add(newDice);
                q.add(new Pair(newDice, midx, cnt+1));
            }
        }
        
        System.out.println(move == Integer.MAX_VALUE ? -1 : move);
    }
    
    public static int swap(int dice, int zeroIdx, int moveIdx) {
        int zeroNumIdx = (int) Math.pow(10, 8 - zeroIdx);
        int moveNumIdx = (int) Math.pow(10, 8 - moveIdx);
        
        int moveNum = (dice / moveNumIdx) % 10;
        
        dice = dice - (moveNum*moveNumIdx) + (moveNum * zeroNumIdx);
        
        return dice;
    }
}
