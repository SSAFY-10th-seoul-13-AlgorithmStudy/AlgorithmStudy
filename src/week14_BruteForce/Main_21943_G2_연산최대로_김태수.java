package week14_BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_21943_G2_연산최대로_김태수 {
	static int N,maxA,maxM,answer;
	static int[] list, nums;
	static boolean v[],op[];
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		list = new int[N];
		v = new boolean[N];
		op = new boolean[N];
		nums = new int[N];
		for(int i = 0; i < N;i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		maxA = Integer.parseInt(st.nextToken());
		maxM = Integer.parseInt(st.nextToken());
		permutation(0);
		System.out.println(answer);
	}
	//연산되는 숫자의 순서를 완탐
	static void permutation(int depth) {
		if(depth == N) {
			combination(0,0);
			return;
		}
		for(int i = 0 ; i < N ;i++) {
			if(v[i]) continue;
			nums[depth] = list[i];
			v[i] = true;
			permutation(depth+1);
			v[i] = false;
		}
	}
	static void combination(int depth, int numM) {
		if(numM == maxM) {
			int sum = nums[0];
			ArrayList<Integer> addUps = new ArrayList<>();
			
			for(int i = 0 ; i < N-1;i++) {
				if(!op[i]) sum += nums[i+1];
				else {
					addUps.add(sum);
					sum = nums[i+1];
				}
			}
			
			if(sum != 0) addUps.add(sum);
			int temp = 1;
			for(int i: addUps) temp *= i;
			answer = Math.max(answer, temp);
			return;
		}
		if(depth == N - 1) return;
		op[depth] = true;
		combination(depth+1, numM+1);
		op[depth] = false;
		combination(depth+1,numM);
	}
}
