import java.util.*;
import java.io.*;

public class Main {
    static int N, A, B;
    public static void main(String[] args) throws Exception {
        //10만.. 일단 채우기?
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        
        if(A+B > N+1) {
            System.out.print(-1);
            return;
        }
        
        //왼쪽 채우고 오른쪽 채운 후 겹치는 내용 삭제?
        // LinkedList<Integer> list = new LinkedList<>();
        Deque<Integer> q = new ArrayDeque<>();
        
        if(A > B) {
            //왼쪽 채우기
            for (int i = 1; i <= A; i++) {
                q.add(i);
            }
        
            //오른쪽 채우기
            if(B != 1) {
                int tmp = N - q.size();
                tmp = tmp > B-1 ? B-1 : tmp;
                
                for (int i = tmp; i >= 1; i--) {
                    q.addLast(i);
                }
            }
        } else {
            //오른쪽 채우기
            for (int i = B; i >= 1; i--) {
                q.add(i);
            }
        
            //왼쪽 채우기
            if(A != 1) {
                // A--;
                
                // list.addFirst(1);
                int tmp = N - q.size();
                tmp = tmp > A-1 ? A-1 : tmp;
                
                for (int i = tmp; i >= 1; i--) {
                    q.addFirst(i);
                }
            }
        }

        int size = q.size();
        // System.out.println(size);
        for (int i = 0; i < size; i++) {            
            System.out.print(q.poll() + " ");   
            
            if(i == 0 && N != size) {
                for (int k = 0; k < N-size; k++)
                    System.out.print("1 ");
            }
        }
    }
}
