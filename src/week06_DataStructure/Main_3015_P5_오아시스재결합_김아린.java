package week06_DataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;


//https://lotuslee.tistory.com/105
class Aud {
	int cnt, h;

	public Aud(int cnt, int h) {
		this.cnt = cnt;
		this.h = h;
	}
}

public class Main_3015_P5_오아시스재결합_김아린 {	
	public static void main(String[] args) throws IOException{
		//소 머리랑 비슷하네
		//long에 주의
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long cnt = 0;
		Stack<Aud> stack = new Stack<Aud>();
		for (int i = 0; i < N; i++) {
			int cur = Integer.parseInt(br.readLine());
			//내림차순으로 정렬이 되어야 함
			// => 들어올 애가 스택의 맨 앞보다 크다면 뒤에 올 사람들은 못보니까 pop
			Aud aud = new Aud(1, cur);
			
			while(!stack.isEmpty() && stack.peek().h <= cur) {
				Aud tmp = stack.pop();
				cnt += tmp.cnt;
				
				//키가 같을때..!!
				if(tmp.h == cur) {
					aud.cnt += tmp.cnt;
				}
			}
			
			if(!stack.isEmpty())
				cnt++;
			
			stack.push(aud);
		}
		
		System.out.println(cnt);
	}
}
