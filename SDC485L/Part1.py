# ============================================================
# 1. Imports
# ============================================================
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

from sklearn.preprocessing import StandardScaler
from sklearn.decomposition import PCA

# Make plots pretty
sns.set(style="whitegrid")
plt.rcParams["figure.figsize"] = (8,5)

# ============================================================
# 2. Load dataset
# ============================================================
df = pd.read_csv("penguins.csv")

# ============================================================
# 3. Convert numeric columns & clean missing values
# ============================================================
num_cols = ["bill_length_mm","bill_depth_mm","flipper_length_mm","body_mass_g","year"]

# Convert to numeric, coerce errors
df[num_cols] = df[num_cols].apply(pd.to_numeric, errors="coerce")

# Show missing counts
print("Missing values per column:")
print(df.isna().sum())

# ============================================================
# 4. Summary Statistics
# ============================================================
print("\nSummary Statistics:")
print(df[num_cols].describe())

# ============================================================
# 5. Histograms
# ============================================================
for col in num_cols:
    plt.figure()
    sns.histplot(df[col], kde=True)
    plt.title(f"Histogram: {col}")
    plt.show()

# ============================================================
# 6. Boxplots (Grouped by Species)
# ============================================================
for col in num_cols:
    plt.figure()
    sns.boxplot(x="species", y=col, data=df)
    plt.title(f"Boxplot of {col} by Species")
    plt.show()

# ============================================================
# 7. Correlation Matrix + Heatmap
# ============================================================
corr = df[num_cols].corr()
print("\nCorrelation Matrix:")
print(corr)

plt.figure(figsize=(10,8))
sns.heatmap(corr, annot=True, cmap="coolwarm")
plt.title("Correlation Heatmap")
plt.show()

# ============================================================
# 8. Pairplot (for clustering exploration)
# ============================================================
sns.pairplot(df, vars=num_cols, hue="species")
plt.show()

# ============================================================
# 9. Preprocessing for Neural Network
# ============================================================

# ---- Drop rows with missing numeric values ----
df_clean = df.dropna(subset=num_cols)
print("\nRows before cleaning:", len(df))
print("Rows after cleaning:", len(df_clean))

# ---- One-hot encode categorical features ----
df_encoded = pd.get_dummies(df_clean, columns=["species","island","sex"], dummy_na=True)

# ---- Standardize numeric features ----
scaler = StandardScaler()
scaled_numeric = scaler.fit_transform(df_encoded[num_cols])

# Replace numeric columns with scaled versions
df_encoded[num_cols] = scaled_numeric

# ============================================================
# 10. PCA (optional)
# ============================================================
pca = PCA(n_components=2)
pca_components = pca.fit_transform(df_encoded[num_cols])

pca_df = pd.DataFrame(pca_components, columns=["PC1","PC2"])
pca_df["species"] = df_clean["species"].values

# PCA scatter plot
plt.figure(figsize=(8,6))
sns.scatterplot(data=pca_df, x="PC1", y="PC2", hue="species", palette="deep")
plt.title("PCA Projection (2 Components)")
plt.show()

# ============================================================
# Final processed dataset ready for NN
# ============================================================
print("\nProcessed dataset shape:", df_encoded.shape)
