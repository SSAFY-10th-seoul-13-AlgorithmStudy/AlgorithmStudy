import sys
import collections

read = sys.stdin.readline

snapshot = ""

for _ in range(3):
    snapshot += "".join(list(input().split()))
visited = {
    snapshot:0
}

qq = collections.deque([snapshot])

dx = [-1,1,0,0]
dy = [0,0,-1,1]

def bfs():
    while qq:
        snapshot = qq.popleft()
        count = visited[snapshot]
        z = snapshot.index('0')

        if snapshot == '123456780':
            return count
        
        x = z // 3
        y = z % 3

        count += 1

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if 0<= nx <3 and 0 <=ny <3:
                nz = nx*3 + ny
                to_list = list(snapshot)
                to_list[z], to_list[nz] = to_list[nz], to_list[z]
                new_shot = "".join(to_list)

                if visited.get(new_shot,0) == 0:
                    visited[new_shot] = count
                    qq.append(new_shot)
    return -1


print(bfs())

