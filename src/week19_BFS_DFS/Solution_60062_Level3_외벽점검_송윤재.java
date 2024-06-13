import java.util.*;

class Solution {    
    static int w_length, f_length, new_weaks[];
    
    public int solution(int n, int[] weak, int[] dist) {
        w_length = weak.length;
        f_length = dist.length;
        new_weaks = new int[w_length * 2];
        
        for(int i = 0; i < w_length; i++){            
            new_weaks[i] = weak[i];
            new_weaks[i + w_length] = weak[i] + n;
        } // 원을 길이가 두 배인 배열로 표현
        
        int min = f_length;
        boolean flag = false;

        // 모든 친구 순열에 대해 점검
        List<int[]> permutations = getPermutations(dist);
        
        for (int[] perm : permutations) {
            for (int i = 0; i < w_length; i++) {
                int cur_dfs = dfs(0, 0, i, perm);
                if (cur_dfs != -1) {
                    flag = true;
                    min = Math.min(min, cur_dfs);
                }
            }
        }

        if(flag) return min;
        else return -1;
    }
    
    static int dfs(int cnt, int f_idx, int w_idx, int[] friends){
        if(cnt >= w_length) return f_idx;
        if(f_idx == f_length) return -1;
        if(w_idx >= 2 * w_length) return -1;
        
        int target = new_weaks[w_idx] + friends[f_idx]; // 이 친구의 이동거리
        while(w_idx < 2 * w_length && target >= new_weaks[w_idx]){
            cnt++;
            w_idx++;
        }
        return dfs(cnt, f_idx + 1, w_idx, friends);
    }

    private List<int[]> getPermutations(int[] dist) {
        List<int[]> permutations = new ArrayList<>();
        permute(dist, 0, permutations);
        return permutations;
    }
    
    private void permute(int[] arr, int k, List<int[]> permutations) {
        if (k == arr.length) {
            permutations.add(arr.clone());
        } else {
            for (int i = k; i < arr.length; i++) {
                swap(arr, k, i);
                permute(arr, k + 1, permutations);
                swap(arr, k, i);
            }
        }
    }
    
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
