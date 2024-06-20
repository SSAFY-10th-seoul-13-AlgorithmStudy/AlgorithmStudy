package week19_BFS_DFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {

    public int solution(int n, int[] weak, int[] dist) {
        int weakLen = weak.length;
        int[] linearWeak = new int[weakLen * 2];

        for (int i = 0; i < weakLen * 2; i++) {
            if (i < weakLen) {
                linearWeak[i] = weak[i];
            } else {
                linearWeak[i] = n + weak[i - weakLen];
            }
        }

        Arrays.sort(dist);
        reverse(dist);

        for (int i = 1; i <= dist.length; i++) {
            List<List<Integer>> permutation = getPermutations(dist, i);

            for (List<Integer> permu : permutation) {
                for (int j = 0; j < weakLen; j++) {
                    int[] line = Arrays.copyOfRange(linearWeak, j, weakLen + j);

                    for (int p : permu) {
                        line = filterLine(line, line[0] + p);
                        if (line.length == 0)
                            return i;
                    }
                }
            }
        }

        return -1;
    }

    private int[] filterLine(int[] line, int threshold) {
        return Arrays.stream(line)
                .filter(e -> e > threshold)
                .toArray();
    }

    private void reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }

    private List<List<Integer>> getPermutations(int[] arr, int n) {
        List<List<Integer>> result = new ArrayList<>();
        if (n == 1) {
            for (int el : arr) {
                result.add(Collections.singletonList(el));
            }
            return result;
        }

        for (int i = 0; i < arr.length; i++) {
            int[] rest = new int[arr.length - 1];
            System.arraycopy(arr, 0, rest, 0, i);
            System.arraycopy(arr, i + 1, rest, i, arr.length - i - 1);

            List<List<Integer>> perms = getPermutations(rest, n - 1);
            for (List<Integer> perm : perms) {
                List<Integer> attached = new ArrayList<>();
                attached.add(arr[i]);
                attached.addAll(perm);
                result.add(attached);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 12;
        int[] weak = { 1, 5, 6, 10 };
        int[] dist = { 1, 2, 3, 4 };
        System.out.println(sol.solution(n, weak, dist));
    }
}