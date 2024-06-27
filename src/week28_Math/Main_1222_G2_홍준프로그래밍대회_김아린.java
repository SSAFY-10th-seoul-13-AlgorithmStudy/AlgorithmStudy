import java.util.*;
import java.io.*;

public class Main {
    static int size;
    static long[] count = new long[2000001];

    public static void main(String[] args) throws Exception {
      //수학문제는 이해가 안돼..
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        size = Integer.parseInt(br.readLine());
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            divide(Integer.parseInt(st.nextToken()));
        }

        long max = 0;
        for (long i = 1; i <= 2000000; i++) {
            if (count[(int) i] < 2) {
                continue; // count가 2 미만인 경우 조건 충족 X 넘어감
            }
            max = Math.max(max, count[(int) i] * i);
        }

        System.out.println(max);
    }

    private static void divide(int num) {
        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) { //약수인 경우
                count[i]++;
                if (i != num / i)
                    count[num / i]++; // num / i도 약수임
            }
        }
    }
}
