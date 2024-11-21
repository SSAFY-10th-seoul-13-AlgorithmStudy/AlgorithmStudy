import java.io.*;
import java.util.*;

public class Main {
    static int N, M, T, cnt;
    static int[][] disc;
    static int[] discStart;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        cnt = N*M;
        
        disc = new int[N][M];
        discStart = new int[N];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            discStart[i] = 0;
            for (int j = 0; j < M; j++) {
                disc[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            
            for (int j = x; j <= N; j++) {
                if (j % x == 0) {
                    rotate(j-1, d, k);
                }
            }
            findRelative();
        }
        
        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                ans += disc[i][j];
            }
        }
        
        System.out.println(ans);
    }
    
    public static void rotate(int x, int d, int k) {
        //번호가 x번인 원판을 d(시계/반시계)방향으로 k칸 회전
        //시계방향 = 뒤에가 앞으로
        //반시계 = 앞에가 뒤로
        
        if(d == 0) { //시계
            //시작점 위치가 - i만큼 움직임
            discStart[x] = ((discStart[x] - k) + M) % M;
        } else { //반시계
            //시작점 위치가 + i만큼 움직임
            discStart[x] = (discStart[x] + k) % M;
        }
    }
    
    public static void findRelative() {
        Set<int[]> set = new HashSet<>();
        int sum = 0;
        for (int i = 0; i < N; i++) {
            int fsidx = discStart[i];
            
            //현재 원판 안에서 비교
            for (int j = 0; j < M; j++) {
                int idx = (fsidx + j) % M;
                sum += disc[i][idx];
                if(disc[i][idx] == 0) continue; //없어진거면 넘어가기
                
                //같은 원판 양 옆부터 점검
                int next = (idx + 1) % M;
                int prev = (idx - 1 + M) % M;
                if(disc[i][idx] == disc[i][next]) {
                    set.add(new int[]{i, idx});
                    set.add(new int[]{i, next});
                }
                if(disc[i][idx] == disc[i][prev]) {
                    set.add(new int[]{i, idx});
                    set.add(new int[]{i, prev});
                }
                
                //다른 원판 점검
                if(i + 1 == N) continue;
                int sidx = (discStart[i+1] + j) % M;
                if(disc[i+1][sidx] == disc[i][idx]) {
                    set.add(new int[]{i, idx});
                    set.add(new int[]{i+1, sidx});
                }
            }
            
        }
        
        if(set.isEmpty()) {
            findMean(sum);
        } else {
            for(int[] n : set) {
                if(disc[n[0]][n[1]] != 0)
                    cnt--;  
                disc[n[0]][n[1]] = 0;
            }
        }
    }
    
    public static void findMean(int sum) {
        double avg = (float)sum / (float)cnt;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(disc[i][j] == 0) continue;
                if((double) disc[i][j] < avg) {
                    disc[i][j] += 1;
                } else if ((double) disc[i][j] > avg) {
                    disc[i][j] -= 1;
                }
            }
        }
    }
}
