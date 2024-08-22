import java.util.*;
import java.io.*;

public class Main {
    static final int MAX = 40000 + 1;
    static int N, M;
    static int[][] depth = new int[MAX][2];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int x, y, x2, y2;
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken()); // 처음 0, 0 처리
        
        for (int i = 0; i < N / 2 - 1; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            
            for (int j = x; j < x2; j++) {
                depth[j][0] = y;
            }
        }
        
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken()); // 마지막 (x, 0) 처리

        int row = x;
        M = Integer.parseInt(br.readLine());
        List<Integer> hole = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            // 해당 칸만 알면 됨
            hole.add(x);
        }
        
        for (int i = 0; i < hole.size(); i++) {
            x = hole.get(i);
            int height = depth[x][0];
            depth[x][1] = height;

            // 왼
            for (int j = x - 1; j >= 0; j--) {
                // 더 이상 깊어질 수 없음
                height = Math.min(height, depth[j][0]);
                // 최대 깊이만큼 물이 빠짐
                depth[j][1] = Math.max(depth[j][1], height);
            }
            height = depth[x][0];
            // 오
            for (int j = x + 1; j < row; j++) {
                height = Math.min(height, depth[j][0]);
                depth[j][1] = Math.max(depth[j][1], height);
            }
        }

        int result = 0;
        // 최대 깊이 - 빠져나간 물의 깊이
        for (int i = 0; i < row; i++) {
            result += depth[i][0] - depth[i][1];
        }
        System.out.println(result);
    }
}
