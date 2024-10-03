//https://tussle.tistory.com/1035

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int [] arr, cnt;
    static  StringTokenizer st;
    static Set<Integer> set;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        arr = new int[N];
        set = new HashSet<>();
        
        st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        long result = 0;
        int start = 0;
        
        for(int i = 0; i < N; i++){
            if(set.contains(arr[i])) {
                for(int j = start; j < i; j++){
                    result += i-j;
                    start++;
                    if(arr[j] == arr[i])
                        break;
                    set.remove(arr[j]);
                }
            } else {
                set.add(arr[i]);
            }
        }
        
        for(int i = start; i < N; i++) {
            result += N-i;
        }
        
        System.out.println(result);
    }
}
