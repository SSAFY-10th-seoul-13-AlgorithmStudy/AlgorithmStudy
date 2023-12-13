package week05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Node {
	int u, v;

	public Node(int u, int v) {
		this.u = u;
		this.v = v;
	}
}

public class Main_19535_G3_ㄷㄷㄷㅈ_김아린 {
    public static void main(String[] args) throws IOException {
    	//ㅈ : N개의 간선 중 3개 뽑기
    	//ㄷ : (현재 노드의 간선 - 1) * (연결된 노드의 간선 - 1)
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	StringTokenizer st = null;
    	int N = Integer.parseInt(br.readLine());
    	int[] child = new int[N+1];
    	ArrayList<Node> list = new ArrayList<Node>();
    	for (int i = 1; i < N; i++) {
    		st = new StringTokenizer(br.readLine());
    		int u = Integer.parseInt(st.nextToken());
    		int v = Integer.parseInt(st.nextToken());
    		child[u]++;
    		child[v]++;
    		list.add(new Node(u, v));
    	}
    	
    	//ㅈ모양
    	long gz = 0;
    	for (int i = 1; i <= N; i++) {
    		if (child[i] >= 3) {
    			gz += (long) child[i] * (child[i]-1) * (child[i]-2) / 6; 
    		}
    	}
    	
    	//ㄷ모양
    	long d = 0;
    	for (Node node : list) {
    		d += (long)(child[node.u]-1) * (child[node.v] - 1);
    	}
    	
    	String output = d == gz*3 ? "DUDUDUNGA" : (d > gz*3 ? "D" : "G" );
    	System.out.println(output);
    }
}
