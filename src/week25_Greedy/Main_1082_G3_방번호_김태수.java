package week25_Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main_1082_G3_방번호_김태수 {

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());

        int[] cost = new int[N];
        List<int[]> list = new ArrayList<>();

        String[] splitedLine = in.readLine().split(" ");
        for (int i = 0; i < N; ++i) {
            int value = Integer.parseInt(splitedLine[i]);
            cost[i] = value;
            list.add(new int[]{i, value});
        }

        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[1] == b[1])
                    return b[0] - a[0];
                return b[1] - a[1];
            }
        });

        int target = Integer.parseInt(in.readLine());

        if (N == 1) {
            System.out.println(0);
            return;
        }

        String base = "";
        int length = 0;
        if (list.get(N - 1)[0] == 0) {
            int tempCost = target - list.get(N - 2)[1];
            if (tempCost < 0) {
                System.out.println(0);
                return;
            }
            base += Integer.toString(list.get(N - 2)[0]);
            length = tempCost / list.get(N - 1)[1];
            for (int i = 0; i < length; ++i)
                base += Integer.toString(list.get(N - 1)[0]);
            target = tempCost - list.get(N - 1)[1] * length;
        } else {
            length = target / list.get(N - 1)[1];
            for (int i = 0; i < length; ++i)
                base += Integer.toString(list.get(N - 1)[0]);
            target = target - list.get(N - 1)[1] * length;
        }

        char[] arr = base.toCharArray();
        for (int i = 0; i < base.length(); i++) {
            int curNumberCost = cost[arr[i] - '0'];
            for (int j = N - 1; j >= 0; j--) {
                if (target - (cost[j] - curNumberCost) >= 0) {
                    target = target - (cost[j] - curNumberCost);
                    arr[i] = (char)(j + '0');
                    break;
                }
            }
        }
        System.out.println(arr);
    }
}
