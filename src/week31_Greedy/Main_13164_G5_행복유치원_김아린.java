import java.util.*;
import java.io.*;

public class Main {
    static int N, K;
    static ArrayList<Integer> list, chai;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        //만약 조에 한명밖에 없다면? 비용 0
        // 1 2 3 4 5 , 3
        // 1 2 / 3 4 / 5 뭐 대충 이런식으로 나누면 됨
        // 즉.. 일단 1 / 2 / 3 / 4 / 5 이렇게 나눈 후에
        // 가장.. 차이가 없는 놈들끼리 뭉치기?
        // 1 2 / 3 / 4 / 5 근데 이렇게 막 뭉쳐도 되나?
        // 뭉치는거 말고.. 그럼 나누기?
        // 정렬된 상태로 주어지니까, 다 뭉쳐있다 치고, 차이가 큰 놈부터 떨구기
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        list = new ArrayList<Integer>();
        chai = new ArrayList<Integer>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        
        for (int i = 0; i < N-1; i++) {
            chai.add(list.get(i+1)-list.get(i));
        }
        Collections.sort(chai);
        
        System.out.println(divide());
        
    }
    
    static int divide() {
        int sum = 0;
        //30만개를 돌면서 자르기? => N^2
        // 자르는 순간 상관없게 됨 + 2개가 생김
        // 그니까 3개를 만들려면 2번 잘라야한다.. 2개만드려면 1번만 자르면 됨
        // 앞에것만 더하면 된다!!!!!
        for (int i = 0; i <= N - 1 - K; i++) {
            sum += chai.get(i);
        }
        
        return sum;
        
    }
}
