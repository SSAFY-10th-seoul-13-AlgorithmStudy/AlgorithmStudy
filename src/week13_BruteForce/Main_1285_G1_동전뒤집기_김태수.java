package week13_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1285_G1_동전뒤집기_김태수 {
	public static int N;
	public static char[][] map;
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    N = Integer.parseInt(br.readLine());
	    map = new char[N][N];
	    for(int i=0; i<N; i++) {
	      String s = br.readLine();
	      for(int j=0; j<N; j++) {
	        map[i][j] = s.charAt(j);
	      }
	    }
	    int answer = Integer.MAX_VALUE;
	    for(int bit=1; bit < (1 << N); bit++) {
	      int sum = 0;
	      for(int j=0; j<N; j++) {
	        int back = 0;
	        for(int i=0; i<N; i++) {
	          char curr = map[i][j];
	          if((bit & (1<<i)) != 0) {
	            curr = flip(curr);
	          }
	          if(curr == 'T')
	            back++;
	        }
	        sum += Math.min(back, N-back);
	      }
	      answer = Math.min(answer, sum);
	    }
	    System.out.println(answer);
	  }
	  public static char flip(char curr) {
	    if(curr == 'T')
	      return 'H';
	    else 
	      return 'T';
	  }
}
