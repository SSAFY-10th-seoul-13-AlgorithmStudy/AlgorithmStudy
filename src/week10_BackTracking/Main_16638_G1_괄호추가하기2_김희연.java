package week10_BackTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_16638_G1_괄호추가하기2_김희연 {
	static int n;
	static int max = Integer.MIN_VALUE;
	static String[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		arr = new String[n];
		String str = br.readLine();

		arr = str.split("");

		int cnt = n/2;
		int target = cnt%2 == 0 ? cnt/2 : (cnt/2)+1;

		calculate(null);

		for(int i=1; i<=target; i++){
			find(0, 1, i, new int[i]);
		}

		System.out.println(max);
	}

	public static void calculate(int[] select){
		String[] now;
		now = arr.clone();

		if(select != null){
			for(int i=0; i<select.length; i++){
				if(select[i] != 0){
					int index = select[i];
					int front = index - 1;
					int back = index + 1;
					int value = 0;
					switch (now[index]){
						case "+":
							value = Integer.parseInt(now[front]) + Integer.parseInt(now[back]);
							break;
						case "-":
							value = Integer.parseInt(now[front]) - Integer.parseInt(now[back]);
							break;
						case "*":
							value = Integer.parseInt(now[front]) * Integer.parseInt(now[back]);
							break;
					}

					now[index] = Integer.toString(value);
					now[front] = now[back] = null;
				}
			}
		}

		for(int i=1; i<n; i++){
			if(now[i] != null && now[i].equals("*")){
				int L = i-1, R = i+1;
				while(L-1 > 0 && now[L] == null){
					--L;
				}
				while(R+1 < n && now[R] == null){
					++R;
				}
				now[i] = Integer.toString(Integer.parseInt(now[L]) * Integer.parseInt(now[R]));
				now[L] = now[R] = null;
			}
		}

		for(int i=1; i<n; i+=2){
			if(now[i] != null && now[i].equals("*")){
				int L = i-1, R = i+1;
				while(L-1 > 0 && now == null)
					--L;
				while(R+1 < n && now[R] == null)
					++R;
				if(now[i].equals("+")) {
					now[i] = Integer.toString(Integer.parseInt(now[L]) + Integer.parseInt(now[R]));
				}else if(now[i].equals("-")) {
					now[i] = Integer.toString(Integer.parseInt(now[L]) - Integer.parseInt(now[R]));
				}
				now[L] = now[R] = null;
			}
		}
		int sum = 0;
		for (int i = 0; i<n; ++i) {
			if (now[i] != null)
				sum += Integer.parseInt(now[i]);
		}
		max = Math.max(max, sum);
	}

	static void find(int cnt, int start, int R, int[] select) {
		if (cnt == R) {
			calculate(select);
			return;
		}

		for (int i = start; i <n; i += 2) {
			if (cnt > 0 && (select[cnt - 1] + 2) == i)
				continue;
			select[cnt] = i;
			find(cnt + 1, i + 2, R, select);
		}
	}
}
