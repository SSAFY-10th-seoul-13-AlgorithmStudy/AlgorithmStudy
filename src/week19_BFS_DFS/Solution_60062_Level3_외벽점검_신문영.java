package week19_BFS_DFS;

public class Solution_60062_Level3_외벽점검_신문영 {
    int answer = Integer.MAX_VALUE;
    int wallLength;
    int[][] weaks;
    int[] dists;
    boolean[] isSelected;
    public int solution(int n, int[] weak, int[] dist) {
        wallLength = n;
        weaks = new int[weak.length][];
        isSelected = new boolean[dist.length];
        for (int i = 0; i < weak.length; i++) {
            weaks[i] = new int[weak.length];
            int idx = 0;
            for (int j = i; j < i + weak.length; j++) {
                if (j >= weak.length) {
                    weaks[i][idx++] = n + weak[j % weak.length];  
                } else {
                    weaks[i][idx++] = weak[j];
                }
            }
        }

        for (int i = 1; i <= dist.length; i++) {
            dists = new int[i];
            permutation(dist, 0, i);
            if (answer != Integer.MAX_VALUE) return answer;
        }

        return -1;
    }

    public void permutation(int[] dist, int cnt, int select) {
        if (cnt == select) {
            int weakLength = weaks[0].length;
            for (int i = 0; i < weakLength; i++) {
                boolean[] weakVisited = new boolean[wallLength];
                for (int j = 0; j < wallLength; j++) {
                    weakVisited[j] = true;
                }
                
                for (int j = 0; j < weaks[0].length; j++) {
                    weakVisited[weaks[i][j] % wallLength] = false;
                }
                
                int coveringWeakFriendsCount = select;
                for (int j = 0, distIndex = 0; j < weakLength && distIndex < dists.length; j++, distIndex++) {
                    int maxLooping = 0;
                    while (maxLooping < Math.min(weakLength, dists.length) && weakVisited[weaks[i][j] % wallLength]) {
                        j = (j + 1) % weaks[i].length;
                        maxLooping++;
                    }
                    
                    int duplicateLength = 0;
                    for (int k = weaks[i][j]; k <= weaks[i][j] + dists[distIndex]; k++) {
                        if (weakVisited[k % wallLength]) duplicateLength++;
                        weakVisited[k % wallLength] = true;
                    }
                    if (duplicateLength == dists[distIndex] + 1) coveringWeakFriendsCount--;
                }

                boolean covered = false;

                for (int j = 0; j < wallLength; j++) {
                    if (!weakVisited[j]) {
                        covered = false;
                        break;
                    }
                    covered = true;
                }

                if (covered) answer = Math.min(answer, coveringWeakFriendsCount);
            }

            return;
        }

        for (int i = 0; i < dist.length; i++) {
            if (isSelected[i]) continue;
            isSelected[i] = true;
            dists[cnt] = dist[i];
            permutation(dist, cnt + 1, select);
            isSelected[i] = false;
        }
    }
}
