import numpy as np
import matplotlib.pyplot as plt
from sklearn.decomposition import PCA

# 单词列表
docs = ['cat', 'and', 'mice', 'are', 'buddies', 'hole', 'lives', 'in',
        'house', 'chases', 'catches', 'runs', 'into', 'says', 'bad',
        'words', 'pals', 'chums', 'stores', 'sleeps']

# 为每个单词生成随机向量（例如，10维）
np.random.seed(42)  # 为了可重复性
random_embeddings = np.random.rand(len(docs), 10)

# 执行PCA以将维度减少到2D以便可视化
pca = PCA(n_components=2)
reduced_embeddings = pca.fit_transform(random_embeddings)

# 绘制二维嵌入
plt.figure(figsize=(10, 8))
for i, word in enumerate(docs):
    plt.scatter(reduced_embeddings[i, 0], reduced_embeddings[i, 1], marker='o')
    plt.text(reduced_embeddings[i, 0] + 0.01, reduced_embeddings[i, 1] + 0.01, word, fontsize=12)

plt.title("随机词向量的二维可视化")
plt.xlabel("主成分 1")
plt.ylabel("主成分 2")
plt.grid(True)
plt.show()
