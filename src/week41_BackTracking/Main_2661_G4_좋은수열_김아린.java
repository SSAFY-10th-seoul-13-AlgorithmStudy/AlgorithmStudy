import java.io.*;
import java.util.*;

public class Main {
	static int N;
  static BufferedWriter bw;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
        
		back("1");
	}

	public static void back(String str) throws IOException {
		if(str.length() == N) {
            System.out.println(str);
            System.exit(0);
		}

		for(int i = 1; i <= 3; i++) {
			if(find(str+i)) { //좋은 수열인지 확인
				back(str+i);
			}
		}
	}
	public static boolean find(String str) {
    int size = str.length();
		for(int j = 1; j <= size / 2; j++) {
			for(int k = 0; k <= size - 2 * j; k++) {
        //첫번째 부분문자열 : str.substring(k, k+j)
        //두번째 부분문자열 : str.substring(k+j, k+j+j)
				if(str.substring(k, k+j).equals(str.substring(k+j, k+j+j))) {
					return false;
				}
			}
		}
		return true;
	}

}
