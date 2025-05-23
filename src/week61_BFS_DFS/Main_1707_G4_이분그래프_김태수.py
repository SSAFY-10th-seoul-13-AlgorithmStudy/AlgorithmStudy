import sys
sys.setrecursionlimit(10**6)
input = sys.stdin.readline

def is_bipartite(V, adj):
    color = [0] * (V + 1)
    def dfs(node, c):
        color[node] = c
        for nxt in adj[node]:
            if color[nxt] == 0:
                if not dfs(nxt, -c):
                    return False
            elif color[nxt] == color[node]:
                return False
        return True
    for i in range(1, V + 1):
        if color[i] == 0:
            if not dfs(i, 1):
                return False
    return True

t = int(input())
for _ in range(t):
    V, E = map(int, input().split())
    adj = [[] for _ in range(V + 1)]
    for _ in range(E):
        a, b = map(int, input().split())
        adj[a].append(b)
        adj[b].append(a)
    if is_bipartite(V, adj):
        print('YES')
    else:
        print('NO')
