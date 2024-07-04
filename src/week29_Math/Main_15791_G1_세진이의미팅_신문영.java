package week29_Math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_15791_G1_세진이의미팅_신문영 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        long ans = 0;
        int mod = 1000000007;

        long a = 1;
        long b = 1;
        for (int i = 0; i < r; ++i) {
            a *= (n - i);
            b *= (r - i);
            a %= mod;
            b %= mod;
        }

        // mod가 소수 일 때, a^-1 % mod = a^(mod-2) % mod
        // 1000000007는 소수
        long b2 = 1;
        int exp = mod - 2;
        while(exp > 0) {
            if(exp % 2 > 0) {
                b2 *= b;
                b2 %= mod;
            }

            b*=b;
            b %= mod;
            exp /=2;
        }
        ans = a * b2;
        ans %= mod;

        bw.write(ans + "\n");
        bw.close();
    }
}
