package BOJ;

import java.io.*;
import java.util.*;

public class Main_9567_G2_책나눠주기_강이규 {

    static class Student implements Comparable<Student> {
        int a, b;

        public Student(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Student o) {
            if (this.b != o.b) {
                return Integer.compare(this.b, o.b);
            }
            return Integer.compare(this.a, o.a);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Student[] students = new Student[M];
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                students[i] = new Student(a, b);
            }
            Arrays.sort(students);

            boolean[] visited = new boolean[N + 1];
            int answer = 0;

            StudentLoop: for (int i = 0; i < M; i++) {
                Student student = students[i];

                int idx = student.a;
                int end = student.b;
                while (visited[idx]) {
                    if (++idx > end) continue StudentLoop;
                }
                // 줄 수 있는 책 번호를 찾은 경우
                answer++;
                visited[idx] = true;
            }

            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }

}
