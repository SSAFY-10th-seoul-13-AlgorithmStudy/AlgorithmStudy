import sys
input = sys.stdin.readline

N = int(input())

dict = [0] * 10; #a,b,c,d,e,f,g,h,i,j
#a = 100 b =10 c = 1
#c = 100 b = 10 a = 1
notAllowed = [False] * 10;
num = 0
zeroIdx = 0

for i in range(N):
    word = input().rstrip()
    mult = 1;
    for j in range(len(word)-1, -1, -1):
        idx = ord(word[j]) - ord('A')
        dict[idx] += mult
        mult *= 10
        if j == 0:
            notAllowed[idx] = True
flag = False
for i in range(10):
    if dict[i] > 0:
        flag = True

if flag:
    mins = sys.maxsize
    for i in range(10):        
        if not notAllowed[i] and mins > dict[i]:
            mins = dict[i]
            zeroIdx = i
    dict[zeroIdx] = 0

dict.sort()
for i in range(9, -1, -1):
    num += i * dict[i]
print(num)



