package week08_DynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_18427_G4_함께블록쌓기_김태수 {
	static int[] dp;
	static int N,M,H;
	static ArrayList<Integer>[] blocks;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int MOD = 10_007;
		N = Integer.parseInt(st.nextToken());	//사람수
		M = Integer.parseInt(st.nextToken());	//블록수
		H = Integer.parseInt(st.nextToken());	//타겟 무게
		blocks = new ArrayList[N];
		dp = new int[H+1];
		for(int i = 0 ; i < N;i++) {
			blocks[i] = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) blocks[i].add(Integer.parseInt(st.nextToken()));
		}
		//모든 사람들이 가지고 있는 블록에 대하여
		for(ArrayList<Integer> block: blocks) {
			//인덱스가 뒤로 밀리는 구조: 역순으로 해야 안꼬임
			for(int i = H; i>=0;i--) {
				//블럭합이 없는 곳이라면 패스
				if(dp[i] == 0) continue;
				for(int item: block) {
					if((item + i) > H) continue;
					//더한 값 만큼 인덱스를 바꾸어서 저장
					dp[item+i] = (dp[item+i]+dp[i]) % MOD;
				}
			}
			//깡으로 더해주기
			for(int item: block) {
				dp[item] += 1;
				dp[item] %= MOD;
			}
		}
		System.out.println(dp[H] % MOD);
 	}
}
