class Solution {
    static public int[] weak_append;
    static public int answer;

    public int solution(int n, int[] weak, int[] dist) {
        answer = Integer.MAX_VALUE;
        weak_append = new int[weak.length * 2 + 1];

        // 원형을 펴서 직선으로 만듦
        int i = 0;
        while (i < weak.length) {
            weak_append[i] = weak[i];
            weak_append[i + weak.length] = weak[i++] + n;
        }

        for (int k = 0; k < weak.length; k++) {
            dfs(k, 0, dist, new boolean[dist.length], new int[dist.length]);
        }

        // 친구들의 능력이 안되는 경우
        if (answer == Integer.MAX_VALUE) return -1;

        return answer;
    }

    // DFS를 이용한 순열 생성
    public void dfs(int start, int depth, int[] dist, boolean[] visited, int[] permuted) {
        if (depth == dist.length) { // 모든 순열이 생성된 경우
            answer = Math.min(answer, check(start, start + weak_append.length / 2, permuted));
            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            permuted[depth] = dist[i];
            dfs(start, depth + 1, dist, visited, permuted);
            visited[i] = false;
        }
    }

    // 주어진 순열로 외벽 점검
    public int check(int start, int end, int[] permuted) {
        int friend = 1; // 첫 번째 친구부터 시작
        int pos = weak_append[start] + permuted[friend - 1]; // 첫 취약점의 위치 + 친구의 이동 거리

        for (int i = start; i < end; i++) {
            if (pos < weak_append[i]) { // 현재 친구가 점검할 수 없는 경우
                friend++; // 다음!
                if (friend > permuted.length) return Integer.MAX_VALUE; // 모든 애들이 해도 안되면?
                pos = weak_append[i] + permuted[friend - 1]; // 새로운 친구의 점검 위치 갱신
            }
        }
        return friend;
    }
}
