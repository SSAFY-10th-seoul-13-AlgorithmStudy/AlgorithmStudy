package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1069_G3_집으로_강이규 {

    static int X, Y, D, T;

    public static void main(String[] args) throws IOException {
        solve();
//        while (true) {
//
//        }
    }

    private static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        // 대각선만 따지면 된다. 대각선 이동이 무조건 빠르다.
        double curDist = Math.sqrt(X*X + Y*Y);
        if (D <= T) {
            System.out.println(curDist);
            System.exit(0);
        }

        double curTime = 0;
        int maxTime = X + Y;

        int possibleDist = 2 * D; // 2D인에 드는 경우, 2T안에 무조건 갈 수 있다.

        while (curTime < maxTime) {
            if (curDist <= possibleDist) {
                double minTime = 2*T;
                if (curDist >= D) {
                    minTime = Math.min(minTime, T + (curDist - D));
                } else {
                    double time1 = curDist;
                    double time2 = T + (D - curDist);
                    double smaller = Math.min(time1, time2);
                    minTime = Math.min(minTime, smaller);
                }
                curTime += minTime;
                break;
            }
            // 못 가면, 일단 점프 한번 뛰는게 최적
            curTime += T;
            curDist = Math.abs(curDist - D);
        }
        System.out.println(Math.min(curTime, maxTime));
    }
}
