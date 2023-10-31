package week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1756_G5_피자굽기_김아린 {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        int D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(in.readLine());
        int min = Integer.MAX_VALUE;
        int[] oven = new int[D];
        for (int i = 0; i < D; i++) {
            min = Math.min(min, Integer.parseInt(st.nextToken()));
            oven[i] = min;
        }
        
        int idx = 0, j = 0;
        
        int[] pizza = new int[N];
        st = new StringTokenizer(in.readLine());
        for (int i = 0; i < N; i++) {
            pizza[i] = Integer.parseInt(st.nextToken());
        }
        
        for (int i = D-1; i >= 0; i--) {
            if(oven[i] >= pizza[j]) {
                oven[i] = 0;
                j++;
            }
            
            
            if(j == N) {
            
                System.out.println(i+1);
                return;
            }
        }
        
       
        System.out.println(0); 
    }

}