package week08_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_16565_G2_N포커_김태수 {
	public static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int MOD = 10_007;
		int[][] comb = new int[53][53];
		for(int i = 0 ; i<= 52;i++) comb[i][0] = 1;
		//파스칼 정리
		for(int i = 1; i <= 52;i++) {
			for(int j = 1 ; j <= 52;j++) {
				comb[i][j] = (comb[i-1][j] + comb[i-1][j-1]) % MOD;
			}
		}
		int ans = 0;
		//포함 배제의 원리: https://burningjeong.tistory.com/588
		//뽑는 카드보다 작은 4의 배수들에 포함배제의 원리를 적용해야한다..
		//ex) N=8일때: 1종류의 포카드를 갖는 수 - 2종류의 포카드를 갖는 수
		//ex) N=12일때:1종류의 포카드를 갖는 수 - 2종류의 포카드를 갖는 수 + 3종류의 포카드를 갖는 수
		//...
		
		for(int i = 4 ; i <= N;i+=4) {
			//홀수 종류의 포카드를 갖는 수
			if((i/4) % 2 == 1) {
				ans += (comb[13][i/4] * comb[52-i][N-i]) % MOD;
			}
			//짝수 종류의 포카드를 갖는 수
			else {
				//mod와 뺄셈 같이 할때 주의점 
				//(A-B)%MOD = ((A%MOD) - B%MOD + "MOD") % MOD
				//뺄셈 연산시 "MOD"를 추가적으로 더해줘야 한다
				//파이썬은 자동 보정이 되는데, C는 안된단다..
				ans = (ans - comb[13][i/4] * comb[52-i][N-i] % MOD + MOD) % MOD;
			}
		}
		System.out.println(ans % MOD);
 	}
}
