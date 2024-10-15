package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_13144_G4_ListOfUniqueNumbers_강이규 {

    static int N;
    static int[] arr;


    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void solve() {
        long result = 0; // max 100000^2
        int l = 0;
        Set<Integer> numbers = new HashSet<>();

        for (int r = 0; r < N; r++) {
            while (numbers.contains(arr[r])) {
                numbers.remove(arr[l]);
                result += r - l;
                l++;
            }
            numbers.add(arr[r]);
        }
        int remain = numbers.size();
        for (int i = 1; i <= remain; i++) {
            result += i;
        }
        System.out.println(result);


    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

}
