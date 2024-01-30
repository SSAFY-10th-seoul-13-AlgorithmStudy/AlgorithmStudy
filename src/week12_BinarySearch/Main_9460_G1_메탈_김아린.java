package week12_BinarySearch;

import java.io.*;
import java.util.*;

class Point {
	int x, y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main_9460_G1_메탈_김아린 {
	static Point[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for (int testcase = 1; testcase <= T; testcase++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int n = Integer.parseInt(st.nextToken());
        	int k = Integer.parseInt(st.nextToken());
        	
        	st = new StringTokenizer(br.readLine());
        	
        	list = new Point[n];
        	for (int i = 0; i < n; i++) {
        		int a = Integer.parseInt(st.nextToken());
        		int b = Integer.parseInt(st.nextToken());
        		
        		list[i] = new Point(a, b);
        	}
        	
        	//두 점이 같은 x좌표를 갖는 일은 없다
        	Arrays.sort(list, Comparator.comparing(p -> p.x));
        	
        	//이분탐색 슛
        	double l = 0, r = 200_000_000, mid = 0;
        	//와 
        	while (0.01 < r - l) {
                mid = (l + r) / 2;
                if (is(n, k, mid)) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            System.out.printf("%.1f\n", mid);
        }

    }

    private static boolean is(int n,int k, double maxi) {
      	int min = list[0].y, max = list[0].y;
      	int cnt = 1;
      	
    	for (int i = 1; i < n; ++i) {
    		//아 중간값 
    		min = Math.min(min, list[i].y);
    		max = Math.max(max, list[i].y);
    		
    		if (2 * maxi <= (max - min)) {
    			cnt++;
    			min = max = list[i].y;
    		}
    	}
        
    	return cnt <= k;
    }

}