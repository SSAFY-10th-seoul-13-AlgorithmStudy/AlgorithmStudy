package week65_BFS_DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_22860_G3_폴더정리small_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, M, Q;
	static Map<String, Folder> map;
	static Folder main;
	
	static class Folder{
		String name;
		List<Folder> subfolders;
		List<String> files;
		
		Folder(String name){
			this.name = name;
			subfolders = new ArrayList<>();
			files = new ArrayList<>();
		}
		
		Folder addFolder(Folder sub) {
			subfolders.add(sub);
			return sub;
		}

		int checkFiles(){
			int cnt = files.size();
			
			for(Folder f  : subfolders){
				cnt += f.checkFiles();
			}
			return cnt;
		}

		int distinctFiles(Set<String> set){
			for(Folder f : subfolders){
				f.distinctFiles(set);
			}
			
			for(String f : files){
				set.add(f);
			}
			return set.size();
		}
		
		Folder getSubFolder(String name) {
		    for (Folder f : subfolders) {
		        if (f.name.equals(name)) return f;
		    }
		    return null;
		}
	}
	
	static class Info {
	    String P, F;
	    int C;
	    Info(String P, String F, int C) {
	        this.P = P;
	        this.F = F;
	        this.C = C;
	    }
	}
	
	static void init() {
		main = new Folder("main");
		map = new HashMap<>();
		map.put("main", main);
	}
	
	static void input_solve() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		List<Info> infoList = new ArrayList<>();
		init();
		for(int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			String P = st.nextToken();
			String F = st.nextToken();
			int C = Integer.parseInt(st.nextToken());

		    if(!map.containsKey(P)) map.put(P, new Folder(P));
		    if(C == 1 && !map.containsKey(F)) map.put(F, new Folder(F));
		    
		    infoList.add(new Info(P, F, C));
		}
		
		for(Info info : infoList) {
		    Folder pre = map.get(info.P);
		    if(info.C == 1) {
		        pre.addFolder(map.get(info.F));
		    } else {
		        pre.files.add(info.F);
		    }
		}
		
		Q = Integer.parseInt(br.readLine());
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), "/");
			Folder cur = main;
			st.nextToken();
			while(st.hasMoreTokens()) {
			    cur = cur.getSubFolder(st.nextToken());
			}
			sb.append(cur.distinctFiles(new HashSet<>())).append(" ").append(cur.checkFiles()).append("\n");
		}
	}
	
	public static void main(String[] args) throws IOException{
		input_solve();
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
