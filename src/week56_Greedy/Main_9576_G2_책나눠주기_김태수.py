import sys
read = sys.stdin.readline

T = int(read())

while True:
    if T <= 0: 
        break
    else:
        T -= 1

    N, M = map(int, read().split())

    arr = []

    for _ in range(M):
        temp = list(map(int,read().split()))
        arr.append(temp)

    arr.sort(key=lambda x: (x[1],x[0]))
    books = [False for _ in range(N+1)]
    
    for i in range(M):
        flag = False
        idx = arr[i][0]

        while books[idx]:
            idx += 1
            if(idx == arr[i][1]+1): 
                flag = True
                break 
            
        if flag: continue
        else: books[idx] = True    

    count = 0
    for i in range(N+1):
        if books[i] == True : count += 1

    print(count)