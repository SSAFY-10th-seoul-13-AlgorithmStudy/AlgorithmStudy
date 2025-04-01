dir = [
    [0,1],  # 오른쪽
    [0,-1], # 왼쪽
    [-1,0], # 위
    [1,0]   # 아래
]

import sys
read = sys.stdin.readline

N,K = map(int, read().split())

colors = [list(map(int, read().split())) for _ in range(N)]

matrix = [[[] for _ in range(N)] for _ in range(N)]
pos = [[] for _ in range(K)]
dirs = [0 for _ in range(K)]
count = 0

for i in range(K):
    x,y,direction = map(int, read().split())
    x -= 1
    y -= 1
    matrix[x][y].append(i)
    pos[i] = [x,y]
    dirs[i] = direction - 1

def isMalsAllinOrder():
    for i in range(N):
        for j in range(N):
            if len(matrix[i][j]) >= 4: return True
    return False

def swapDirection(direction):
    if direction == 0: return 1
    elif direction == 1: return 0
    elif direction == 2: return 3
    else: return 2

def findMalsToMove(idx):
    x,y = pos[idx]
    for i in range(len(matrix[x][y])):
        if matrix[x][y][i] == idx:
            result = matrix[x][y][i:]
            matrix[x][y] = matrix[x][y][:i]
            return result
    return []

def validate(x,y):
    return 0 <= x < N and 0 <= y < N

def move(idx):
    x,y = pos[idx]
    direction = dirs[idx]
    nx,ny = x + dir[direction][0], y + dir[direction][1]
    
    if not validate(nx,ny) or colors[nx][ny] == 2:
        dirs[idx] = swapDirection(direction)
        direction = dirs[idx]
        nx,ny = x + dir[direction][0], y + dir[direction][1]

        if not validate(nx,ny) or colors[nx][ny] == 2:
            return
    
    malsToMove = findMalsToMove(idx)
    
    if colors[nx][ny] == 1:
        malsToMove.reverse()
    
    matrix[nx][ny].extend(malsToMove)

    for item in malsToMove:
        pos[item] = [nx,ny]

def rounds():
    count = 1
    while count < 1001:
        for i in range(K):
            move(i)
            if isMalsAllinOrder():
                return count
        count += 1
    return -1

print(rounds())

