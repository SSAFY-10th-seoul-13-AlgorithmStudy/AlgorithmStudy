package week11_BinarySearch;

import java.io.*;
import java.util.*;

public class Main_1450_G1_냅색문제_김아린 {
    static int c; // 최대 무게
    static int[] arr; // 물건들의 무게 배열
    static List<Integer> left, right; // 반으로 나눠서 저장할 리스트
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); 
        c = Integer.parseInt(st.nextToken()); 

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        left = new ArrayList<>();
        right = new ArrayList<>(); 
        comb(left, 0, n/2, 0); // 무게 조합 left
        comb(right, n/2, n, 0); // 무게 조합 right
        right.sort((a,b) -> (a-b)); // right 오름차순으로 정렬

        int cnt = 0;
        int idx = 0;
        for(int i=0; i<left.size(); i++) {
            idx = upperbound(0, right.size()-1, left.get(i));
            cnt += idx+1;
        }
        System.out.println(cnt);
    }

    // 이분 탐색
    static int upperbound(int s, int e, int val) {
        while(s <= e) {
            int mid = (s+e)/2;
            if(right.get(mid) <= c-val) {
                s = mid+1;
            }else {
                e = mid-1;
            }
        }
        return e;
    }

    static void comb(List<Integer> list, int start, int end, int sum) {
        if(sum > c) return; 
        if(start == end) { 
            list.add(sum);
            return;
        }
        comb(list, start+1, end, sum); //선택하지 않은 경우
        comb(list, start+1, end, sum + arr[start]); // 선택한 경우
    }
}