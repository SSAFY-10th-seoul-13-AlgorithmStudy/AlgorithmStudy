import sys

read = sys.stdin.readline

A,B,C = map(int, read().split())

X = read().rstrip()
Y = read().rstrip()

dp = [[-sys.maxsize] * (len(Y)+1) for _ in range(len(X)+1)]

dp[0][0] = 0

for i in range(1,len(Y)+ 1):
    dp[0][i] = i * B

for i in range(1, len(X)+1):
    dp[i][0] = i * B

for i in range(1, len(X)+1):
    for j in range(1, len(Y)+1):
        dp[i][j] = max(dp[i-1][j] + B, dp[i][j-1] + B)
        adj = C if X[i-1] != Y[j-1] else A
        dp[i][j] = max(dp[i][j], dp[i-1][j-1] + adj)

print(dp[len(X)][len(Y)])


