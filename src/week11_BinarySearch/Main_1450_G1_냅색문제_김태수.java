package week11_BinarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_1450_G1_냅색문제_김태수 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        
        int first = N / 2;
        int second = N - first;
        
        ArrayList<Long> arr1 = new ArrayList<Long>();
        ArrayList<Long> arr2 = new ArrayList<Long>();
        
        int[] list1 = new int[first];
        int[] list2 = new int[second];
        
        st = new StringTokenizer(br.readLine());
        
        for(int i = 0; i < first ; i++) {
        	list1[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i< second;i++) {
        	list2[i] = Integer.parseInt(st.nextToken());
        }
        
        arr1.add(0L);
        arr2.add(0L);
        
        dfs(arr1, list1, 0, 0, first);
        dfs(arr2, list2, 0, 0, second);
        
        Collections.sort(arr1);
        Collections.sort(arr2);
        
        long ans = merge(arr1, arr2, C);
        System.out.println(ans);
	}
	
	public static void dfs(ArrayList<Long> arr, int[] list, long sum, int idx, int max) {
		if(max == idx) return;
		arr.add(sum + list[idx]);
		dfs(arr,list,sum+list[idx], idx+1,max);
		dfs(arr,list,sum, idx+1,max);
	}
	
	public static long merge(ArrayList<Long> arr1, ArrayList<Long> arr2, int thres) {
		long result = 0;
		for(int i = 0 ; i < arr1.size();i++) {
			result += binarySearch(arr2, thres - arr1.get(i));
		}
		return result;
	}
	
	public static long binarySearch(ArrayList<Long> arr, long x) {
		int right = 0;
		int left = 0;
		if(x < 0 ) return 0 ;
		else {
			right = arr.size() - 1;
			left = 0;
			
			while(left <= right) {
				int mid = (right - left) / 2 + left;
				if(arr.get(mid) > x) {
					right = mid - 1;
				}
				else {
					left = mid + 1;
				}
			}
		}
		return left;
	}
}
