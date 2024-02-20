import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_21943_G2_연산최대로_김희연 {
    static int n, p, q;
    static int[] arr, opr;
    static boolean[] visit;
    static int max = Integer.MIN_VALUE;
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        opr = new int[n];
        visit = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        p = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        dfs(0, 0, 0);

        System.out.println(max);
    }

    static void dfs(int depth, int a, int b){
        if(a <= p && b <= q){
            if(depth == n){
                max = Math.max(max, calc());
                return;
            }
        }

        for(int i=0; i<n; i++){
            if(!visit[i]){
                visit[i] = true;
                list.add(arr[i]);
                if(depth == 0)
                    dfs(depth+1, a, b);
                else{
                    opr[depth] = 1;
                    dfs(depth+1, a+1, b);
                    opr[depth] = 2;
                    dfs(depth+1, a, b+1);
                }
                list.remove(depth);
                visit[i] = false;
            }
        }
    }

    static int calc(){
        List<Integer> tempList = new ArrayList<>();
        int temp = list.get(0);
        for(int i=1; i<n; i++){
            if(opr[i] == 1)
                temp += list.get(i);
            else{
                tempList.add(temp);
                temp = list.get(i);
            }
        }
        tempList.add(temp);
        temp = 1;
        for (Integer i : tempList)
            temp *= i;
        return temp;
    }
}
