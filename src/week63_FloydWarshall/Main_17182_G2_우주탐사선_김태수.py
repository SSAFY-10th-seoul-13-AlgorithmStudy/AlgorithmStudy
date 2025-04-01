import sys
read = sys.stdin.readline

N,K = map(int, read().split())

matrix = [list(map(int, input().split())) for _ in range(N)]

v = [False for _ in range(N)]

v[K] = True
answer = sys.maxsize

for k in range(N):
    for i in range(N):
        for j in range(N):
            matrix[i][j] = min(matrix[i][j], matrix[i][k] + matrix[k][j])

def dfs(depth, cur, cost):
    if depth == N:
        global answer
        answer = min(answer, cost)
        return
    for i in range(N):
        if v[i]: continue
        v[i] = True
        dfs(depth+1, i,cost + matrix[cur][i] )
        v[i] = False

dfs(1,K,0)

print(answer)
