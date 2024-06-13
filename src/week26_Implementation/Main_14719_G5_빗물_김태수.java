import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		
		int[] map = new int[width];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < width;i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		int sum = 0;
		for(int i = 1 ; i < width - 1;i++) {
			int left = 0, right = 0;
			for(int j = 0 ; j < i; j++) {
				left = Math.max(left, map[j]);
			}
			for(int j = i+1 ; j < width; j++) {
				right = Math.max(right, map[j]);
			}
			
			if(map[i] < left && map[i] < right)
				sum += (Math.min(left, right) - map[i]);
		}
		System.out.println(sum);
	}
}
