package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj4195 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int F, curr;
    static Map<String, Integer> nameToIdx;
    static int[] parents;
    static int[] childCnts;


    public static void main(String[] args) throws Exception{

        int T = Integer.parseInt(br.readLine());
        while(T-->0){
            init();
            go();
        }
        System.out.print(sb.toString());
    }

    static void go() throws Exception{

        while(F-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());

            String c1 = st.nextToken();
            String c2 = st.nextToken();

            int idx1 = getIdx(c1);
            int idx2 = getIdx(c2);

            int p1 = find(idx1);
            int p2 = find(idx2);

            // 부모가 같은 경우, 이미 같은 네트워크
            if(p1 == p2){
                sb.append(childCnts[p1]).append("\n");
                continue;
            }

            // union: 번호가 작은 쪽이 나오게 합치기
            if(p1 < p2){
                parents[p2] = p1;
                childCnts[p1] += childCnts[p2];
                sb.append(childCnts[p1]).append("\n");
                continue;
            }

            parents[p1] = p2;
            childCnts[p2] += childCnts[p1];
            sb.append(childCnts[p2]).append("\n");
        }


    }

    static int getIdx(String name){
        if(nameToIdx.containsKey(name)){
            int idx = nameToIdx.get(name);
            return idx;
        }

        int idx = curr;
        nameToIdx.put(name, curr++);
        return idx;
    }

    static int find(int curr){
        if(parents[curr]==curr){
            return curr;
        }
        return parents[curr] = find(parents[curr]);
    }

    static void init() throws Exception{
        curr = 0;
        nameToIdx = new HashMap<>();

        F = Integer.parseInt(br.readLine());
        parents = new int[F*2];
        childCnts = new int[F*2];

        Arrays.fill(childCnts, 1);
        int max = F*2;
        for(int i=0; i<F*2; ++i){
            parents[i] = i;
        }
    }
}
