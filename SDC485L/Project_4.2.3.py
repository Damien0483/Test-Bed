# ============================================
# 11. Baseline clustering (same features and k as Part 3)
# ============================================

# Standardize original clustering features
scaler_clust = StandardScaler()
X_clust = scaler_clust.fit_transform(X_clust_raw)

# Suppose k_final from Part 3 was 3 (adjust if you used a different k)
k_baseline = 3
kmeans_baseline = KMeans(n_clusters=k_baseline, random_state=42, n_init=10)
labels_baseline = kmeans_baseline.fit_predict(X_clust)

inertia_baseline = kmeans_baseline.inertia_
sil_baseline = silhouette_score(X_clust, labels_baseline)

print("\nBaseline K-Means clustering:")
print("Number of clusters:", k_baseline)
print("Inertia:", inertia_baseline)
print("Silhouette Score:", sil_baseline)

# ============================================
# 12. PCA on standardized clustering features
# ============================================
pca = PCA()
X_clust_pca_full = pca.fit_transform(X_clust)

explained_var = pca.explained_variance_ratio_
cum_explained = np.cumsum(explained_var)

# Choose number of components to retain at least 90% variance
n_components_90 = np.argmax(cum_explained >= 0.90) + 1

print("\nPCA explained variance ratio:", explained_var)
print("Cumulative explained variance:", cum_explained)
print("Number of components to retain >= 90% variance:", n_components_90)

# Use that many components
pca_opt = PCA(n_components=n_components_90)
X_clust_pca = pca_opt.fit_transform(X_clust)

# ============================================
# 13. Reevaluate k (2–10) with Elbow + Silhouette on PCA data
# ============================================
k_values = range(2, 10)
inertias_pca = []
sil_scores_pca = []

for k in k_values:
    km = KMeans(n_clusters=k, random_state=42, n_init=20)
    labels = km.fit_predict(X_clust_pca)
    inertias_pca.append(km.inertia_)
    sil = silhouette_score(X_clust_pca, labels)
    sil_scores_pca.append(sil)
    print(f"k={k}, inertia={km.inertia_:.2f}, silhouette={sil:.3f}")

# Elbow Method plot
plt.figure()
plt.plot(k_values, inertias_pca, marker="o")
plt.title("Elbow Method (PCA-transformed features)")
plt.xlabel("k")
plt.ylabel("Inertia")
plt.show()

# Silhouette Score plot
plt.figure()
plt.plot(k_values, sil_scores_pca, marker="o")
plt.title("Silhouette Scores (PCA-transformed features)")
plt.xlabel("k")
plt.ylabel("Silhouette Score")
plt.show()

# You’ll interpret:
# - k suggested by elbow (where inertia curve bends)
# - k with highest silhouette score
