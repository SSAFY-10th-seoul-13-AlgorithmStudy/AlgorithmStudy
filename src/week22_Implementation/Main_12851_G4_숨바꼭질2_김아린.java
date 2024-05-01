import java.util.*;
import java.io.*;

public class Main {
    private static int n, k, cnt = 0, ans;
    private static boolean[] visited = new boolean[100001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        if (n == k) {
            System.out.println("0");
            System.out.println("1");
        } else {
            bfs(n);
            System.out.println(ans);
            System.out.println(cnt);
        }
    }
    
    private static void bfs(int start) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start, 0});

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int currentPosition = current[0];
            int currentTime = current[1];
            visited[currentPosition] = true;

            if (currentPosition == k) {
                if (cnt == 0) {
                    ans = currentTime;
                    cnt++;
                } else if (ans == currentTime) {
                    cnt++;
                }
            }

            int[] nextPositions = {currentPosition - 1, currentPosition + 1, currentPosition * 2};
            for (int nextPosition : nextPositions) {
                if (nextPosition >= 0 && nextPosition <= 100000 && !visited[nextPosition]) {
                    q.offer(new int[]{nextPosition, currentTime + 1});
                }
            }
        }
    }
}
