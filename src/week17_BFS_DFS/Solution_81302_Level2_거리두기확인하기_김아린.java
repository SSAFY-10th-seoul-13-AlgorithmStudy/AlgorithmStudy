```java
import java.util.LinkedList;
import java.util.Queue;

class Position {
    int x, y, dist;

    public Position(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}

class Solution {
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for(int i = 0; i < 5; i++) {
            answer[i] = isKeepDistance(places[i]) ? 1 : 0;
        }

        return answer;
    }

    public boolean isKeepDistance(String[] place) {
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                // P(사람)이 있는 위치에서 BFS 탐색
                if(place[i].charAt(j) == 'P') {
                    if(!bfs(place, i, j)) return false;
                }
            }
        }
        return true;
    }

    public boolean bfs(String[] place, int x, int y) {
        boolean[][] visited = new boolean[5][5];
        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(x, y, 0));
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            Position current = queue.poll();

            // 시작 위치가 아니면서 다른 P를 만난 경우 거리두기를 안 지킴
            if(current.dist != 0 && place[current.x].charAt(current.y) == 'P') return false;
            if(current.dist == 2) continue;

            for(int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if(nx >= 0 && ny >= 0 && nx < 5 && ny < 5 && !visited[nx][ny] && place[nx].charAt(ny) != 'X') {
                    visited[nx][ny] = true;
                    queue.offer(new Position(nx, ny, current.dist + 1));
                }
            }
        }
        return true;
    }
}
```
