import sys
import math

read = sys.stdin.readline

def find(parent, x):
    if x!= parent[x]:
        parent[x] = find(parent, parent[x])

    return parent[x]

def union(parent, a, b):
    a = find(parent, a)
    b = find(parent,b)

    if a > b:
        parent[a] = b
    else:
        parent[b] = a

def distance(loc1, loc2):
    return math.sqrt((loc1[0] - loc2[0]) ** 2 + (loc1[1] - loc2[1]) ** 2)

N, M = map(int, read().split())

parent = [i for i in range(N+1)]

loc = [[]]

for _ in range(N):
    loc.append(list(map(int, read().split())))

for _ in range(M):
    a, b = map(int, read().split())
    union(parent, a,b)

possible = []

for i in range(1, len(loc)):
    for j in range(i+1, len(loc)):
        possible.append([distance(loc[i],loc[j]),i,j])

possible.sort()
ans = 0

for route in possible:
    dist, x, y = route[0], route[1], route[2]

    if find(parent, x) != find(parent,y):
        union(parent, x,y)
        ans += dist
print(format(ans, ".2f"))