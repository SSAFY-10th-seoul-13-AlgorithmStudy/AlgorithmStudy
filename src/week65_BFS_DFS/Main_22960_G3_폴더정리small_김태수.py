import sys
input = sys.stdin.readline

N,M = map(int, input().split())
dict = dict()
files = set()

def dfs(path):
    global num 
    if path not in dict:
        return 
    
    for child in dict[path]:
        if child in dict:
            dfs(child)
        else:
            files.add(child)
            num += 1

for i in range(N+M):
    parent,child,type = input().split()
    if parent not in dict:
        dict[parent] = []
    
    if int(type) == 1 and child not in dict:
        dict[child] = []

    dict[parent].append(child)

Q = int(input())

for _ in range(Q):
    files = set()
    path = list(input().rstrip().split("/"))
    num = 0
    dfs(path[-1])
    print(len(files), num)


