import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<Integer> list;
    static int[] switchs, arr;
    static HashMap<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) throws Exception {
        // 출력되는 답의 순서는 노상관?
        // 가장 긴 증가하는 수열
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        switchs = new int[N];
        arr = new int[N];
        
        String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			map.put(Integer.parseInt(input[i]), i);
			switchs[i] = Integer.parseInt(input[i]);
		}

		input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = map.get(Integer.parseInt(input[i]));
		}
        
        // 가장 긴 증가하는 수열
        // 🔹 전구를 입력받고, 스위치와 연결해준다.
        // - 전구 번호와 스위치를 연결해 준다. 
        // - 만약 4번 전구를 입력받았다면 4번 전구에 해당하는 스위치의 인덱스인 1이 저장되어야 한다.
        
        // for (int i = 0; i < N; i++) {
            // System.out.println(list.get(i));
        // }
        
        
        //LIS를 저장하면서 이분탐색으로 찾기
        ArrayList<Integer> ans = findLIS();
        
        Collections.sort(ans);
        
        System.out.println(ans.size());
        for (int i : ans) {
            System.out.print(i + " ");
        }
    }
    
    public static ArrayList<Integer> findLIS() {
        
        ArrayList<Integer> lis = new ArrayList<>();
        int[] predec = new int[N];
        int[] lisIndices = new int[N];
        int length = 0;

        for (int i = 0; i < N; i++) {
            // 이분 탐색
            int left = 0, right = length;
            while (left < right) {
                int mid = (left + right) / 2;
                if (lis.get(mid) < arr[i]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            // 새로운 LIS 길이에 대한 인덱스 및 값 업데이트
            if (left == length) {
                lis.add(arr[i]);
            } else {
                lis.set(left, arr[i]);
            }

            // 이전 인덱스 저장
            lisIndices[left] = i;
            predec[i] = (left > 0) ? lisIndices[left - 1] : -1;

            // 최대 길이 업데이트
            if (left == length) {
                length++;
            }
        }

        // 최장 증가 부분 수열 추적
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = lisIndices[length - 1]; i >= 0; i = predec[i]) {
            int tmp = arr[i];
            result.add(switchs[tmp]);
            // result.add(arr[i]);
        }

        return result;
    }   
}
