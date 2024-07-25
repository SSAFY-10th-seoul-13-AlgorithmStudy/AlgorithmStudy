import java.io.*;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String cat = br.readLine();

        int length = cat.length();
        int left = 0;
        int right = 0;
        int max = 0;
        int[] alpha = new int[26];
        int count = 0;

        while (right < length) {
            int idx = cat.charAt(right) - 'a';
            if (alpha[idx] == 0) {
                count++;
            }
            alpha[idx]++;
            
            if(count > N) {
                int leftIdx = cat.charAt(left) - 'a';
                alpha[leftIdx]--;
                if (alpha[leftIdx] == 0) {
                    count--;
                }
                left++;
            } else {
                max = Math.max(max, right - left + 1);
            }
            
            right++;
           
        }

        System.out.println(max);
    }
}
