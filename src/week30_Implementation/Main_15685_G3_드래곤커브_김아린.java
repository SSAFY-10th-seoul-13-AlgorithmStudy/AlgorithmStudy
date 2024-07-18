import java.util.*;
import java.io.*;
// https://dublin-java.tistory.com/34
public class Main {
    private static final int RIGHT = 0;
    private static final int UP = 1;
    private static final int LEFT = 2;
    private static final int DOWN = 3;
    private static final int LENGTH = 101;
 
    private static boolean[][] map = new boolean[LENGTH][LENGTH];
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 커브의 개수
 
        StringTokenizer st = null;
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()); // 시작 방향
            int g = Integer.parseInt(st.nextToken()); // 세대
 
            draw(x, y, getDirections(d, g));
        }
 
        System.out.println(getNumberOfSquares());
    }
 
    public static List<Integer> getDirections(int d, int g) {
        List<Integer> directions = new ArrayList<>();
        directions.add(d);
 
        while (g-- > 0) {
            for (int i = directions.size() - 1; i >= 0; i--) {
                int direction = (directions.get(i) + 1) % 4;
                directions.add(direction);
            }
        }
        return directions;
    }
 
    public static void draw(int x, int y, List<Integer> directions) {
        map[x][y] = true;
        // 이전의 세대의 방향을 바탕으로 만들어짐
        // 0: (→) x++
        // 1: (↑) y--
        // 2: (←) x--
        // 3: (↓) y++
        // 안그려보면 진짜 모를듯
        for (int direction : directions) {
            switch (direction) {
                case RIGHT:
                    map[++x][y] = true;
                    break;
                case UP:
                    map[x][--y] = true;
                    break;
                case LEFT:
                    map[--x][y] = true;
                    break;
                case DOWN:
                    map[x][++y] = true;
                    break;
            }
        }
    }
 
    private static int getNumberOfSquares() {
        int count = 0;
 
        //크기가 1×1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 정사각형의 개수
        for (int x = 0; x < LENGTH-1; x++) {
            for (int y = 0; y < LENGTH-1; y++) {
                if (map[x][y] && map[x + 1][y] && map[x][y + 1] && map[x + 1][y + 1])
                    count++;
            }
        }
 
        return count;
    }
}
