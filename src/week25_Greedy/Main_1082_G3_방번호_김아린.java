import java.io.*;
import java.util.StringTokenizer;
import java.math.BigInteger;

public class Main {
    static int n;
    // 각 방 번호 숫자의 비용
    static int[] costs;
    static int m;
    // 최대 숫자로 표현된 문자열 => 50자리 수라서 String
    static String maxNumStr;
    static String[] dp;
    static String[] digitStr = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

    // 두 문자열 숫자를 비교하여 더 큰 숫자 문자열을 반환하는 함수
    static String getMaxStr(String str1, String str2) {
        if (str1 == null || str2 == null) return str1 == null ? str2 : str1;

        BigInteger b1 = new BigInteger(str1);
        BigInteger b2 = new BigInteger(str2);
        return b1.compareTo(b2) > 0 ? str1 : str2;
    }

    static void solution() {
        // 입력된 cost 금액에 해당하는 숫자로 dp 초기화
        for (int i = 0; i < n; i++) {
            for (int totalCost = costs[i]; totalCost <= m; totalCost++)
                dp[totalCost] = getMaxStr(dp[totalCost], digitStr[i]);
        }

        // DP 채우기: totalCost 금액 내로 만들 수 있는 최대 숫자 계산
        for (int totalCost = 1; totalCost <= m; totalCost++) {
            for (int i = 0; i < n; i++) {
                int cost = costs[i];
                   
                //돈없을떄
                if (totalCost < cost)
                    continue;

                //없을때
                if (dp[totalCost - cost] == null)
                    continue;
                
                dp[totalCost] = getMaxStr(
                        dp[totalCost], dp[totalCost - cost] + dp[cost]
                );
            }
        }

        // dp[m]에 001 처럼 존재할 수 있으므로 제거
        BigInteger b = new BigInteger(dp[m]);
        maxNumStr = b.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        n = Integer.parseInt(br.readLine());
        StringTokenizerst = new StringTokenizer(br.readLine());

        costs = new int[n];
        for (int i = 0; i < n; i++)
            costs[i] = Integer.parseInt(st.nextToken());

        m = Integer.parseInt(br.readLine());
        dp = new String[m + 1];

        solution();
        
        System.out.println(maxNumStr);
    }
}
