import sys
from collections import defaultdict

read = sys.stdin.readline

while True:
    N,K = map(int, read().split())
    if N == 0 and K == 0:
        break

    arr = list(map(int, read().split()))
    hashmap = defaultdict(int)

    idx = 0
    for i in range(1,N):
        hashmap[arr[i]] = arr[idx]
        if i < N-1 and arr[i+1] - arr[i] > 1:
            idx += 1

    count = 0

    if hashmap[hashmap[K]]:
        for node in arr:
            if hashmap[hashmap[K]] == hashmap[hashmap[node]] and hashmap[node] != hashmap[K]:
                count += 1

    print(count)
