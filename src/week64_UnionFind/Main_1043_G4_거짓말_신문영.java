package week64_UnionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_1043_G4_거짓말_신문영 {
    static int[] people;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(stringTokenizer.nextToken());
        int m = Integer.parseInt(stringTokenizer.nextToken());

        people = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            people[i] = i;
        }

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int know = Integer.parseInt(stringTokenizer.nextToken());
        List<Integer> knowList = new ArrayList<>();
        for (int i = 0; i < know; i++) {
            knowList.add(Integer.parseInt(stringTokenizer.nextToken()));
            union(knowList.get(0), knowList.get(i));
        }

        List<int[]> partyList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int peopleNum = Integer.parseInt(stringTokenizer.nextToken());

            boolean knowFlag = false;
            int[] partyPeople = new int[peopleNum];
            int before = partyPeople[0] = Integer.parseInt(stringTokenizer.nextToken());
            for (int j = 1; j < peopleNum; j++) {
                int now = partyPeople[j] = Integer.parseInt(stringTokenizer.nextToken());
                union(now, before);
                before = now;

                if (knowList.contains(before)) knowFlag = true;
            }

            if (knowFlag) {
                if (knowList.isEmpty()) knowList.add(partyPeople[0]);

                union(find(partyPeople[0]), find(knowList.get(0)));

                for (int partyPerson : partyPeople) knowList.add(partyPerson);
            }

            partyList.add(partyPeople);
        }

        if (!knowList.isEmpty()) {
            Set<Integer> knowSet = new HashSet<>();
            knowSet.add(knowList.get(0));

            int parentKnow = find(knowList.get(0));
            for (int i = 1; i <= n; i++) {
                if (parentKnow == find(people[i])) {
                    knowSet.add(i);
                }
            }

            int answer = m;
            for (int[] partyPeople : partyList) {
                for (int partyPerson : partyPeople) {
                    if (knowSet.contains(partyPerson)) {
                        answer--;
                        break;
                    }
                }
            }

            System.out.println(answer);
        } else {
            System.out.println(m);
        }
    }

    public static int find(int a) {
        if (people[a] == a) return a;
        return people[a] = find(people[a]);
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;

        people[bRoot] = aRoot;
        return true;
    }

}
