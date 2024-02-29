package week15_TwoPointer;
import java.io.*;
import java.util.*;

class Solution_67258_Level3_보석쇼핑_강이규 {

    static String[] gems;
    static int N;
    static int M; // 보석 종류 수
    static Map<String, Integer> cnts;
    static int mapSize;
    static int minLength;
    static int resLeft, resRight;

    public int[] solution(String[] input) throws IOException {
        init(input);
        search();
        int[] res = {resLeft+1, resRight+1};
        return res;
    }

    private static void init(String[] input) throws IOException {
        N = input.length;
        gems = input;
        cnts = new HashMap<>();
        // init M
        cntGemTypes();

        mapSize = 0;
        minLength = Integer.MAX_VALUE;
        resLeft = -1;
        resRight = -1;
    }

    private static void cntGemTypes() {
        Set<String> types = new HashSet<>();
        for (int i = 0; i < N; i++) {
            if (!types.contains(gems[i])) types.add(gems[i]);
        }
        M = types.size();
    }

    private static void search() {
        // 투포인터 형식 탐색
        int left = 0;
        int right = 0;
        cnts.put(gems[0], 1);
        mapSize++;
        while (right < N) { // left > right 될 일은 없음
            if (mapSize < M) { // 다 못 찾음
                if (++right >= N)
                    break;
                String curGem = gems[right];
                if (cnts.get(curGem) == null) {
                    cnts.put(curGem, 1);
                    mapSize++;
                } else {
                    cnts.put(curGem, cnts.get(curGem) + 1);
                }
                // right++;
                continue;
            }
            // 다 찾음
            // 기록
            if (right - left < minLength) {// min인 경우가 여러개일 때, left가 가장 왼쪽인걸 반환
                resLeft = left;
                resRight = right;
                minLength = right - left;
            }
            // left 이동
            int target = cnts.get(gems[left]);
            if (target != 1) {
                cnts.put(gems[left], target-1);
            } else {
                cnts.remove(gems[left]);
                mapSize--;
            }
            left++;
        }
    }
}