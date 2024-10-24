import java.util.*;
import java.io.*;

public class Main {
    
    static class Castle {
        int x;
        int y;
        
        public Castle(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int N, M, P;
    static int[] S, ans;
    static int[][] map;
    static int[][] dir = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    static ArrayList<Queue<Castle>> qList;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        
        S = new int[P+1];
        ans = new int[P+1];
        qList = new ArrayList<>();
        qList.add(new LinkedList<>());
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= P; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            S[i] = tmp > N*M - 1 ? N*M - 1 : tmp;
            qList.add(new LinkedList<>());
        }
        
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            char[] tmmp = st.nextToken().toCharArray();
            for (int j = 0; j < M; j++) {
                if(tmmp[j] == '.')
                    map[i][j] = 0;
                else if (tmmp[j] == '#')
                    map[i][j] = -1;
                else {
                    map[i][j] = tmmp[j] - '0';
                    qList.get(map[i][j]).add(new Castle(i, j));
                    ans[map[i][j]]++;
                }
            }
        }
        
        while(true) {
            boolean isMoving = false;
            
            for (int i = 1; i <= P; i++) {
                isMoving |= bfs(qList.get(i), i);
            }
            
            // System.out.println(isMoving);
            
            if(!isMoving)
                break;
        }
        
        // System.out.println(map[2][2]);
        for (int i = 1; i <= P; i++)
            System.out.print(ans[i] + " ");
        
    }
    
    static boolean bfs(Queue<Castle> q, int index) {
        int dist = S[index]; //움직여야할 거리
        int cnt = 0; //점령한 성 개수
        
        while(!q.isEmpty()) {
            // System.out.println(index + "의 큐 사이즈는 " + q.size());
            int size = q.size();
            for (int a = 0; a < size; a++) {
                Castle now = q.poll();   
                
                for (int i = 0; i < 4; i++) {
                    int mx = dir[i][0] + now.x;
                    int my = dir[i][1] + now.y;
                
                    if(mx < 0 || my < 0 || mx >= N || my >= M) continue;
                    if(map[mx][my] == 0) {
                        q.add(new Castle(mx, my));
                        map[mx][my] = index;
                        // System.out.println(mx + " " + my + " => " + index);
                        cnt++;
                    }
                }
            }
            dist--;
            
            if(dist <= 0)
                break;
        }
        
        ans[index] += cnt;
        
        return cnt > 0;
    }
}
