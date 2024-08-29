import java.util.*;
import java.io.*;

public class Main {
    static int N, S;
    static int[] arr;
    
    public static void swap() {
        int tmp = 0;
        
        for (int i = 0; i < N; i++) {
            int max = 0;
            int idx = 0;
            for (int j = i; j < N; j++) {
                if(max < arr[j]) {
                    idx = j;
                    max = arr[j];
                }
                
                if(j == i+S)
                    break;
            }
            
            //옮길 수 있는 만큼 차감. i 기준임
            S -= idx - i;
            
            for (int j = idx; j > i; j--) {
                tmp = arr[j];
                arr[j] = arr[j-1];
                arr[j-1] = tmp;
            }
            
            if(S == 0)
                return;
        }
        
        // tmp = arr[N-1];
        // arr[N-1] = arr[N-2];
        // arr[N-2] = tmp;
        
        return;
        
    }
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        S = Integer.parseInt(br.readLine());
        
        //사전순으로 뒷선다 => 사전의 맨 뒤에 있을 법 하다
        //맨 앞자리로 가장 큰 값이 와줘야함.. 
        //S번 동안 맨 앞자리로 가장 큰값이.. 차례로 나올 수 있는지??
        if(N > 1) {
            swap();
            // for (int i = 0; i < S; i++) {
                // swap();
            // }
        }
        
        for (int i = 0; i < N; i++) {
            System.out.print(arr[i] + " ");
        }
        
    }
}
