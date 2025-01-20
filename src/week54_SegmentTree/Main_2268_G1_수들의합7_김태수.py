import sys
read = sys.stdin.readline

def modify( node, start, end, index, value):
    if index < start or index >end: return

    if start == end:
        tree[node] = value
        return
    
    modify(node * 2, start, (start + end) // 2, index, value )
    modify(node * 2 + 1, (start + end) // 2, end, index, value)

    tree[node] = tree[node * 2] + tree[node * 2 + 1]

def sum(node, start, end, left, right):
    if left > end or right < start: return 0 
    if left <= start and right >= end: return tree[node]

    return sum(node * 2, start, (start + end)//2, left , right) + sum(node*2+1, (start+end)//2, end, left, right)


N,M = map(int, read().split())

tree = [0]*(4*N)
L = [0] * N

for i in range(M):
    Q,A,B = map(int, read().split())

    if Q == 0 :
        if A > B:
            A, B = B, A
            print(sum(1,0,N-1, A-1,B-1))
        else:
            modify(1,0,N-1,A-1,B)