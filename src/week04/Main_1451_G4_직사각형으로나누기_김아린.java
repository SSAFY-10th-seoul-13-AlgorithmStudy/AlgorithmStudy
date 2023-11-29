package week04;

import java.io.*;
import java.util.*;
 
public class Main_1451_G4_직사각형으로나누기_김아린 {
	static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        
        
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(in.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        long max = 0;
        
        for(int i = 1; i < n; i++) {
            long a = getSum(0, i, 0, m);
            
            for(int j = 1; j < m; j++) {
                long b = getSum(i, n, 0, j);
                long c = getSum(i, n, j, m);
                
                long tmp = a * b * c;
                
                max = Math.max(tmp, max);
            }
            
            for(int j = i + 1; j < n; j++) {
                long b = getSum(i, j, 0, m);
                long c = getSum(j, n, 0, m);
                
                long tmp = a * b * c;
                
                max = Math.max(tmp, max);
            }    
        }
        
        for(int i = n - 1; i > 0; i--) {
            long a = getSum(i, n, 0, m);
            
            for(int j = 1; j < m; j++) {
                long b = getSum(0, i, 0, j);
                long c = getSum(0, i, j, m);
                
                long tmp = a * b * c;
                
                max = Math.max(tmp, max);
            }
        }
        
        for(int i = 1; i < m; i++) {
            long a = getSum(0, n, 0, i);
            
            for(int j = 1; j < n; j++) {
                long b = getSum(0, j, i, m);
                long c = getSum(j, n, i, m);
                
                long tmp = a * b * c;
                
                max = Math.max(tmp, max);
            }
            
            for(int j = i + 1; j < m; j++) {
                long b = getSum(0, n, i, j);
                long c = getSum(0, n, j, m);
                
                long tmp = a * b * c;
                
                max = Math.max(tmp, max);
            }    
        }
        
        for(int i = m - 1; i > 0; i--) {
            long a = getSum(0, n, i, m);
            
            for(int j = 1; j < n; j++) {
                long b = getSum(0, j, 0, i);
                long c = getSum(j, n, 0, i);
                
                long tmp = a * b * c;
                
                max = Math.max(tmp, max);
            }
        }
        
        System.out.println(max);
    }
    
    private static long getSum(int sI, int eI, int sJ, int eJ) {
        long sum = 0;
        
        for(int i = sI; i < eI; i++) {
            for(int j = sJ; j < eJ; j++) {
                sum += map[i][j];
            }
        }
        
        return sum;
    }
}