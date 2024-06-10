import numpy as np
import random
import math
import networkx as nx
import matplotlib.pyplot as plt
from numpy import linalg as la, inexact
import time

start = time.time()
# cities =np.array([(456, 320),
#          (228, 0),
#          (912, 0),
#          (0, 80),
#          (114, 80),
#          (570, 160),
#          (798, 160),
#          (342, 240),
#          (684, 240),
#          (570, 400),
#          (912, 400),
#          (114, 480),
#          (228, 480),
#          (342, 560),
#          (684, 560),
#          (0, 640),
#          (798, 640)])
pos = [(37, 52),
       (49, 49),
       (52, 64),
       (20, 26),
       (40, 30),
       (21, 47),
       (17, 63),
       (31, 62),
       (52, 33),
       (51, 21),
       (42, 41),
       (31, 32),
       (5, 25),
       (12, 42),
       (36, 16),
       (52, 41),
       (27, 23),
       (17, 33),
       (13, 13),
       (57, 58),
       (62, 42),
       (42, 57),
       (16, 57),
       (8, 52),
       (7, 38),
       (27, 68),
       (30, 48),
       (43, 67),
       (58, 48),
       (58, 27),
       (37, 69),
       (38, 46),
       (46, 10),
       (61, 33),
       (62, 63),
       (63, 69),
       (32, 22),
       (45, 35),
       (59, 15),
       (5, 6),
       (10, 17),
       (21, 10),
       (5, 64),
       (30, 15),
       (39, 10),
       (32, 39),
       (25, 32),
       (25, 55),
       (48, 28),
       (56, 37),
       (30, 40)]
cities = np.array(pos)

city_count = len(cities)  # 城市数量
Distance = np.zeros([city_count, city_count])  # i到j的距离矩阵
for i in range(city_count):
    for j in range(city_count):
        # Distance[i][j]=abs((cities[i][0] - cities[j][0]))+abs(cities[i][1] - cities[j][1])  #曼哈顿距离
        Distance[i][j] = math.sqrt(((cities[i][0] - cities[j][0]) ** 2 + (cities[i][1] - cities[j][1]) ** 2))  # 欧氏距离
# 问题参数
# 起点城市

origin = 0
# 车辆数
m = 7
# GA参数
# 种群数
count = 40
# 强者存活率
retain_rate = 0.85
# 弱者存活概率
random_rate = 0.15
# 变异率
mutation_rate = 0.1
# 迭代次数
item_time = 10000

# 生成初始个体
def individual():
    index = [i for i in range(city_count)]
    index.remove(origin)
    a = int(np.floor(len(index) / m))
    X = []
    for i in range(m):
        if i < m - 1:
            x = index[a * i:a * (i + 1)]
        else:
            x = index[a * i:]
        X.append(x)
    return X


# 路线总距离
def get_total_distance(X):
    distance = 0
    distance_list = []
    for i in range(len(X)):
        x = X[i]
        distance += Distance[origin][x[0]]
        distance += Distance[origin][x[-1]]
        for i in range(len(x) - 1):
            distance += Distance[x[i]][x[i + 1]]
        distance_list.append(distance)
    sum_Distance = np.sum(distance)
    return sum_Distance, distance_list


# 选择函数
def selection(population):
    graded = [[get_total_distance(x)[0], x] for x in population]
    graded = [x[1] for x in sorted(graded)]
    retain_length = int(len(graded) * retain_rate)
    parents = graded[:retain_length]
    for chromosome in graded[retain_length:]:
        if random.random() < random_rate:
            parents.append(chromosome)
    return parents


# 交叉繁殖
def crossover(parents):
    # 还需要的子代个数，保持总数count稳定
    target_count = count - len(parents)
    # 子代列表
    children = []
    while len(children) < target_count:
        # 父母代索引
        male_index = random.randint(0, len(parents) - 1)
        female_index = random.randint(0, len(parents) - 1)
        if male_index != female_index:
            # 父母代个体
            male = parents[male_index]
            female = parents[female_index]
            # 染色单体索引
            gene1 = []
            gene2 = []
            for i in range(len(male)):
                gene1 += male[i]
                gene2 += female[i]
            # 交叉规则
            left = random.randint(0, len(gene1) / 2)
            right = random.randint(left + 1, len(gene1))
            cut = gene1[left:right]
            copy = gene2.copy()
            for j in cut:
                copy.remove(j)

            child = copy + cut
            a = int(np.floor(len(child) / m))
            child_c = []
            for i in range(m):
                if i < m - 1:
                    x = child[a * i:a * (i + 1)]
                else:
                    x = child[a * i:]
                child_c.append(x)
            children.append(child_c)
    return children


