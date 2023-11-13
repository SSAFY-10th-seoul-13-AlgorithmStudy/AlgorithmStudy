package week03;

import java.io.*;
import java.util.*;

public class Main_2295_G4_세수의합_김아린 {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());

        //반드시 정렬된 상태로 들어오나? 그런 말이 없음
        ArrayList<Integer> list = new ArrayList<>();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(in.readLine());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                list.add(arr[i] + arr[j]);
            }
        }
        //이분탐색을 위해 정렬해준다
        list.sort(Comparator.naturalOrder());

        //1. for문을 세번 돌린다 (최대 10억...)
        //이때 for문을 돌면서 나온 수가 집합 내에 있는지 확인해줘야함
        //set contains는 상수 시간복잡도
        //응 이렇게 풀면 메모리 초과
//        int ans = case1(arr, set);
//        System.out.println(ans);

        //2. 이분탐색과 중간에서 만나기가 있으니까..
        //for문 한번에, left + right = K 로 할까? 근데 이러면 원하는 값이 안나올 수 있음
        //key인 arr[i] - arr[j]가 존재하는지를 판단하는 이분탐색
        //a+b+c = d 를 a+b = d-c로 변형해서 찾는다..! 와 이걸 어떻게 생각해낼 수 있는거야
        //시간복잡도가 n*n*logN임
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                int key = arr[i] - arr[j];

                int low = 0;
                int high = N*N;

                int mid;

                while(low <= high) {
                    mid = (low + high) / 2;

                    if(list.get(mid) == key) { //존재하면
                        ans = Math.max(ans, arr[i]);
                        low = mid + 1;
                    } else if (list.get(mid) < key) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
            }
        }

        System.out.println(ans);

    }

    public static int case1(int[] arr, Set<Integer> set) {
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    sum = arr[i] + arr[j] + arr[k];
                    if (set.contains(sum)) {
                        ans = sum;
                    }
                }
            }
        }
        return ans;
    }
}

/*
5
2
3
5
10
18
 */