package week65_Greedy;
import java.util.*;
import java.io.*;
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int N;
    static Alphabet alphabets[];
    static String nums[];
    static Set<Character> nonZero;
    
    static class Alphabet implements Comparable<Alphabet>{
        char name;
        int num;
        long sum;
        
        Alphabet(char name){
            this.name = name;
            this.sum = 0;
            this.num = 0;
        }
        
        @Override
        public int compareTo(Alphabet o){
            return Long.compare(this.sum, o.sum);
        }
    }
    
    static void init(){
        nums = new String[N];
        alphabets = new Alphabet[10];
        for(int i = 0; i < 10; i++){
            alphabets[i] = new Alphabet((char)('A' + i));
        }
        nonZero = new HashSet<>();
    }
    
    static void input() throws IOException{
        N = Integer.parseInt(br.readLine());
        init();
        for(int i = 0; i < N; i++){
            nums[i] = br.readLine();
            nonZero.add(nums[i].charAt(0));
        }
    }
    
    static void solve(){
        for(int i = 0; i < N; i++){
            int len = nums[i].length();
            for(int j = 0; j < len; j++){
                alphabets[nums[i].charAt(j) - 'A'].sum += Math.pow(10, len - j - 1);
            }
        }
        
        Arrays.sort(alphabets);
        
        int temp = 1;
        boolean flag = false;
        
        for(int i = 0; i < 10; i++){
            if(!flag && !nonZero.contains(alphabets[i].name)){
                alphabets[i].num = 0;
                flag = true;
            } else{
                alphabets[i].num = temp++;
            }
        }
        
        long res = 0;
        
        for(int i = 0; i < 10; i++){
            res += alphabets[i].num * alphabets[i].sum;
        }
        sb.append(res);
    }
    
    public static void main(String[] args) throws IOException{
        input();
        solve();
        
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