# 变异
def mutation(children):
    for i in range(len(children)):
        if random.random() < mutation_rate:
            child = children[i]
            for j in range(int(np.floor(len(child) / 2))):
                a = 2 * j
                u = random.randint(1, len(child[a]) - 1)
                w = random.randint(1, len(child[a + 1]) - 1)
                child_1 = child[a][:u].copy()
                child_2 = child[a][u:].copy()
                child_3 = child[a + 1][:w].copy()
                child_4 = child[a + 1][w:].copy()
                child_a = child_1 + child_3
                child_b = child_2 + child_4
                child[a] = child_a
                child[a + 1] = child_b
            children[i] = child.copy()
        return children


# 获得最好结果
def get_result(population):
    graded = [[get_total_distance(x)[0], x] for x in population]
    graded = sorted(graded, key=lambda x: x[0])
    return graded[0][0], graded[0][1]


population = []
for i in range(count):
    population.append(individual())
i = 0
distance_list = []
result_path_list = []
while i < item_time:
    parents = selection(population)
    children = crossover(parents)
    children = mutation(children)
    population = parents + children
    distance, result_path = get_result(population)
    distance_list.append(distance)
    result_path_list.append(result_path)
    i = i + 1
print("Result Path:", result_path_list)
print("Best times: ", distance_list[-1])
print("Elapse times:", time.time() - start, "s")
final_result_path = result_path_list[-1]

# save critical path
with open("../Critical_Path/critical_path.txt", 'w') as f:
    for i in range(len(final_result_path)):
        line = str(final_result_path[i])
        line = line.strip('[]')
        line = line.replace(',', '')
        f.write(line)
        f.write("\n")
print("GA's final critical path has been saved.")

# path
# colors = ['gray', 'yellow', 'pink', 'orange', 'red', 'green', 'purple']
# G = nx.DiGraph()
# p = {}
# path = []
# _, ax = plt.subplots()
# for i in range(city_count):
#     G.add_node(i, desc=str(i+1))
#     p[i] = cities[i]
# for i in range(m):
#     each_path = []
#     for j in range(len(result_path_list[-1][i])):
#         if j == 0:
#             G.add_edge(0, result_path_list[-1][i][j])
#             each_path.append((0, result_path_list[-1][i][j]))
#         elif j == len(result_path_list[-1][i]) - 1:
#             G.add_edge(result_path_list[-1][i][j], 0)
#             G.add_edge(result_path_list[-1][i][j - 1], result_path_list[-1][i][j])
#             each_path.append((result_path_list[-1][i][j-1], result_path_list[-1][i][j]))
#             each_path.append((result_path_list[-1][i][j], 0))
#         else:
#             G.add_edge(result_path_list[-1][i][j - 1], result_path_list[-1][i][j])
#             each_path.append((result_path_list[-1][i][j-1], result_path_list[-1][i][j]))
#     path.append(each_path)
#
# # 按pos所定位置画出节点,无标签无权值
# nx.draw_networkx(G, pos=p, with_labels=None)
# # 画出标签
# node_labels = nx.get_node_attributes(G, 'desc')
# nx.draw_networkx_labels(G, pos=p, labels=node_labels, font_color='white')
# nx.draw_networkx_nodes(G, pos=p, ax=ax, node_color=(0.4157, 0.3529, 0.3490))
# for i in range(m):
#     nx.draw_networkx_edges(G, pos=p, edgelist=path[i], arrows=True, edge_color=colors[i])
# ax.tick_params(left=True, bottom=True, labelleft=True, labelbottom=True)
# plt.show()

# fitness
# plt.plot(np.linspace(0, len(distance_list) - 1, item_time), distance_list)
# plt.show()
