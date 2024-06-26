# 混合遗传蚁群算法求解多旅行商问题

多旅行商问题与旅行商问题一样，可以利用遗传算法、蚁群算法、模拟退火算法、粒子群算法等算法进行求解。针对蚁群算法在求解问题过程中对信息素的强依赖，以及遗传算法全局搜索能力强且收敛速度慢的特点，使用将遗传算法与蚁群算法混合的策略进行求解多旅行商问题。该策略先利用遗传算法在全局范围内搜索较优解，以此为基础，再利用蚁群算法进一步优化得出最优解。分别采用了遗传算法（Genetic Algorithm, GA）、蚁群算法（Ant Colony Algorithm, ACA）以及混合遗传蚁群算法（Hybrid Genetic Algorithm and Ant Colony Algorithm. GA-ACA）对Benchmark基准上的4个不同人数的多旅行商问题进行了求解，并记录了10组测试数据通过求其平均值以及标准差进行比较分析，验证了混合遗传蚁群算法在多旅行商问题上的正确性和有效性。



## 算法流程图
<p align="center"><img src="./picture/算法流程图.png"></p>

## 源码来源
[BRP](https://github.com/HonghuiZou/BRP)
[MTSP_ACO](https://github.com/ganyariya/MTSP_ACO)
