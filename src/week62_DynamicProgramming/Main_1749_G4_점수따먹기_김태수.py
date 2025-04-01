import sys
read = sys.stdin.readline

N,M = map(int, read().split())

matrix = [list(map(int,read().split())) for _ in range(N)]

sumMatrix = [[0 for _ in range(M+1)] for _ in range(N+1)]

for i in range(1,N+1):
    for j in range(1,M+1):
        sumMatrix[i][j] = sumMatrix[i-1][j] + sumMatrix[i][j-1] + matrix[i-1][j-1] - sumMatrix[i-1][j-1]

answer = -sys.maxsize

for x1 in range(1,N+1):
    for y1 in range(1,M+1):
        for x2 in range(x1,N+1):
            for y2 in range(y1,M+1):
                answer = max(answer, sumMatrix[x2][y2] - sumMatrix[x1-1][y2] - sumMatrix[x2][y1-1] + sumMatrix[x1-1][y1-1])
print(answer)