package week22_Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_12851_G4_숨바꼭질2_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, K;
    static int[] dir = {-1, 1, 0};
    static int[] time = new int[100001];
    
    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }
    
    static void solve(){
        int arrive_time = 100000;
        int ways = 0;
        if(K <= N) {
            arrive_time = N - K;
            ways = 1;
            sb.append(arrive_time).append("\n").append(ways);
            return;
        }
        Queue<Integer> que = new LinkedList<>();
        que.offer(N);

        while(!que.isEmpty()){
            int cur = que.poll();
            if(time[cur] >= arrive_time){ break; }
            dir[2] = cur; // dir = { -1, 1, X }
            for(int i = 0; i < 3; i++){
                int next = cur + dir[i];
                if(next < 0 || next > 100000){continue;}
                if(next == K){
                    ways++;
                    time[next] = time[cur] + 1;
                    arrive_time = time[next];
                }
                else if(time[next] == 0 || time[next] == time[cur] + 1){
                    que.offer(next);
                    time[next] = time[cur] + 1;
                }
            }
        }
        sb.append(arrive_time).append("\n").append(ways);
	}
	
	public static void main(String[] args) throws IOException {		
		input();
		solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}