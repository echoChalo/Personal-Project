import tsplib95
import matplotlib.pyplot as plt
import networkx as nx
import random
import time
import numpy as np

from colony import Colony
from solver import Solver

problem = tsplib95.load_problem('data/eil51.tsp')
start = time.time()
G = problem.get_graph()

solver = Solver()
colony = Colony(1, 1)
sales = 7
iteration = 500

ans, result = solver.solve(G, colony, sales, start=1, limit=iteration, opt2=20)
print(sum(s.cost for s in ans), ans)
print("Elapse time: ", time.time()-start, "s")

# 获取适应度矩阵
fitness = []
for i in range(iteration):
    fitness.append(sum(r.cost for r in result[i]))

# draw
colors = ['gray', 'yellow', 'pink', 'orange', 'red', 'green', 'purple']
_, ax = plt.subplots()
pos = problem.display_data or problem.node_coords
nx.draw_networkx_nodes(G, pos=pos, ax=ax, node_color=(0.4157, 0.3529, 0.3490))
nx.draw_networkx_labels(G, pos=pos, labels={i: str(i) for i in range(1, len(G.nodes) + 1)}, font_size=8, font_color='white')
for i in range(len(ans)):
    solution = ans[i]
    path = solution.path
    nx.draw_networkx_edges(G, pos=pos, edgelist=path, arrows=True, edge_color=colors[i])
    # nx.draw_networkx_edges(G, pos=pos, edgelist=path, arrows=True, edge_color=[random.random() for i in range(3)])

# If this doesn't exsit, x_axis and y_axis's numbers are not there.
ax.tick_params(left=True, bottom=True, labelleft=True, labelbottom=True)
plt.show()

# fitness
plt.plot(np.linspace(0, len(fitness) - 1, iteration), fitness)
plt.show()
