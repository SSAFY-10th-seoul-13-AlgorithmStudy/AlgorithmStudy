package week49_BinarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_12014_G2_주식_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, K, arr[];
	static List<Integer> lis;
	
	static void init() {
		arr = new int[N];
		lis = new ArrayList<Integer>();
	}
	
	static void input() throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		init();
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
	}
	
	static void solve() {
		lis.add(arr[0]);
		for(int i = 1; i < N; i++) {
			int size = lis.size();
			if(arr[i] > lis.get(size - 1)) {
				lis.add(arr[i]);
			} else {
				lis.set(binarySearch(arr[i]), arr[i]);
			}
		}
		sb.append(lis.size() < K ? 0 : 1).append("\n");
	}
	
    public static int binarySearch(int num) {
        int left = 0;
        int right = lis.size() - 1;
        
        while(left <= right) {
            int mid = (left + right) / 2;
 
            if(lis.get(mid) < num) {
                left = mid + 1;
            } else{
                right = mid - 1;
            }
        }
        return left;
    }

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			sb.append("Case #").append(tc).append("\n");
			input();
			solve();
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
