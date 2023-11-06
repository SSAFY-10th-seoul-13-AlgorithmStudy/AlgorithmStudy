package week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.text.AbstractDocument.BranchElement;

class Node{
	int value;
	Node left;
	Node right;
	public Node() {}
}

public class Main_5639_G5_이진검색트리_김태수 {
	public static Node root;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in;
		while((in = br.readLine()) != null) {
			if(in.equals("")) break;
			int val = Integer.parseInt(in);
			add(val);
		}
		
		dfs(root);
	}
	
	public static void add(int key) {
		Node newNode = new Node();
		newNode.value = key;
		
		if(root == null) {
			root = newNode;
		}
		else {
			root = addNode(root, newNode);
		}
		
	}
	
	public static Node addNode(Node curNode, Node newNode) {
		if(curNode == null) return newNode;
		else if(curNode.value > newNode.value) {
			curNode.left = addNode(curNode.left, newNode);
		}
		else {
			curNode.right = addNode(curNode.right, newNode);
		}
		return curNode;
	}
	
	public static void dfs(Node node) {
//		if(node.left == null && node.right == null) {
//			return;
//		}
		if(node.left != null) dfs(node.left);
		if(node.right != null) dfs(node.right);
		System.out.println(node.value);
	}
}
