import os
from collections import defaultdict

users = [
    "강이규",
    "김아린",
    "김태수",
    "김희연",
    "송윤재"
]
Algorithm = [
    'BackTracking',
    'BFS_DFS',
    'BinarySearch',
    'BruteForce',
    'DataStructure',
    'Dijkstra',
    'DivideAndConquer',
    'DynamicProgramming',
    'FloydWarshall',
    'Greedy',
    'Implementation',
    'KMP',
    'LCA',
    'MST',
    'PrefixSum',
    'SegmentTree',
    'String',
    'TopologySort',
    'Tree',
    'Trie',
    'TwoPointer',
    'UnionFind'
]

file_path = os.path.abspath(__file__)
BASE_DIR = os.path.dirname(file_path)
def generateREADME():
    with open(os.path.join(BASE_DIR,"..","README.md"),"w",encoding="UTF-8") as f:
        f.write("# ☘️ Algorithm For B ☘️\n") ##제목 쓰세요
        ## 원하는 본문 내용 있으면 추가해 주세요
        f.write("## 알고리즘별 분류 \n")
        data = getAlgoData()
        f.write(createAlgo(data))
        f.write("## 주차별 풀이 상황 \n")
        
        week = getWeekData()
        for case in sorted(week.keys()):
            f.write(createWeekToggle(week[case], case))
        print("all records are up to date!")

def getAlgoData():
    data = {}
    for pathName in os.listdir(BASE_DIR+"/../src"):
        if len(pathName.split("_")) >= 2:
            algo = ("_").join(pathName.split("_")[1:])
            # algo = pathName.split("_")[1]
            #print(algo)
            if(algo not in data): data[algo] = {}
            for fileName in os.listdir(os.path.join(BASE_DIR,"..","src",pathName)):
                solver = fileName.split("_")[4].split(".")[0]
                if solver not in data[algo]: data[algo][solver] = 1
                else: data[algo][solver] += 1
    #print("data: " ,data)
    return data
            


def createAlgo(data):
    #print("create: ",data)
    line = '''| Algorithm | '''
    for user in users:
        line += user + ''' | '''
    line += '''\n | :--: | :--: | :--: | :--: | :--: | :--: | \n'''
    for algo in Algorithm:
        line += (" | " + algo + " | ")
        for user in users:
            if(algo not in data): continue
            if(user not in data[algo]): line += " 0 |"
            else: line += (str(data[algo][user]) + " | ")
        line += "\n"
    return line
    

def createWeekToggle(data,week):
        #print(data)
        line = ""
        line += '''\n<details>\n<summary>\n'''
        line += (week)
        line += '''\n</summary>\n'''
        line += '''<div markdown="1">\n'''
        line += "\n"
        line += " | 문제 | "
        for user in users:
            line += user
            line += " | "
        line += '''\n | :--: | :--: | :--: | :--: | :--: | :--: | \n'''
        for problem in data:
            line += (" | " + problem + " | ")
            for user in users:
                #print(user)
                if user not in data[problem]:
                    line += ( " ❌ "+ " | ")
                else:
                    line += ( " ✔️ "+ " | ")
            line += "\n"
        line += '''</div>'''
        line+='''</details>\n<br>'''
        return line
        
def getWeekData():
    count="1"
    data = {}
    #print(os.listdir("../src"))
    #os.path.join(BASE_DIR,"..","src",pathName)
    for pathName in os.listdir(os.path.join(BASE_DIR,"..","src")):
        data[pathName] = {}
        print(os.path.join(BASE_DIR,"..","src",pathName))
        fileList = os.listdir(os.path.join(BASE_DIR,"..","src",pathName))
            #print ("file_list: {}".format(fileList))
        for file in fileList:
            solve = file.split("_")
            if(solve[3] not in data[pathName]): data[pathName][solve[3]] = []
            data[pathName][solve[3]].append(solve[4].split(".")[0])
    return data

generateREADME()