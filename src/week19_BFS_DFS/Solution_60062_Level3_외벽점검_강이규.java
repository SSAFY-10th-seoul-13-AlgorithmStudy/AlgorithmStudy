package programmers.kakao;

public class Solution_60062_Level3_외벽점검_강이규 {

    static int N, wLen, dLen;
    static int[] weakLinear;
    static int[] dist;
    static int min = 9;
    static boolean[] visited;
    static int[] selected;

    public int solution(int n, int[] weak, int[] dist) {
        N = n;
        this.dist = dist;
        wLen = weak.length;
        dLen = dist.length;

        // weak를 원형 -> 직선 형태로 초기화(시작점 - 끝점 간 편한 연결을 위해)
        weakLinear = new int[weak.length*2];
        for (int i = 0; i < wLen; i++) {
            weakLinear[i] = weak[i];
            weakLinear[i+wLen] = weak[i] + N;
        }

        visited = new boolean[dLen];
        selected = new int[dLen];

        permutation(0);

        return min != 9 ? min : -1;
    }

    // 만들어지는 모든 순열로, 모든 weak를 시작점으로 탐색
    // weak를 다 커버했을 때 더 안 세고 끊어주는 건 check()에서 해준다.
    private void permutation(int depth) {
        if (depth == dLen) {
            for (int i = 0; i < wLen; i++) {
                min = Math.min(min, check(i, i + wLen));
            }
            return;
        }
        for (int i = 0; i < dLen; i++) {
            if (visited[i])
                continue;
            selected[depth] = dist[i];
            visited[i] = true;
            permutation(depth+1);
            visited[i] = false;
        }
    }

    private int check(int start, int end) {
        int friendNum = 0;
        int coveredRange = weakLinear[start];

        for (int i = start; i < end; i++) {
            if (weakLinear[i] <= coveredRange)
                continue;
            if (friendNum == dLen)
                return 9; // 친구를 모두 선택해도 모자랄 때
            coveredRange = weakLinear[i] + selected[friendNum++]; // 주의! 커버된 범위는 중간이 비어있을 수 있으므로
        }
        return friendNum;
    }
}
