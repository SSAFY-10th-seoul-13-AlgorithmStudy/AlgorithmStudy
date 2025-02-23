import sys
read = sys.stdin.readline

N = int(read())
guilty = list(map(int, read().split()))
matrix = [list(map(int, read().split())) for _ in range(N)]
me = int(read())
dead = [False] * N

def dfs(left, depth):
    if left == 2:
        return depth+1

    if left == 1:
        return depth
    
    if left % 2== 0:
        result = depth+1
        
        for i in range(N):
            if dead[i] or i == me: continue
            maxVal, maxIdx = -float('inf'), -1

            for j in range(N):
                if dead[j] or i == j: continue
                guilty[j] += matrix[i][j]
                if guilty[j] > maxVal:
                    maxVal, maxIdx = guilty[j],j
            if maxIdx != me:
                dead[i] = True
                dead[maxIdx] = True
                result = max(result, dfs(left-2, depth+1))
                dead[i] = False
                dead[maxIdx] = False
            for j in range(N):
                if dead[j] or i == j:
                    continue
                guilty[j] -= matrix[i][j]
    else:
        maxVal, maxIdx = -float('inf'), -1
        for i in range(N):
            if dead[i]: continue
            if guilty[i] > maxVal:
                maxVal, maxIdx = guilty[i],i
        if maxIdx == me:
            return depth
        else:
            dead[maxIdx] = True
            result = dfs(left - 1, depth)
            dead[maxIdx] = False
    return result

print(dfs(N,0))
