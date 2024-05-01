import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
    static boolean[] deleted;
    static int root = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }

        deleted = new boolean[N];
        int[] parents = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            parents[i] = parent;

            if (parent == -1) {
                root = i;
            } else {
                tree.get(parent).add(i);
            }
        }

        int delete = Integer.parseInt(br.readLine());
        deleteNode(delete, parents);

        if(delete == root) {
            System.out.println(0);
        } else {
            System.out.println(countLeafNodes());
        }
    }

    public static void deleteNode(int node, int[] parents) {
        deleted[node] = true;

        int parent = parents[node];
        if (parent != -1) {
            tree.get(parent).remove(Integer.valueOf(node));
        }

        for (int child : new ArrayList<>(tree.get(node))) {
            deleteNode(child, parents);
        }
    }

    public static int countLeafNodes() {
        int leafCount = 0;
        for (int i = 0; i < N; i++) {
            if (!deleted[i] && tree.get(i).isEmpty()) {
                leafCount++;
            }
        }
        return leafCount;
    }
}
