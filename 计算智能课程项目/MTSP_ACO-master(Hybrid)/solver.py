from collections import defaultdict


class Solver:

    def __init__(self, rho=0.5, q=1, top=None):
        self.rho = rho
        self.q = q
        self.top = top

    def solve(self, *args, **kwargs):
        result_solution = []
        best = None
        for solution in self.optimize(*args, **kwargs):
            if best is None:
                best = solution
            elif sum([s.cost for s in best]) > sum([s.cost for s in solution]):
                best = solution
            result_solution.append(best)
        return best, result_solution

    def optimize(self, graph, colony, sales, start=1, gen_size=None, limit=50, opt2=None):
        gen_size = gen_size or len(graph.nodes)
        ants = colony.get_ants(gen_size)

        for u, v in graph.edges:
            weight = graph.edges[u, v]['weight']
            if weight == 0:
                weight = 1e100
            graph.edges[u, v].setdefault('pheromone', 1 / weight)

        # TODO:Use GA's critical path to initial the pheromone
        with open("../Critical_Path/critical_path.txt", 'r') as f:
            for line in f:
                line_list = line.split()
                critical_path = [int(i) + 1 for i in line_list]
                for i in range(len(critical_path)):
                    if i == 0:
                        graph.edges[1, critical_path[i]].\
                            setdefault('pheromone', 10 / graph.edges[1, critical_path[i]]['weight'])
                    elif i == len(critical_path) - 1:
                        graph.edges[critical_path[i], 1]. \
                            setdefault('pheromone', 10 / graph.edges[1, critical_path[i]]['weight'])
                        graph.edges[critical_path[i - 1], critical_path[i]]. \
                            setdefault('pheromone', 10 / graph.edges[1, critical_path[i]]['weight'])
                    else:
                        graph.edges[critical_path[i - 1], critical_path[i]]. \
                            setdefault('pheromone', 10 / graph.edges[1, critical_path[i]]['weight'])

        for _ in range(limit):
            sales_solutions = self.find_solutions(graph, ants, sales, start, opt2)
            for solutions in sales_solutions:
                solutions.sort()
            sales_solutions.sort(key=lambda x: sum([y.cost for y in x]))
            self.global_update(sales_solutions, graph)

            yield sales_solutions[0]

    def find_solutions(self, graph, ants, sales, start, opt2):
        return [ant.tour(graph, sales, start, opt2) for ant in ants]

    def global_update(self, sales_solutions, graph):
        next_pheromones = defaultdict(float)
        for solutions in sales_solutions:
            cost = sum([solution.cost for solution in solutions])
            for solution in solutions:
                for path in solution:
                    next_pheromones[path] += 1 / cost

        for edge in graph.edges:
            p = graph.edges[edge]['pheromone']
            graph.edges[edge]['pheromone'] = p * (1 - self.rho) + next_pheromones[edge]
