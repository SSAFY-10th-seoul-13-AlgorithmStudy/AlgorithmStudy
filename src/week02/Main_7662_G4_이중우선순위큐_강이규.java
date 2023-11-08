import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_7662_G4_이중우선순위큐_강이규 {

    static TreeMap<Integer, Integer> map = new TreeMap<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            map.clear();
            int k = Integer.parseInt(br.readLine());
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                String oper = st.nextToken();
                int val = Integer.parseInt(st.nextToken());

                if (oper.equals("I")) {
                    Integer curCnt = map.getOrDefault(val, 0);
                    map.put(val, curCnt + 1);
                    continue;
                }
                // pop
                if (!map.isEmpty()) {
                    int targetKey = val < 0 ? map.firstKey() : map.lastKey();
                    int targetVal = map.get(targetKey);
                    if (targetVal != 1) map.put(targetKey, targetVal-1);
                    else map.remove(targetKey);
                }
            }
            if (!map.isEmpty()) {
                sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
            } else sb.append("EMPTY\n");
        }
        System.out.println(sb);
    }
}