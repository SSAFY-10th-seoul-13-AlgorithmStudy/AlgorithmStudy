package week02;

import java.io.*;

class Node {
	int key;
	Node left;
	Node right;

	public Node() {
	}
}

public class Main_5639_G5_이진검색트리_김아린 {
	static Node root = null;

	public static void main(String[] args) throws Exception {
		// 전위순회 값으로 트리 구성하고
		// 후위 순회하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String in;
		while ((in = br.readLine()) != null) {
//			if (in.equals(""))
//				break;
			int val = Integer.parseInt(in);
			add(val);
		}
		
		post(root);
	}

	public static void add(int key) {
		Node newNode = new Node();
		newNode.key = key;

		if (root == null) {
			root = newNode;
		} else {
			root = addNode(root, newNode);
		}
	}

	private static Node addNode(Node node, Node newNode) {
		if (node == null)
			return newNode;
		else if (node.key > newNode.key)
			node.left = addNode(node.left, newNode);
		else
			node.right = addNode(node.right, newNode);

		return node;
	}

	private static void post(Node node) {
		// 좌측 중위 순회
		if (node == null)
			return;
		if (node.left != null)
			post(node.left);

		if (node.right != null)
			post(node.right);
		System.out.println(node.key);
	}
}
