package week02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_5639_G5_이진검색트리_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static Node root;
    
    static class Node{
    	int value;
    	Node left;
    	Node right;
    	
    	public Node(int value) {
    		this.value = value;
    	}
    	
    	void insert(int value) {
    		if(this.value > value) {
    			if(left == null) left = new Node(value);
    			else this.left.insert(value);
    		} else {
    			if(right == null) right = new Node(value);
    			else this.right.insert(value);
    		}
    	}
    }
    
    static void input() throws IOException{
    	root = new Node(Integer.parseInt(br.readLine()));
    	String in;
        while(true) {
        	in = br.readLine();
            if(in == null || in.equals("")) break;
            root.insert(Integer.parseInt(in));
        }
    }   
    
    static void solve() {
    	postOrder(root);
    }
    
    static void postOrder(Node node) {
        if (node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.value).append("\n");
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
