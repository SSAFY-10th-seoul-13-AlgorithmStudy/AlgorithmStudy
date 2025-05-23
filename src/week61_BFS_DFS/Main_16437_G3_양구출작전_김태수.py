import sys
sys.setrecursionlimit(10**6)
read = sys.stdin.readline

N = int(read())

row = [[] for _ in range(N + 2)]
tree = [[0,0],[0,0]]

for i in range(1, N):
    t, a, p = read().split()
    a, p = int(a), int(p)
    row[p].append(i+1)
    tree.append([t, a, p])

def dfs(node):
    count = 0
    for i in row[node]:
        count += dfs(i)
    if tree[node][0] == 'S' and node != 1:
        count +=  tree[node][1]
    elif tree[node][0] == 'W':
        count -= tree[node][1]

    if count <= 0:
        count = 0

    return count

print(dfs(1))

