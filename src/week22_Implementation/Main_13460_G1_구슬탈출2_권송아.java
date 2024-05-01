package boj;

import java.util.*;
import java.io.*;

public class boj13460 {

    static char[][] map;
    static int R, C;

    static class Point{
        int r, c;

        Point(int r, int c){
            this.r = r;
            this.c = c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        @Override
        public boolean equals(Object obj) {
            Point p = (Point) obj;
            return p.r == r && p.c == c;
        }
    }

    static Point red, blue, hole;

    static final int[] dr = {0, 0, 1, -1};
    static final int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for(int r=0; r<R; ++r){
            String str = br.readLine();
            for(int c=0; c<C; ++c){
                char curr = str.charAt(c);

                map[r][c] = curr;
                if(curr == 'O'){
                    hole = new Point(r,c);
                    continue;
                }

                if(curr == 'R'){
                    map[r][c] = '.';
                    red = new Point(r,c);
                    continue;
                }

                if(curr == 'B'){
                    map[r][c] = '.';
                    blue = new Point(r,c);
                    continue;
                }
            }
        }

        go();
    }


    static void go(){

        Queue<Point> rq = new LinkedList<>();
        Queue<Point> bq = new LinkedList<>();

        boolean[][][][] visited = new boolean[R][C][R][C];
        visited[red.r][red.c][blue.r][blue.c] = true;
        rq.add(red);
        bq.add(blue);

        for(int t = 0; t < 11; ++t){
            int size = rq.size();
            while(size-->0){
                // 상하좌우로 공 움직이기
                // 목표) 빨간 공 빼내기
                // 파란공이 구멍에 빠지면 안됨
                Point currRed = rq.poll();
                Point currBlue = bq.poll();

                if(currBlue.equals(hole)){
                    continue;
                }

                if(currRed.equals(hole)){
                    System.out.println(t);
                    return;
                }

                if(t==10){
                    continue;
                }

                ////////////////////////////////////Next 계산//////////////////////////////
                for(int d=0; d<4; ++d){

                    Point start = currRed;
                    Point end = currBlue;
                    boolean redStart = true;

                    // 오른쪽
                    if(d==0){
                        if(start.r == end.r){
                           if(start.c < end.c){
                               start = currBlue;
                               end = currRed;
                               redStart = false;
                           }
                        }
                    }
                    // 왼쪽
                    else if(d==1){
                        if(start.r == end.r){
                            if(start.c > end.c){
                                start = currBlue;
                                end = currRed;
                                redStart = false;
                            }
                        }
                    }
                    // 아랫쪽
                    else if(d==2){
                        if(start.c == end.c){
                            if(start.r < end.r){
                                start = currBlue;
                                end = currRed;
                                redStart = false;
                            }
                        }
                    }
                    // 윗쪽
                    else{
                        if(start.c == end.c){
                            if(start.r > end.r){
                                start = currBlue;
                                end = currRed;
                                redStart = false;
                            }
                        }
                    }


                    int nr = start.r;
                    int nc = start.c;
                    do{
                        nr += dr[d];
                        nc += dc[d];
                    } while(nr > 0 && nr < R-1 && nc > 0 && nc < C-1 && map[nr][nc] == '.');
                    int nsr = nr;
                    int nsc = nc;
                    if(map[nr][nc]!='O'){
                        nsr -= dr[d];
                        nsc -= dc[d];
                    }

                    nr = end.r;
                    nc = end.c;
                    do{
                        nr += dr[d];
                        nc += dc[d];
                    } while(nr > 0 && nr < R-1 && nc > 0 && nc < C-1 && map[nr][nc] == '.');
                    int ner = nr;
                    int nec = nc;
                    if(map[nr][nc]!='O'){
                        ner -= dr[d];
                        nec -= dc[d];
                    }

                    // 두 구슬이 같은 위치에 있다
                    if(nsr==ner && nsc==nec){
                        if(map[nsr][nsc] != 'O'){ // 구멍이 아니다
                            ner -= dr[d];
                            nec -= dc[d];
                            if(redStart){
                                if(!visited[nsr][nsc][ner][nec]){
                                    rq.add(new Point(nsr, nsc));
                                    bq.add(new Point(ner, nec));
                                    visited[nsr][nsc][ner][nec] = true;
                                }
                                continue;
                            }

                            if(!visited[ner][nec][nsr][nsc]){
                                bq.add(new Point(nsr, nsc));
                                rq.add(new Point(ner, nec));
                                visited[ner][nec][nsr][nsc] = true;
                            }
                            continue;
                        }
                        continue;
                    }

                    // 두 구슬이 다른 위치에 있다
                    if(redStart){
                        if(!visited[nsr][nsc][ner][nec]){
                            rq.add(new Point(nsr, nsc));
                            bq.add(new Point(ner, nec));
                            visited[nsr][nsc][ner][nec] = true;
                        }
                        continue;
                    }

                    if(!visited[ner][nec][nsr][nsc]){
                        bq.add(new Point(nsr, nsc));
                        rq.add(new Point(ner, nec));
                        visited[ner][nec][nsr][nsc] = true;
                    }
                    continue;
                }
            }
        }

        System.out.println(-1);
    }
}
