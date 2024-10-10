import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[] arr, brr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        brr = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            brr[i] = arr[i];
        }
        
        Arrays.sort(brr);
        int flag, temp = 0;
        
        //아 이해했다
        //얼만큼 회전을 할거냐 = 원소 중 가장 최악으로 돌아가는 애의 횟수
        int max = -1;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, i - Arrays.binarySearch(brr, arr[i]));
        }
        
        System.out.println(max);
    }
}
