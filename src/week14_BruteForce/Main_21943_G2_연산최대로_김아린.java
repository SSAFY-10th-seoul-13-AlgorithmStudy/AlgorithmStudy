import java.util.*;
import java.io.*;

// 정렬: 주어진 배열을 정렬. 이렇게 하면 순열을 생성할 때 순서를 고려 가능.
// 순열 생성: permute 함수를 호출하여 모든 가능한 순열을 생성.
// 백트래킹: 각 순열에 대해 dfs 함수를 호출하여 백트래킹을 시작. 여기서 백트래킹은 모든 가능한 조합을 탐색하면서, 특정 조건을 만족하지 않는 경로는 더 이상 탐색하지 않음.
// 계산: dfs 함수에서는 주어진 조건을 만족하는지 확인하고, 마지막 원소에 도달했을 때는 계산을 수행하여 최대값을 갱신합니다. 계산은 각 서브셋의 합을 곱함.


public class Main {
    static int N, P, Q;
    static int[] arr;
    static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String[] line = br.readLine().split(" ");
        P = Integer.parseInt(line[0]);
        Q = Integer.parseInt(line[1]);

        // 배열 정렬
        Arrays.sort(arr);

        // 모든 순열에 대해 dfs 수행
        permute(arr, 0);

        // 결과 출력
        System.out.println(result);
    }

    private static void dfs(int idx, int cnt, List<Integer> position, int[] perm) {
        // 백트래킹 조건
        if ((cnt + N - 1 - idx) < Q) {
            return;
        }

        // 마지막 원소에 도달한 경우, 계산 수행
        if (idx == N - 1) {
            position.add(idx);
            long mulVal = 1;
            int sumVal = 0;
            int cuIdx = 0;
            for (int mulIdx : position) {
                while (cuIdx <= mulIdx) {
                    sumVal += perm[cuIdx];
                    cuIdx++;
                }
                mulVal *= sumVal;
                sumVal = 0;
            }
            result = Math.max(result, mulVal);
            position.remove(position.size() - 1);
            return;
        }

        // 다음 원소 탐색
        dfs(idx + 1, cnt, position, perm);
        position.add(idx);
        if (cnt + 1 <= Q) {
            dfs(idx + 1, cnt + 1, position, perm);
        }
        position.remove(position.size() - 1);
    }

    // 배열의 모든 순열 생성
    private static void permute(int[] array, int start) {
        if (start == array.length - 1) {
            dfs(0, 0, new ArrayList<>(), array);
        } else {
            for (int i = start; i < array.length; i++) {
                swap(array, i, start);
                permute(array, start + 1);
                swap(array, i, start);
            }
        }
    }

    // 교환
    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
