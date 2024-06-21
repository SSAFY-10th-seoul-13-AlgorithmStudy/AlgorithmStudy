import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        // 루트 노드를 빈 문자열로 초기화
        HashMap<String, HashMap> root = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            HashMap<String, HashMap> now = root;

            for (int j = 0; j < K; j++) {
                String food = st.nextToken();
                now.putIfAbsent(food, new HashMap<>());
                now = now.get(food);
            }
        }

        // 트리를 출력
        print(root, "");

        System.out.println(sb);
    }

    public static void print(HashMap<String, HashMap> root, String ans) {
        // 키셋을 리스트로 변환하고 정렬
        ArrayList<String> keySet = new ArrayList<>(root.keySet());
        Collections.sort(keySet);

        for (String s : keySet) {
            sb.append(ans).append(s).append("\n");
            print(root.get(s), ans + "--");
        }
    }
}
