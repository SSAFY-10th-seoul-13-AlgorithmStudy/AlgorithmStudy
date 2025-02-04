import sys
from collections import deque
read = sys.stdin.readline

word = read()
length = len(word)

bIndex = deque()
aIndex = deque()

count =0
for i in range(length):
    ## 돌겠네.. peek 어떻게 하더라 
    if word[i] == 'C' and len(bIndex) > 0 and bIndex[0] < i:
        count += 1
        bIndex.popleft()
    if word[i] == 'A':
        aIndex.append(i)
    if word[i] == 'B':
        bIndex.append(i)
        
while len(aIndex) and len(bIndex):
    ## 돌겠네.. peek 어떻게 하더라 
    if aIndex[0] < bIndex[0]:
        count +=1
        bIndex.popleft()
        aIndex.popleft()
    else:
        bIndex.popleft()

print(count)