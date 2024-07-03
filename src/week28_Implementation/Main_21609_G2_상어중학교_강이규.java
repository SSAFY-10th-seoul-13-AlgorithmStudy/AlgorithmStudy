package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_21609_G2_상어중학교_강이규 {

   static int N, M, res = 0;
   static int[][] map;
   static boolean[][] visited;
   static int[] dr = {-1, 0, 1, 0};
   static int[] dc = {0, -1, 0, 1};

   static class Group implements Comparable<Group> {
       int size, rbCnt, headR, headC;
       List<Pair> blocks;

       Group(Pair head) {
           this.size = 1;
           this.rbCnt = 0;
           this.headR = head.r;
           this.headC = head.c;
           this.blocks = new ArrayList<>();
           this.blocks.add(head);
       }

       @Override
       public int compareTo(Group o) {
           if (this.size == o.size) {
               if (this.rbCnt == o.rbCnt) {
                   if (this.headR == o.headR) {
                       return Integer.compare(o.headC, this.headC);
                   } else {
                       return Integer.compare(o.headR, this.headR);
                   }
               } else {
                   return Integer.compare(o.rbCnt, this.rbCnt);
               }
           } else {
               return Integer.compare(o.size, this.size);
           }
       }
   }

   static class Pair {
       int r, c;
       Pair(int r, int c) {
           this.r = r;
           this.c = c;
       }
   }

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
       while (true) {
           Group maxGroup = findMaxGroup();
           if (maxGroup == null)
               break;
           // 점수 업데이트
           res += maxGroup.size * maxGroup.size;
           for (Pair p : maxGroup.blocks) {
               map[p.r][p.c] = -2;
           }
           gravity();
           rotate();
           gravity();
       }
        System.out.println(res);
    }

    private static void gravity() {
       for (int i = N-2; i >= 0; i--) {
           for (int j = 0; j < N; j++) {
               if (map[i][j] < 0) continue; // 없거나, 검은 블록일 때
               // 이동
               int newR = i + 1;
               while (newR < N && map[newR][j] == -2) {
                   newR++;
               }
               if (newR != i + 1) { // 이동한 경우
                   map[newR-1][j] = map[i][j];
                   map[i][j] = -2;
               }
           }
       }
    }

    private static void rotate() {
       int[][] newMap = new int[N][N];
       for (int i = 0; i < N; i++) {
           for (int j = 0; j < N; j++) {
               newMap[i][j] = map[j][N-1-i];
           }
       }
       map = newMap;
    }

    private static Group findMaxGroup() {
       PriorityQueue<Group> groups = new PriorityQueue<>();
       visited = new boolean[N][N];
       for (int i = 0; i < N; i++) {
           for (int j = 0; j < N; j++) {
               if (!canBeHead(i, j)) continue;
               bfs(new Pair(i, j), groups);
           }
       }
       return !groups.isEmpty() ? groups.poll() : null;
    }

    private static void bfs(Pair s, PriorityQueue<Group> groups) {
       Group res = new Group(s);
       Queue<Pair> q = new ArrayDeque<>();
       List<Pair> rainbows = new ArrayList<>();

       q.offer(s);
       visited[s.r][s.c] = true;

       int groupColor = map[s.r][s.c];
       while (!q.isEmpty()) {
           Pair cur = q.poll();

           for (int d = 0; d < 4; d++) {
               int nr = cur.r + dr[d];
               int nc = cur.c + dc[d];
               if (!inRange(nr, nc)) continue;
               if (!canBeAdded(nr, nc, groupColor)) continue;
               // add
               visited[nr][nc] = true;
               Pair adj = new Pair(nr, nc);
               if (map[nr][nc] == 0) {
                   rainbows.add(adj);
                   res.rbCnt++;
               }
               res.blocks.add(adj);
               res.size++;
               q.offer(adj);
           }
       }
       if (res.size < 2) {
           for (Pair p : res.blocks) {
               visited[p.r][p.c] = false;
           }
           return;
       }
       // 무지개 블록 방문 복구
       for (Pair p : rainbows) {
           visited[p.r][p.c] = false;
       }
       groups.offer(res);
    }

    private static boolean inRange(int r, int c) {
       return (0 <= r && r < N) && (0 <= c && c < N);
    }

    private static boolean canBeHead(int r, int c) {
       return !visited[r][c] && 0 < map[r][c];
    }

    private static boolean canBeAdded(int r, int c, int groupColor) {
       return !visited[r][c] && 0 <= map[r][c]
               && (map[r][c] == 0 || map[r][c] == groupColor);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
