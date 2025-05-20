import sys
from collections import deque


def bfs(mid):
    visited[start] = True
    qq = deque()
    qq.append(start)
    while qq:
        now = qq.popleft()
        if now == end:
            return True
        for next, cost in graph[now]:
            if not visited[next] and cost >= mid:
                qq.append(next)
                visited[next] = True
    return False

input = sys.stdin.readline

N, M = map(int, input().split())

graph = [[] for _ in range(N+1)]

for _ in range(M):
    a, b, c = map(int, input().split())
    graph[a].append((b, c))
    graph[b].append((a, c))

start, end = map(int, input().split())

left = 1
right = 1000000000

for i in range(1, N+1):
    graph[i].sort(reverse=True)

while left <= right:
    visited = [False] * (N+1)
    mid = (left + right) // 2
    if bfs(mid):
        left = mid + 1
    else:
        right = mid - 1

print(right)