import sys
read = sys.stdin.readline

N,M = map(int, read().split())

men = list(map(int, read().split()))
women = list(map(int, read().split()))
men.sort()
women.sort()

dp = [[0 for _ in range(M+1)] for _ in range(N+1)]

for i in range(1,N+1):
    for j in range(1, M+1):
        dp[i][j] = dp[i-1][j-1] + abs(men[i-1] - women[j-1])
        if i > j:
            dp[i][j] = min(dp[i][j], dp[i-1][j])
        elif i < j:
            dp[i][j] = min(dp[i][j], dp[i][j-1])

print(dp[N][M])