package week06_DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import javax.swing.text.AbstractDocument.BranchElement;

public class Main_2696_G2_중앙값구하기_김태수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		StringBuilder sb = null;
		for(int t = 0 ; t < T ;t++) {
			int N = Integer.parseInt(br.readLine());
			System.out.println((N+1)/2);
			PriorityQueue<Integer> left = new PriorityQueue<>((o1,o2)->{return o2-o1;});
			PriorityQueue<Integer> right = new PriorityQueue<>();
			sb = new StringBuilder();
			int line = 0;
			for(int i = 0 ; i < N;i++) {
				if(i % 10 == 0) st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				
				//right.add(num);

				if(left.size() == right.size()) left.add(num);
				else right.add(num);
				
				if(!right.isEmpty() && left.peek() > right.peek()) {
					int temp1 = right.poll();
					int temp2 = left.poll();
					right.add(temp2);
					left.add(temp1);
				}
				
				if(i % 2 == 0) {
					if(line == 9 || i == N-1) {
						sb.append(left.peek()).append("\n");
					}
					else {
						sb.append(left.peek()).append(" ");
					}
					line++;
				}
//				if(i % 2 == 0) {
//					sb.append(right.toArray()[i/2]);
//					if(line == 9 || i == N -1) sb.append("\n");
//					else sb.append(" ");
//				}
//				for(int tt: right) {
//					System.out.print(tt);
//				}
//				System.out.println();
			}
			System.out.print(sb);
		}
		
	}
}
