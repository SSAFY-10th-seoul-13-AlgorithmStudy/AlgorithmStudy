package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_14719_G5_빗물_강이규 {

    static int H, W;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(solve());
    }

    private static int solve() {
        int res = 0;
        Stack<Integer> s = new Stack<>();

        int max = arr[0];
        s.push(arr[0]);
        for (int i = 1; i < W; i++) {
            int cur = arr[i];

            List<Integer> tmpList = new ArrayList<>();
            // cur 와 max 사이에 빗물을 채운다.
            while (!s.isEmpty() && s.peek() <= cur) {
                int pop = s.pop();
                if (cur >= max) { // cur가 제일 크다 = cur 앞에껀 더이상 필요 없다.
                    res += max - pop;
                } else { // cur가 더 작다 = 뒤에서 더 채워질 여지가 있다.
                    res += cur - pop;
                    tmpList.add(cur);
                }
            }
            // 비었거나, cur가 더 작은 경우에만 내려온다.
            if (max > cur) { // 이후에 더 채워질 여지가 있는 경우
                for (int tmp : tmpList) {
                    s.push(tmp);
                }
            } else {
                max = cur;
            }
            s.push(cur);
        }
        return res;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[W];
        for (int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }
}
