package week03;
//https://www.acmicpc.net/problem/13334
//https://blog.naver.com/occidere/221085858307

import java.io.*;
import java.util.*;

class Work implements Comparable<Work>{
    int start, end;

    public Work(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Work o) {
        if(end - o.end == 0)
            return start - o.start;
        else
            return end - o.end;
    }
}

public class Main_13334_G2_철로_김아린 {

    public static void main(String[] args) throws Exception {
        //흠...일단 각 라인을 정렬하는 것이 첫번째
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(in.readLine());

        StringTokenizer st = null;

        ArrayList<Work> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(in.readLine());
            int tmpA = Integer.parseInt(st.nextToken());
            int tmpB = Integer.parseInt(st.nextToken());

            if(tmpA > tmpB)
                list.add(new Work(tmpB, tmpA));
            else
                list.add(new Work(tmpA, tmpB));
        }

        int D = Integer.parseInt(in.readLine());

        Collections.sort(list);

//        //1. 사이즈를 계속 재줘야함 근데 -1억~1억..
//        //시간복잡도 N^2니까 역시 될 리가 없다~~~~~
//        for (int i = 0; i < N; i++) {
//            int cnt = 0;
//            //항상 매 원소의 start가 시작 지점이라고 쳐보자
//            metro = list.get(i).start;
//            for (int j = 0; j < N; j++) {
//                int tmpS = list.get(j).start;
//                int tmpE = list.get(j).end;
//                if(tmpS >= metro && tmpE <= metro+D && (tmpE - tmpS) <= D) {
////                    System.out.println(tmpS + " " + tmpE);
//                    cnt++;
//                }
//            }
//            ans = Math.max(cnt, ans);
//        }

        int max = 0;
        //2. 그럼 큐에 넣어서 해보자
        //n개의 선분들을 오른쪽 끝 점을 기준으로 오름차순 정렬을 한다.
        //이후 각 i번째 선분의 오른쪽 끝 점을, 철로 D의 오른쪽 끝 점으로 잡고 길이 D 내부에 포함되는 선분들의 최대 개수를 계산
//        ArrayDeque<Integer> q = new ArrayDeque<>();
        PriorityQueue<Integer> q = new PriorityQueue<>();
        //PriorityQueue(최소 힙)을 사용하여 O(nlogn)으로 접근
        for (int i = 0; i < N; i++) {
            //시작점을 넣을때 간격이 D보다 크면 넣지 말아야 하는구나
            if(list.get(i).end - list.get(i).start > D)
                continue;

            q.offer(list.get(i).start);

            while(!q.isEmpty() && q.peek() < list.get(i).end - D)
                q.poll();

            max = Math.max(max, q.size());

        }

        System.out.println(max);
    }
}