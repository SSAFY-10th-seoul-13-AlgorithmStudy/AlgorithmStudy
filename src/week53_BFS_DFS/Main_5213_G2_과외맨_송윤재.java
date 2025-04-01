package week53_BFS_DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_5213_G2_과외맨_송윤재 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int N, tile_size, tiles[][], max_num;
	static Node resNode;
	
	static class Node{
		int num;
		Node pre;
		public Node(int num, Node pre) {
			this.num = num;
			this.pre = pre;
		}
	}
	
	static void init() {
		tile_size = N * N - N / 2;
		tiles = new int[tile_size + 1][2];
	}
	
	static void input() throws IOException {
		N = Integer.parseInt(br.readLine());
		init();
		for(int i = 1; i <= tile_size; i++) {
			st = new StringTokenizer(br.readLine());
			tiles[i][0] = Integer.parseInt(st.nextToken());
			tiles[i][1] = Integer.parseInt(st.nextToken());
		}
	}
	
	static void solve() {
		bfs();
		print();
	}
	
	static void bfs() {
		Queue<Node> que = new ArrayDeque<>();
		que.offer(new Node(1, null));
		boolean visited[] = new boolean[tile_size + 1];
		visited[1] = true;
		
		while(!que.isEmpty()) {
			Node cur = que.poll();
			int num = cur.num;
			
			if(num > max_num) {
				max_num = num;
				resNode = cur;
				if(max_num == tile_size)
					return;
			}
			
			// 위로 가기
			if(findFloor(num) != 1) {
			    if(num - N > 0 && tiles[num][0] == tiles[num - N][1]) {
			        int next = num - N;
			        if(findFloor(num) == findFloor(next) + 1 && !visited[next]) {
			            visited[next] = true;
			            que.offer(new Node(next, cur));
			        }
			    }
			    if(num - N + 1 > 0 && tiles[num][1] == tiles[num - N + 1][0]) {  // ⬅️ 예외 방지
			        int next = num - N + 1;
			        if(findFloor(num) == findFloor(next) + 1 && !visited[next]) {
			            visited[next] = true;
			            que.offer(new Node(next, cur));
			        }
			    }

			    if(num - 1 > 0 && tiles[num][0] == tiles[num - 1][1]) {  // ⬅️ 예외 방지
			        int next = num - 1;
			        if(findFloor(num) == findFloor(next) && !visited[next]) {
			            visited[next] = true;
			            que.offer(new Node(next, cur));
			        }
			    }
			}

			// 오른쪽으로 이동
			if(num < tile_size && tiles[num][1] == tiles[num + 1][0]) {  // ⬅️ 예외 방지
			    int next = num + 1;
			    if(findFloor(num) == findFloor(next) && !visited[next]) {
			        visited[next] = true;
			        que.offer(new Node(next, cur));
			    }
			}

			// 아래로 가기
			if(findFloor(num) != N) {
			    if(num + N - 1 <= tile_size && tiles[num][0] == tiles[num + N - 1][1]) {  // ⬅️ 예외 방지
			        int next = num + N - 1;
			        if(findFloor(num) == findFloor(next) - 1 && !visited[next]) {
			            visited[next] = true;
			            que.offer(new Node(next, cur));
			        }
			    }
			    if(num + N <= tile_size && tiles[num][1] == tiles[num + N][0]) {  // ⬅️ 예외 방지
			        int next = num + N;
			        if(findFloor(num) == findFloor(next) - 1 && !visited[next]) {
			            visited[next] = true;
			            que.offer(new Node(next, cur));
			        }
			    }
			}
		}
	}
	
	static void print() {
		Stack<Integer> tileNumbers = new Stack<>();
        Node cur = resNode;

        while (cur != null) {
            tileNumbers.push(cur.num);
            cur = cur.pre;
        }
        int size = tileNumbers.size();
        sb.append(size).append("\n");
        for (int i = 0; i < size; i++) {
            sb.append(tileNumbers.pop()).append(" ");
        }
	}
	
	static int findFloor(int num) {
	    int base = (2 * N - 1);
	    int floor = (num - 1) / base * 2 + 1;  
	    if ((num - 1) % base >= N) floor++; 
	    return floor;
	}
	
	public static void main(String[] args) throws IOException {
		input();
		solve();

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
