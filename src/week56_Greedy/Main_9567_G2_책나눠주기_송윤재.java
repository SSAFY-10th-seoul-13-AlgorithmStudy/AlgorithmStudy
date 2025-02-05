import java.util.*;
import java.io.*;
public class Main {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N, M;
    static List<Student> students;
    static boolean check[];
    
    static class Student implements Comparable<Student>{
        int start, end;
        Student(int start, int end){
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(Student o){
            if(this.end == o.end){
                return Integer.compare(this.start, o.start);
            }
            return Integer.compare(this.end, o.end);
        }
    }
    static void init(){
        students = new ArrayList<>();
        check = new boolean[N + 1];
    }
    
    static void input() throws IOException{
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();
        for(int i = 0; i < M; i++){            
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            students.add(new Student(a, b));
        }
    }
    
    static void solve(){
        Collections.sort(students);
        int cnt = 0;
        for(Student cur : students){
            for(int i = cur.start; i <= cur.end; i++){
                if(!check[i]){
                    check[i] = true;
                    cnt++;
                    break;
                }
            }
        }
        sb.append(cnt).append("\n");
    }
    
    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++){
            input();
            solve();
        }
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
