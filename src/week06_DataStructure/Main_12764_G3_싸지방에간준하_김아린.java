package week06_DataStructure;

import java.io.*;
import java.util.*;

class Usage {
	int sTime, eTime, room;

	public Usage(int sTime, int eTime) {
		this.sTime = sTime;
		this.eTime = eTime;
	}
}

public class Main_12764_G3_싸지방에간준하_김아린 {
	public static void main(String[] args) throws IOException {
		// 모든 사람은 비어있는 자리 중에서 가장 번호가 작은 자리부터 앉음
		// 컴퓨터 최소 개수 / 자리별로 몇 명의 사람이 이용했는가?
		// 생각보다 자리 별로 세는게 빡센데?
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int p, q;
		StringTokenizer st = null;

		List<Usage> list = new ArrayList<Usage>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());

			list.add(new Usage(p, q));
		}
		
		Collections.sort(list, (o1, o2) -> o1.sTime-o2.sTime);

		PriorityQueue<Usage> left = new PriorityQueue<>((o1, o2) -> o1.eTime - o2.eTime);
		PriorityQueue<Usage> gone = new PriorityQueue<>((o1, o2) -> o1.room - o2.room);

		int[] roomCnt = new int[N];

		int roomNo = 0;
		
		for (Usage cur : list) {
			while (!left.isEmpty() && left.peek().eTime < cur.sTime)
				gone.add(left.poll());
		    // 현재(cur)사람의 P보다 Q가 작은 사람들은 candidates로 보낸다.
		    
		    int selectedRoomNo = gone.isEmpty() ? roomNo++ : gone.poll().room;
		    // gone이 비었다는 말은 빈자리가 없단 얘기이므로, 차선책으로 방 번호를 높힌다.
		    // gone이 비어있지 않다면, 가장 방 번호가 작은곳에 앉은사람 자리에 앉는다.
		    
		    roomCnt[selectedRoomNo]++;
		    cur.room = selectedRoomNo;
		    left.add(cur);
		}

		StringBuilder sb = new StringBuilder();
        int cnt = 0;
        for (int i = 0; i < N && roomCnt[i] != 0; i++, cnt++) {
            sb.append(roomCnt[i]).append(' ');
        }
        System.out.println(cnt);
        System.out.println(sb);

	}
}