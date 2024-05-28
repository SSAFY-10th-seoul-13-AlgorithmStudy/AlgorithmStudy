package week21_Greedy;

import java.io.*;
import java.util.*;

public class Main_1202_G2_보석도둑_송윤재 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Jewel[] jewels = new Jewel[N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            jewels[i] = new Jewel(Integer.parseInt(st.nextToken()),
                                  Integer.parseInt(st.nextToken()));
        }

        TreeSet<Bag> bags = new TreeSet<>();
        for (int i = 0; i < K; ++i) {
            bags.add(new Bag(i, Integer.parseInt(br.readLine())));
        }

        // solution
        Arrays.sort(jewels, (o1, o2) -> {
            if (o1.v != o2.v)
                return Integer.compare(o2.v, o1.v);
            return Integer.compare(o2.m, o1.m);
        });

        long answer = 0;
        for (Jewel jewel: jewels) {
            Bag bag = bags.ceiling(new Bag(-1, jewel.m));
            if (bag != null) {
                bags.remove(bag);
                answer += jewel.v;
            }
        }
        System.out.println(answer);
        br.close();
    }

    private static class Bag implements Comparable<Bag> {
        int index, capacity;

        public Bag(int index, int capacity) {
            this.index = index;
            this.capacity = capacity;
        }

        @Override
        public int compareTo(Bag o2) {
            if (this.capacity != o2.capacity)
                return Integer.compare(this.capacity, o2.capacity);
            return Integer.compare(this.index, o2.index);
        }
    }

    private static class Jewel {
        int m, v;

        public Jewel(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }
}