package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14908_G1_구두수선공_강이규 {

    static class Job implements Comparable<Job> {
        int n, s, t;

        public Job(int n, int s, int t) {
            this.n = n;
            this.s = s;
            this.t = t;
        }

        @Override
        public int compareTo(Job o) {
            // 반환값이 양수면, o가 앞에 온다.
            // o가 뒤로 가는 비용이 더 크면, 앞으로 가야 한다.
            return o.s * this.t - this.s * o.t;
        }
    }

    static int N;
    static Job[] jobs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        jobs = new Job[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int t = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            jobs[i] = new Job(i + 1, s, t);
        }

        Arrays.sort(jobs);

        StringBuilder sb = new StringBuilder();
        for (Job job : jobs) {
            sb.append(job.n).append(" ");
        }
        System.out.print(sb);
    }
}
