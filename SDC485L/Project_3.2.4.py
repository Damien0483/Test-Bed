from sklearn.preprocessing import StandardScaler
from sklearn.cluster import KMeans
from sklearn.metrics import silhouette_score
from scipy.cluster.hierarchy import linkage, dendrogram

# ============================================
# 8. Standardize clustering features
# ============================================
scaler_clust = StandardScaler()
X_clust = scaler_clust.fit_transform(X_clust_raw)

# ============================================
# 9. Hierarchical clustering + dendrogram
# ============================================
Z = linkage(X_clust, method="ward")

plt.figure(figsize=(10, 6))
dendrogram(Z)
plt.title("Hierarchical Clustering Dendrogram")
plt.xlabel("Samples")
plt.ylabel("Distance")
plt.show()

# ============================================
# 10. K-Means: Elbow Method
# ============================================
inertias = []
k_values = range(2, 10)

for k in k_values:
    km = KMeans(n_clusters=k, random_state=42, n_init="auto")
    km.fit(X_clust)
    inertias.append(km.inertia_)

plt.figure()
plt.plot(k_values, inertias, marker="o")
plt.title("Elbow Method for K-Means")
plt.xlabel("k")
plt.ylabel("Inertia")
plt.show()

# ============================================
# 11. Silhouette Scores
# ============================================
sil_scores = []

for k in k_values:
    km = KMeans(n_clusters=k, random_state=42, n_init="auto")
    labels = km.fit_predict(X_clust)
    sil = silhouette_score(X_clust, labels)
    sil_scores.append(sil)
    print(f"k={k}, silhouette={sil:.3f}")

plt.figure()
plt.plot(k_values, sil_scores, marker="o")
plt.title("Silhouette Scores vs k")
plt.xlabel("k")
plt.ylabel("Silhouette Score")
plt.show()

# ============================================
# 12. Final K-Means model (choose k based on elbow + silhouette)
# ============================================
k_final = 3  # example; set based on your analysis
kmeans_final = KMeans(n_clusters=k_final, random_state=42, n_init="auto")
cluster_labels = kmeans_final.fit_predict(X_clust)

# Add cluster labels back to a DataFrame for interpretation
clust_df = X_clust_raw.copy()
clust_df["cluster"] = cluster_labels

print(clust_df.groupby("cluster").mean())

# ============================================
# 13. Cluster visualization (using two features or PCA)
# ============================================
pca_clust = PCA(n_components=2)
X_clust_pca = pca_clust.fit_transform(X_clust)

pca_clust_df = pd.DataFrame(X_clust_pca, columns=["PC1","PC2"])
pca_clust_df["cluster"] = cluster_labels

plt.figure(figsize=(8,6))
sns.scatterplot(data=pca_clust_df, x="PC1", y="PC2", hue="cluster", palette="deep")
plt.title("K-Means Clusters (PCA 2D Projection)")
plt.show()
