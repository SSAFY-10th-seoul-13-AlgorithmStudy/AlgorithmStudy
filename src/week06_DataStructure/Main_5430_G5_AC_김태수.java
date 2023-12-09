package week06_DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_5430_G5_AC_김태수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		top:
		for(int t = 0; t < T;t++) {
			String cmd = br.readLine();
			int N = Integer.parseInt(br.readLine());
			StringTokenizer nums = new StringTokenizer(br.readLine(), "[,]");
			ArrayList<Integer> list = new ArrayList<>();
			while(nums.hasMoreTokens()) {
				int temp = Integer.parseInt(nums.nextToken());
				list.add(temp);
			}
			boolean flag = true;
			int left = 0;
			int right = list.size();
			for(int i = 0, end = cmd.length(); i < end;i++) {
				if(left == right && cmd.charAt(i) == 'D') {
					System.out.println("error");
					continue top;
				}
				if(cmd.charAt(i) == 'R') {
					flag = !flag;
				} 
				else {
					if(flag) left++;
					else right--;
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append("[");
			if(flag) {
				for(int i = left; i < right;i++)sb.append(list.get(i)).append(",");
			}
			else {
				for(int i = right - 1; i >= left;i--) sb.append(list.get(i)).append(",");
			}
			if(sb.length() != 1) sb.deleteCharAt(sb.length() - 1);
			sb.append("]");
			System.out.println(sb);
		}
		
	}
}
