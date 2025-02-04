import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static StringTokenizer st;
    static long tree[];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new long[4000001];
        
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            if(t == 0) {
                int min = Math.min(a, b);
                int max = Math.max(a, b);
                
                sb.append(sum(1, N, 1, min, max) + "\n");
            } else {
                update(1, N, 1, a, b);
            }
        }
        System.out.print(sb.toString());
    }
    
    //start : 트리 구간 시작 인덱스
    //end : 트리 구간 끝 인덱스
    //node : 현재 트리 노드 번호
    //left : 구하고자 하는 구간의 시작
    //right : 구하고자 하는 구간의 끝
    public static long sum(int start, int end, int node, int left, int right) {
        //현재 구간과 찾고자 하는 구간이 겹치지 않으면 상관 무
        if(left > end || right < start) return 0;
        
        //현재 구간이 찾고자 하는 구간에 완전 포함되면 현재 노드값 반환
        if (left <= start && end <= right) return tree[node];
        
        int mid = (start + end) / 2;
        
        //왼쪽과 오른쪽 자식의 합을 구해 반환
        return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2 + 1, left, right);
    }
    
    //val : 업데이트할 값
    //idx : 업데이트할 인덱스
    public static long update(int start, int end, int node, int idx, int val) {
        //만약 인덱스가 현재 구간에 속해있지 않다면 현재 노드 고대로 반환
        if(idx < start || idx > end) return tree[node];
        
        //리프 노드인 경우 특정 인덱스에 도달한 것이니 해당 노드의 값을 val로 업뎃
        if(start == end) return tree[node] = val;
        
        int mid = (start + end) / 2;
        
        //왼쪽 오른쪽 자식 재귀 + 현재 노드 갱신
        return tree[node] = update(start, mid, node*2, idx, val) + update(mid + 1, end, node * 2 + 1, idx, val);
    }
}
