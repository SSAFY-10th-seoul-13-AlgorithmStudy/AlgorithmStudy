import java.io.*;
import java.util.*;

public class Main {
    static int X,Y,D,T;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        //1. 그냥 걷기
        //2. 일직선으로 점프 + 걷기
        //3. 점프 두번
        
        //피타고라스 정리
        double len = Math.sqrt(X * X + Y * Y);
		double walk, jumpWalk, jumpTwice; // 이동 시간
		
        //걷기
		walk = len;

		if (len >= D) { //남은 길이가 점프 길이보다 크면?
			int jump = (int)(len / D); //점프 횟수(>= 1)

            //점프 할만큼 하고 남은 거리 걷기  -점프->-걷기->
			jumpWalk = (T * jump) + (len - (D * jump));

            //점프 할만큼 하고 남은 거리 다시 점프 -점프-> / \
			jumpTwice = T * (jump + 1);
		}
		else { //남은 거리가 점프 거리보다 짧다면?

            //일직선 점프 후 다시 돌아오기 -점프-><-걷기-
			jumpWalk = T + (D - len);

            //점프 두번 / \
			jumpTwice = T * 2;
		}
        double ans = jumpWalk > jumpTwice ? jumpTwice : jumpWalk;
        ans = ans > walk ? walk : ans;
        System.out.println(ans);
        
        
    }
}
