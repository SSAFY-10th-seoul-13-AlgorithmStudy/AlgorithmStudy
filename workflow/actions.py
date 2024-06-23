import os
from collections import defaultdict

users = [
    "강이규",
    "김아린",
    "김태수",
    "신문영",
    "송윤재",
]

algorithms = [
    'BackTracking', 'BFS_DFS', 'BinarySearch', 'BruteForce', 'DataStructure',
    'Dijkstra', 'DivideAndConquer', 'DynamicProgramming', 'FloydWarshall', 'Greedy',
    'Implementation', 'KMP', 'LCA', 'MST', 'PrefixSum', 'SegmentTree', 'String',
    'TopologySort', 'Tree', 'Trie', 'TwoPointer', 'UnionFind','Math'
]

file_path = os.path.abspath(__file__)
BASE_DIR = os.path.dirname(file_path)

def generateREADME():
    with open(os.path.join(BASE_DIR, "..", "README.md"), "w", encoding="UTF-8") as f:
        f.write("# ☘️ Algorithm For B ☘️\n")
        f.write("## 알고리즘별 분류 \n")
        algo_data = getAlgoData()
        f.write(createAlgoSection(algo_data))
        f.write("## 주차별 풀이 상황 \n")
        
        week_data = getWeekData()
        for week in sorted(week_data.keys()):
            f.write(createWeekSection(week_data[week], week))
        print("all records are up to date!")

def getAlgoData():
    data = defaultdict(lambda: defaultdict(int))
    src_dir = os.path.join(BASE_DIR, "..", "src")
    for pathName in os.listdir(src_dir):
        if len(pathName.split("_")) >= 2:
            algo = "_".join(pathName.split("_")[1:])
            algo_path = os.path.join(src_dir, pathName)
            for fileName in os.listdir(algo_path):
                solver = fileName.split("_")[4].split(".")[0]
                data[algo][solver] += 1
    return data

def createAlgoSection(data):
    header = createAlgoHeader()
    body = createAlgoBody(data)
    return header + body

def createAlgoHeader():
    header = "| Algorithm | "
    header += " | ".join(users) + " | \n"
    header += "| :--: " * (len(users) + 1) + "|\n"
    return header

def createAlgoBody(data):
    body = ""
    for algo in algorithms:
        body += f" | {algo} | "
        for user in users:
            if algo in data and user in data[algo]:
                body += f"{data[algo][user]} | "
            else:
                body += "0 | "
        body += "\n"
    return body

def createWeekSection(data, week):
    header = createWeekHeader(week)
    body = createWeekBody(data)
    return header + body

def createWeekHeader(week):
    header = f"\n<details>\n<summary>\n{week}\n</summary>\n<div markdown=\"1\">\n\n"
    header += " | 문제 | " + " | ".join(users) + " | \n"
    header += "| :--: " * (len(users) + 1) + "|\n"
    return header

def createWeekBody(data):
    body = ""
    for problem in data:
        body += f" | {problem} | "
        for user in users:
            body += "✔️ | " if user in data[problem] else "❌ | "
        body += "\n"
    body += "</div></details>\n<br>"
    return body

def getWeekData():
    data = defaultdict(lambda: defaultdict(list))
    src_dir = os.path.join(BASE_DIR, "..", "src")
    for pathName in os.listdir(src_dir):
        problem_path = os.path.join(src_dir, pathName)
        for file in os.listdir(problem_path):
            solve = file.split("_")
            user = solve[3]
            problem = solve[4].split(".")[0]
            data[pathName][user].append(problem)
    return data

generateREADME()
