import java.util.*;
import java.io.*;

public class Main {   
    static int N, M, score;
    static int[][] map;
    static boolean[][] visited;
    static int[][] dir = {{1,0}, {0,1}, {-1, 0}, {0, -1}};
    
    public static void main(String[] args) throws Exception {
        // 문제보니까 당 떨어지네
        // -1 검은색
        // 0 무지개
        // 일반 블록은 M가지 색상 1~M
        // 8방 = 인접함
        // 블록 그룹 = 일반 블록 적어도 하나, 모두 같은 색, 검은색 X, 무지개는 노상관 (>=2)
        // 기준 블록 = 일반 블록 중 0,0에 가까운 블록
        
        // 크기가 가장 큰 블록 그룹(<무지개<기준블록 행 < 기준블록 열)의 모든 블록 제거(블록수^2 획득)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        score = 0;
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        while(true) {
            
            visited = new boolean[N][N]; //계속 새로 찾아야함
            
            BlockGroup bg = null;
            
            //블록그룹 찾기
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(visited[i][j] || map[i][j] <= 0) //방문했거나 일반 블럭이 아니면 X
                        continue;
                    BlockGroup g = findGroup(i, j);
                    if (g != null) {
                            if (bg == null || g.compareTo(bg) > 0) {
                                bg = g;
                            }
                        }
                }
            }
            
            //종료조건 = 블록 그룹이 없을때
            if(bg == null)
                break;
            
            // 블록 그룹 제거 및 점수 계산
            score += bg.size * bg.size;
            removeGroup(bg);
            
             // 중력 적용
            gravity();

            // 반시계 방향 회전
            rotateGrid();

            // 중력 다시 적용
            gravity();
            
        }
        System.out.println(score);
    }
    
    static BlockGroup findGroup(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        List<int[]> blocks = new ArrayList<>();
        List<int[]> rainbows = new ArrayList<>();
        boolean[][] tmpVisited = new boolean[N][N];
        
        int color = map[x][y];
        q.add(new int[]{x, y});
        tmpVisited[x][y] = true;
        blocks.add(new int[]{x, y});
        
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int cx = cur[0] + dir[i][0];
                int cy = cur[1] + dir[i][1];
                if(cx >= N || cx < 0 || cy >= N || cy < 0) continue;
                if(tmpVisited[cx][cy]) continue;
                if(map[cx][cy] != color && map[cx][cy] != 0) continue;
                q.add(new int[]{cx, cy});
                tmpVisited[cx][cy] = true;
                blocks.add(new int[]{cx, cy});
                if(map[cx][cy] == 0)
                    rainbows.add(new int[]{cx, cy});
            }
        }
        
        if(blocks.size() < 2)  
            return null;
        
        for (int[] block : blocks) {
            if(map[block[0]][block[1]] != 0)
                visited[block[0]][block[1]] = true;
        }
        
        return new BlockGroup(blocks, rainbows);
    }
    
     static void removeGroup(BlockGroup g) {
        for (int[] block : g.blocks) {
            map[block[0]][block[1]] = -2;
        }
    }
    
    static void gravity() {
        for (int j = 0; j < N; j++) {
            int bottom = N - 1;
            for (int i = N - 1; i >= 0; i--) {
                if (map[i][j] == -1) {
                    bottom = i - 1;
                } else if (map[i][j] >= 0) {
                    int temp = map[i][j];
                    map[i][j] = -2;
                    map[bottom--][j] = temp;
                }
            }
        }
    }
    static void rotateGrid() {
        int[][] newGrid = new int[N][N];
        for (int i = 0; i < N; i++) {
              for (int j = 0; j < N; j++) {
                newGrid[N - 1 - j][i] = map[i][j];
            }
        }
        map= newGrid;
    }
    
 static class BlockGroup implements Comparable<BlockGroup> {
    List<int[]> blocks;
    List<int[]> rainbowBlocks;
    int size;
    int rainbowSize;
    int giX;
    int giY;

    public BlockGroup(List<int[]> blocks, List<int[]> rainbowBlocks) {
        this.blocks = blocks;
        this.rainbowBlocks = rainbowBlocks;
        this.size = blocks.size();
        this.rainbowSize = rainbowBlocks.size();

        this.giX = Integer.MAX_VALUE;
        this.giY= Integer.MAX_VALUE;

        for (int[] block : blocks) {
            if (map[block[0]][block[1]] > 0) {
                if (block[0] < giX || (block[0] == giX && block[1] < giY)) {
                    giX = block[0];
                    giY = block[1];
                }
            }
        }
    }

    @Override
    public int compareTo(BlockGroup o) {
        if (this.size != o.size) {
            return Integer.compare(o.size, this.size);
        }
        if (this.rainbowSize != o.rainbowSize) {
            return Integer.compare(o.rainbowSize, this.rainbowSize);
        }
        if (this.giX != o.giX) {
            return Integer.compare(o.giX, this.giX);
        }
        return Integer.compare(o.giY, this.giY);
    }
}
}
