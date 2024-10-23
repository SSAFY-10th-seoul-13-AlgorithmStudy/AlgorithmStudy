import java.util.*;
import java.io.*;

public class Main {
    static class Block implements Comparator<Block> {
        int x;
        int y;
        
        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public int compare(Block b1, Block b2) {
            // 행 x 비교 (작은 값이 우선순위 높음)
            if (b1.x != b2.x)
                return Integer.compare(b2.x, b1.x);
            
            // 행 x가 같다면 열 y 비교 (작은 값이 우선순위 높음)
            return Integer.compare(b2.y, b1.y);
        }
    }
    static class BlockGroup implements Comparable<BlockGroup> {
        List<Block> group;
        int rainbowCnt;
        int size;
        Block standard;
        
        public BlockGroup(List<Block> group, int rainbowCnt, int size, Block standard) {
            this.group = group;
            this.rainbowCnt = rainbowCnt;
            this.size = size;
            this.standard = standard;
        }
               
        @Override
        public int compareTo(BlockGroup bg) {
            if(this.size != bg.size)
                return Integer.compare(bg.size, this.size);
                
            // 크기가 같다면 rainbowCnt를 비교 (큰 값이 우선순위 높음)
            if (this.rainbowCnt != bg.rainbowCnt) {
                return Integer.compare(bg.rainbowCnt, this.rainbowCnt);
            }
            // rainbowCnt가 같다면 sr을 비교 (큰 값이 우선순위 높음)
            if (this.standard.x != bg.standard.x) {
                return Integer.compare(bg.standard.x, this.standard.x);
            }
            // sr도 같다면 sc을 비교 (큰 값이 우선순위 높음)
            return Integer.compare(bg.standard.y, this.standard.y);
        }
    }
    
    static int N, M, score;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1,0,0,-1};
    static int[] dy = {0,1,-1,0};
    static PriorityQueue<BlockGroup> pq;
    static StringTokenizer st;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        score = 0;
        map = new int[N][N];
                
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        while(true) {
            pq = new PriorityQueue<>();
            visited = new boolean[N][N];
            
            //크기가 가장 큰 블록 찾기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(map[i][j] <= 0) continue;
                    if(map[i][j] > 0 && visited[i][j]) continue;
                    findBiggest(i, j);
                }
            }
                        
            if(pq.isEmpty())
                break;
            
            BlockGroup willRemove = pq.poll();
            score += Math.pow(willRemove.size, 2);
            
            //가장 큰 블록 그룹 삭제
            removeBiggest(willRemove);
            
            //중력 적용
            gravity();
            
            rotate();
            
            gravity();
        }
        
        System.out.println(score);
    }
    
    public static void rotate() {
        int[][] copy = new int[N][N];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copy[N-1-j][i] = map[i][j];
            }
        }
        map = copy;
    }
    
    public static void gravity() {
        for (int c = 0; c < N; c++) {
            int tam = N - 1; // 아래에서부터 탐색
            
            // 아래에서 위로 탐색
            for (int r = N - 1; r >= 0; r--) {
                if(map[r][c] == -1) // 벽을 만나면
                    tam = r - 1;
                if (map[r][c] != -2 && map[r][c] != -1) { // 블록이 있으면
                    map[tam][c] = map[r][c]; // 빈 공간으로 블록 이동
                    if (tam != r) {
                        map[r][c] = -2; // 원래 위치를 빈 칸으로
                    }
                    tam--; // 다음 빈 칸 위로 이동
                }
            }
        }
    }
    
    public static void removeBiggest(BlockGroup b) {
        for (Block now : b.group) {
            map[now.x][now.y] = -2;
        }
    }
    
    public static void findBiggest(int startX, int startY) {
        Queue<Block> q = new ArrayDeque<>();
        boolean[][] isvisit = new boolean[N][N];
        List<Block> list = new ArrayList<>(); //블록 리스트
        Block standard = new Block(startX, startY); // 기준 블록
        int rainbowCnt = 0; //레인보우 블록 개수
        int color = map[startX][startY]; //블록 색깔
        
        q.add(new Block(startX, startY));
        isvisit[startX][startY] = true;
        visited[startX][startY] = true;
        list.add(q.peek());
        
        while(!q.isEmpty()) {
            Block now = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int mx = now.x + dx[i];
                int my = now.y + dy[i];
                
                if(mx < 0 || my < 0 || mx >= N || my >= N) continue;
                if(isvisit[mx][my]) continue;
                if(map[mx][my] == -1) continue;
                
                Block b = new Block(mx, my);
                if(map[mx][my] == 0) { //무지개 블록이면 방문처리+q삽입+cnt증가
                    q.add(b);
                    rainbowCnt++;
                    list.add(b);
                    isvisit[mx][my] = true;
                    continue;
                }
                if(map[mx][my] != color) continue;
                
                q.add(b);
                isvisit[mx][my] = true;
                visited[mx][my] = true; //일반 블록일때만 방문처리하여 이후 방문하지 않도록 함.
                list.add(b);
                standard = b.compare(standard, b) < 0 ? b : standard ;
            }
        }
        
        if(list.size() < 2)
            return;
        else {
            pq.add(new BlockGroup(list, rainbowCnt, list.size(), standard));
        }
    }
}
