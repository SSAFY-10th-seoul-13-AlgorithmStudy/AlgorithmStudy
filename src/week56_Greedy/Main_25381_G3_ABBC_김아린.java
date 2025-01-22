import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // A -> B , B -> C
        // BC AB
        //그리디?????
        //진짜 감이 전혀 안왔음
        
        Queue<Integer> aq = new ArrayDeque<>();
        Queue<Integer> bq = new ArrayDeque<>();
        Queue<Integer> cq = new ArrayDeque<>();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        char[] input = br.readLine().toCharArray();
        
        
        for (int i = 0; i < input.length; i++) {
            char tmp = input[i];
            
            if(tmp == 'A') {
                aq.add(i);
            } else if (tmp == 'B') {
                bq.add(i);
            } else {
                cq.add(i);
            }
        }
        
        int cnt = 0;
        
        //BC
        while(!cq.isEmpty() && !bq.isEmpty()) {
            
            if(bq.peek() < cq.peek()) {
                cnt++;
                bq.poll();
                cq.poll();
            } else {
                cq.poll();
            }
        }
        
        //AB
        while(!aq.isEmpty() && !bq.isEmpty()) {
            
            if(bq.peek() > aq.peek()) {
                cnt++;
                bq.poll();
                aq.poll();
            } else {
                bq.poll();
            }
        }
        
        System.out.println(cnt);
        
    }
}
