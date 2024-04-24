import java.util.*;
import java.io.*;

public class Main {
    
    static class Jew {
        int m;
        int v;

        Jew(int m, int v){
            this.m = m;
            this.v = v;
        }
    }
    
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        // 현재 가방 크기 이하 보석들 중 비싼 것만 담기
        // 필요한 PQ
        // 보석 : 보석 무게 오름차순 / 가격 내림차순 순으로 정렬
        // 가방 : 가방 무게 오름차순 
        // 훔칠 보석 : 보석가격 내림차순 (그니까 비싼 순서)
        
        //이런식으로 다양하게 Comparator가 들어가야 한다면 람다식으로 표현하는게 편함
        PriorityQueue<Jew> jew = new PriorityQueue<>(new Comparator<Jew>() {
            @Override
            public int compare(Jew o1, Jew o2) {
                if(o1.m == o2.m) return o2.v - o1.v;
                else return o1.m - o2.m;
            }
        });
        
        int tmp_m, tmp_v;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            tmp_m = Integer.parseInt(st.nextToken());
            tmp_v = Integer.parseInt(st.nextToken());
            jew.add(new Jew(tmp_m,tmp_v));
        }
        
        PriorityQueue<Integer> bag = new PriorityQueue<>();
        
        for(int i = 0; i < K; i++){
            bag.add(Integer.parseInt(br.readLine()));
        }
        
        PriorityQueue<Jew> stole = new PriorityQueue<>(new Comparator<Jew>() {
            @Override
            public int compare(Jew o1, Jew o2) {
                return o2.v - o1.v;
            }
        });

        long ans = 0;
        while(!bag.isEmpty()){
            //일단 가방에 뭘 넣어야 할까??
            int b = bag.poll();

            //현재 가방에 넣을 수 있는 거 다 넣기
            while(!jew.isEmpty() && b >= jew.peek().m){
                stole.add(jew.poll());
            }

            //정렬된 것에 따라 가장 가치가 높은 보석이 맨 앞임
            
            if(!stole.isEmpty()){
                ans += stole.poll().v;
            }
        }
        System.out.println(ans);
    }
}
