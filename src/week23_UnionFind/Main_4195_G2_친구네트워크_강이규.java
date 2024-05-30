package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_4195_G2_친구네트워크_강이규 {
    /**
     * union하면서, root끼리 비교하며 숫자가 작은 쪽의 root를 큰 쪽의 root로
     */

    static int N;
    static Map<String, Integer> nameMapper;
    static int[] parent;
    static int[] nums;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

    private static int union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot == bRoot)
            return nums[aRoot];

        int bigger, smaller;
        if (nums[aRoot] >= nums[bRoot]) {
            bigger = aRoot;
            smaller = bRoot;
        } else {
            bigger = bRoot;
            smaller = aRoot;
        }
        parent[smaller] = bigger;
        nums[bigger] += nums[smaller];
        return nums[bigger];
    }

    private static void make(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
        nums = new int[size];
        Arrays.fill(nums, 1);
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            List<Integer> aList = new ArrayList<>();
            List<Integer> bList = new ArrayList<>();
            nameMapper = new HashMap<>();

            // get input
            int size = 0;
            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String t1 = st.nextToken();
                String t2 = st.nextToken();
                nameMapper.putIfAbsent(t1, size++);
                nameMapper.putIfAbsent(t2, size++);

                aList.add(nameMapper.get(t1));
                bList.add(nameMapper.get(t2));
            }
            make(size);

            // do logic
            for (int i = 0; i < N; i++) {
                int a = aList.get(i);
                int b = bList.get(i);

                sb.append(union(a, b)).append("\n");
            }
        }
        System.out.print(sb);
    }
}
