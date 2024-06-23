package week28_Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1222_G2_홍준프로그래밍대회_김태수 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] schools = new int[2_000_001];

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        //학교의 사이즈들을 저장
        for(int i = 0 ; i <N ;i++){
            int idx = Integer.parseInt(st.nextToken());
            schools[idx]++;
        }

        long max = 0;

        // 배수에 해당되는 학교가 몇개인지 확인하면 되지 않을까?
        // 2배수 몇개, 3배수 몇개,.. 그리고 그 갯수 * 배수 하면 본선 인원수
        // 갯수가 2 이상인 것들중 최댓값을 출력
        
        for(int i = 1 ; i <=2_000_000;i++ ){
            // 2_000_000 ^ 2 이니까 int를 넘어섬..
            long cnt = 0;
            for(int j = i ; j <=2_000_000; j += i){
                cnt += schools[j];
            }
            if(cnt > 1){
                max = Math.max(max,cnt * i);
            }
        }

        System.out.println(max);
        
    }
}
