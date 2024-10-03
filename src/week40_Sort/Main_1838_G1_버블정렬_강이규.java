package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_1838_G1_버블정렬_강이규 {

    static int N;
    static int[] arr;
    static int[] sortedArr; // 인덱스 비교를 위해 정렬할 배열


    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        /*
        정렬한 배열에서의 위치와 비교한다.
        그것과 순서를 맞추기 위해, 오른쪽으로 가는 건 한번에 쭉 갈 수 있으므로 필요한 i의 카운트가 최대 1이다.
        왼쪽으로 오는 건, 반복문의 진행방향 때문에 거리만큼의 i 카운트가 필요하다.
        순서를 맞추기 위해 필요한 i 카운트의 최대값을 찾는다.
         */
        Map<Integer, Integer> idxs = new HashMap<>();
        for (int i = 0; i < N; i++) {
            idxs.put(sortedArr[i], i);
        }

        int maxMoveCnt = 1;
        for (int i = 0; i < N; i++) {
            int idxAfterSort = idxs.get(arr[i]);
            maxMoveCnt = Math.min(maxMoveCnt, idxAfterSort - i);
        }

        System.out.println(maxMoveCnt != 1 ? -(maxMoveCnt) : maxMoveCnt);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        sortedArr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sortedArr[i] = arr[i];
        }

        Arrays.sort(sortedArr);
    }
}
