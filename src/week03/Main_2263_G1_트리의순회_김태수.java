package week03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2263_G1_트리의순회_김태수 {
	static int idx;
	static int[] in,pre,post;
	public static void main(String[] args) throws NumberFormatException, IOException {
		//이런 문제 만나면 벽느낌 ㄹㅇ
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
	
		in = new int[N];
		pre = new int[N];
		post = new int[N];
		idx = 0;
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N;i++) in[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N;i++) post[i] = Integer.parseInt(st.nextToken());
		
		makePre(0,N-1,0,N-1);
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N;i++) sb.append(pre[i]).append(" ");
		System.out.println(sb);
	}
	
	public static void makePre(int inSt, int inEnd, int preSt, int preEnd) {
		if(inSt <= inEnd && preSt <= preEnd) {
			pre[idx++] = post[preEnd];
			int position = inSt;
			for(int i = inSt; i<= inEnd;i++) {
				if(in[i] == post[preEnd]) {
					position = i;
					break;
				}
			}
			makePre(inSt, position-1,preSt, preSt + position - inSt - 1);
			makePre(position+1, inEnd,preSt + position - inSt, preEnd - 1);
		}
		
	}
}
