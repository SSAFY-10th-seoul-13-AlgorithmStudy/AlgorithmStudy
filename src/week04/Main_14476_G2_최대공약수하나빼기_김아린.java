package week04;

////https://velog.io/@hyez_dev/%EB%B0%B1%EC%A4%80-14476-%EC%B5%9C%EB%8C%80%EA%B3%B5%EC%95%BD%EC%88%98-%ED%95%98%EB%82%98-%EB%B9%BC%EA%B8%B0-C

import java.io.*;
import java.util.*;

public class Main_14476_G2_최대공약수하나빼기_김아린 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());
        StringTokenizer st = new StringTokenizer(in.readLine());

        int[] num = new int[N];
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int ans = -1;
        int[] LR = new int[N];
        int[] RL = new int[N];
        LR[0] = num[0];
        RL[N-1] = num[N-1];

        //왼->오 여기까지는 생각했는데
        for (int i = 1; i < N; i++) {
            LR[i] = gcd(LR[i-1], num[i]);
        }
        //오->왼
        for (int i = N-2; i >= 0; i--) {
            RL[i] = gcd(RL[i+1], num[i]);
        }

        int result = 0, target = 0;
        //하나씩 제거해보기
        for(int i = 0; i < N; i++) {
            if(i == 0) { //num[0]을 제외했을 때 RL[1] 값이 최대
                result = RL[1];
            }
            else if (i == N - 1) { //num[n-1]을 제외했을 때 LR[n-2] 값이 최대
                result = LR[N-2];
            }
            else { //LR[i-1]과 RL[i+1]의 최대공약수
                result = gcd(LR[i-1], RL[i+1]);
            }
            if (ans < result) {
                ans = Math.max(ans, result);
                target = num[i];
            }
        }

        if(target % ans != 0)
            System.out.println(ans + " " + target);
        else
            System.out.println(-1);
    }

    //반복문으로 함 재귀도 있음 
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }
}