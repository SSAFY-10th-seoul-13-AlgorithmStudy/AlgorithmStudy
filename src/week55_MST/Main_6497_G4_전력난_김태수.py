import sys
read = sys.stdin.readline

def find_parent(parent,x):
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]

def union_parent(parent, a,b):
    a = find_parent(parent,a)
    b = find_parent(parent,b)

    if a < b:
        parent[b] = a
    else:
        parent[a] = b


while True:
    N,M = map(int,read().split())
    if(N,M) == (0,0): break
    
    parent = [0 for _ in range(N)]

    for i in range(N):
        parent[i] = i

    edges = []
    total = 0
    result = 0

    for _ in range(M):
        x,y,z = map(int, read().split())
        total += z
        edges.append((z,x,y))

    edges.sort()

    for edge in edges:
        cost, a, b = edge
        if find_parent(parent,a) != find_parent(parent,b):
            union_parent(parent, a,b)
            result += cost

    print(total - result)

        