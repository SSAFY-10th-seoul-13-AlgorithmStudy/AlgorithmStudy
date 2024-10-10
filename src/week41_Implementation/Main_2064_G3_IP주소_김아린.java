import java.util.*;
import java.io.*;

public class Main {
    static int N;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        int[] network = new int[N];
        for (int i = 0; i < N; i++) {
            int tmp = 0;
            StringTokenizer st = new StringTokenizer(br.readLine(), ".");
            for (int j = 0; j < 4; j++) {
                int input = Integer.parseInt(st.nextToken());
                tmp <<= 8;
                tmp += input; 
            }
            network[i] = tmp;
        }
        
        int common = 0;
        //공통인자 구하기
        for (int i = 31; i >= 0; i--) { //앞에서부터 보기
            //전체 ip들을 한번에 확인
            int bit = 1 << i;
            boolean isDiff = false;
            
            int gijun = network[0] & bit;
            
            for (int j = 1; j < N; j++) {
                if(gijun != (network[j] & bit)) { //만약 다르면
                    isDiff = true;
                    break;   
                }
            }
            
            if(isDiff)
                break;
            else {
                //공통 인자에 추가
                common |= bit;
            }
        }
        
        //네트워크 주소 구하기
        int networkAddr = network[0] & common;
        
        System.out.println(convert(networkAddr));
        System.out.println(convert(common));
        
           
    }
    
    public static String convert(int ip) {
        
        StringBuilder tmp = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            //앞에서부터 추출
            int sub = (ip >> (8*i)) & 255;
            tmp.append(sub);
            
            if(i != 0)
                tmp.append(".");
        }
        
        return tmp.toString();
    }
}
