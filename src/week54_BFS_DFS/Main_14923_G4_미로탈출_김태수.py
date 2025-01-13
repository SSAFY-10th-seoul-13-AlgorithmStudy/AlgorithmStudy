import sys
import collections

read = sys.stdin.readline

N,M = map(int, read().split())

hy,hx = map(int,read().split())

ey,ex = map(int,read().split())

hx -= 1
hy -= 1
ex -= 1
ey -= 1

arr = [list(map(int, read().split())) for _ in range(N)]

dir = [
    [1,0],
    [0,1],
    [-1,0],
    [0,-1]
]

v = [[[False for _ in range(2)] for _ in range(M)] for _ in range(N)]
v[hy][hx][1] = True
qq = collections.deque()

qq.append([hx,hy,0,1])

answer = -1
while qq:
    x,y,depth,shot = qq.popleft()
    if(x == ex and y == ey): 
        answer = depth
        break
    for i in dir:
        nx = x + i[0]
        ny = y + i[1]

        if 0 <= nx < M and 0 <= ny < N:
            if v[ny][nx][shot]: continue
            if arr[ny][nx] == 1 and shot == 1:
                v[ny][nx][1] = True
                qq.append([nx,ny,depth+1,shot-1])
            elif arr[ny][nx] == 0:
                v[ny][nx][shot] = True
                qq.append([nx,ny,depth+1,shot])

print(answer)


