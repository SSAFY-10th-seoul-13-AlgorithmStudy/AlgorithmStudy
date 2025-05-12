import java.util.*;
import java.io.*;

public class Main {
    static StringTokenizer st;
    static int N, M; //폴더 / 파일
    static int cnt;
    static Set<String> set;
    static Map<String, ArrayList<String>> folders, files;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        folders = new HashMap<>();
        files = new HashMap<>();
        
        for (int i = 0; i < N+M; i++) {
            st = new StringTokenizer(br.readLine());
            String P = st.nextToken();
            String F = st.nextToken();
            int C = Integer.parseInt(st.nextToken());
            
            //흠..?
            if(C == 1) { //폴더
                ArrayList<String> tmp = folders.getOrDefault(P, new ArrayList<>());
                tmp.add(F);
                folders.put(P, tmp);
            } else { //파일
                ArrayList<String> tmp = files.getOrDefault(P, new ArrayList<>());
                tmp.add(F);
                files.put(P, tmp);
            }
        }
        
        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            cnt = 0;
            set = new HashSet<>();
            
            String[] t = br.readLine().split("/");
            String find = t[t.length-1];
            dfs(find);
            System.out.println(set.size() + " " + cnt);
        }
    }
    
    public static void dfs(String find) {
        //파일 찾기
        ArrayList<String> fileList = files.getOrDefault(find, new ArrayList<>());
        
        // if(fileList.size() != 0) {
        for (String s : fileList) {
            set.add(s);
            cnt++;
        }
        // }
        
        //폴더 있으면 넘어가기
        ArrayList<String> folderList = folders.getOrDefault(find, new ArrayList<>());
        // if(folderList.size() != 0) {
        for (String s : folderList) {
            dfs(s);
        }
        
        return;
        // }
    }
}
