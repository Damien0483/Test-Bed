# ============================================
# 14. Select final k (based on elbow + silhouette)
# ============================================
k_opt = 3  # example; set based on your analysis of the plots

kmeans_opt = KMeans(n_clusters=k_opt, random_state=42, n_init=20)
labels_opt = kmeans_opt.fit_predict(X_clust_pca)

inertia_opt = kmeans_opt.inertia_
sil_opt = silhouette_score(X_clust_pca, labels_opt)

print("\nOptimized K-Means clustering (PCA features):")
print("Final number of clusters:", k_opt)
print("Inertia:", inertia_opt)
print("Silhouette Score:", sil_opt)

# ============================================
# 15. Comparison table (original vs optimized clustering)
# ============================================
clust_comparison_df = pd.DataFrame({
    "Clustering Model": ["Original", "Optimized"],
    "Features Used": ["Standardized original features", "PCA-transformed features"],
    "Number of Clusters": [k_baseline, k_opt],
    "Inertia": [inertia_baseline, inertia_opt],
    "Silhouette Score": [sil_baseline, sil_opt]
})

print("\nClustering comparison:")
print(clust_comparison_df)

# ============================================
# 16. Visualize final clusters (first two PCs)
# ============================================
# Use first two principal components for scatterplot
pc1 = X_clust_pca[:, 0]
pc2 = X_clust_pca[:, 1]

centroids = kmeans_opt.cluster_centers_

plt.figure(figsize=(8, 6))
scatter = plt.scatter(pc1, pc2, c=labels_opt, cmap="viridis", alpha=0.7, label="Points")
plt.scatter(centroids[:, 0], centroids[:, 1], c="red", s=200, marker="X", label="Centroids")
plt.xlabel("PC1")
plt.ylabel("PC2")
plt.title("Optimized K-Means Clusters (PCA 2D Projection)")
plt.legend()
plt.show()

# ============================================
# 17. Cluster profiles in original feature space
# ============================================
clust_profile_df = X_clust_raw.copy()
clust_profile_df["cluster"] = labels_opt

cluster_means = clust_profile_df.groupby("cluster").mean()
print("\nCluster profiles (mean original features):")
print(cluster_means)
