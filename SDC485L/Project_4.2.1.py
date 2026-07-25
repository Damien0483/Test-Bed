# ============================================
# Imports
# ============================================
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

from sklearn.model_selection import train_test_split, GridSearchCV
from sklearn.metrics import (
    classification_report,
    confusion_matrix,
    accuracy_score,
    f1_score,
    silhouette_score
)
from sklearn.neighbors import KNeighborsClassifier
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import GradientBoostingClassifier
from sklearn.preprocessing import StandardScaler
from sklearn.cluster import KMeans
from sklearn.decomposition import PCA
from scipy.cluster.hierarchy import linkage, dendrogram

sns.set(style="whitegrid")
plt.rcParams["figure.figsize"] = (8, 5)

# ============================================
# 1. Load cleaned/preprocessed dataset (from Part 3)
# ============================================
# If you saved them in Part 3:
# df_clean: original cleaned data with species
# df_encoded: numeric + one-hot encoded features

df_clean = pd.read_csv("penguins_clean.csv")      # contains original columns, including 'species'
df_encoded = pd.read_csv("penguins_encoded.csv")  # contains numeric + one-hot encoded features

# ============================================
# 2. Identify features and target for classification
# ============================================
y = df_clean["species"]  # target: species
X = df_encoded.drop(columns=["year"])  # features: all encoded + numeric (excluding year if desired)

print("Classification features shape:", X.shape)
print("Target shape:", y.shape)

# ============================================
# 3. Numerical features for clustering
# ============================================
num_features = ["bill_length_mm", "bill_depth_mm", "flipper_length_mm", "body_mass_g"]
X_clust_raw = df_clean[num_features].dropna()

print("Clustering numeric features shape:", X_clust_raw.shape)

# ============================================
# 4. Recreate train/test split (same random_state, stratify=y)
# ============================================
X_train, X_test, y_train, y_test = train_test_split(
    X, y,
    test_size=0.2,
    random_state=42,
    stratify=y
)

print("Train size:", X_train.shape[0])
print("Test size:", X_test.shape[0])

# ============================================
# 5. Recreate original classification models (baseline)
# ============================================

# KNN baseline
knn = KNeighborsClassifier(n_neighbors=5, weights="uniform")
knn.fit(X_train, y_train)
y_pred_knn = knn.predict(X_test)

# Decision Tree baseline
dt = DecisionTreeClassifier(random_state=42)
dt.fit(X_train, y_train)
y_pred_dt = dt.predict(X_test)

# Gradient Boosting baseline
gb = GradientBoostingClassifier(random_state=42)
gb.fit(X_train, y_train)
y_pred_gb = gb.predict(X_test)

# Record baseline metrics
def evaluate_model(name, y_true, y_pred):
    acc = accuracy_score(y_true, y_pred)
    f1_w = f1_score(y_true, y_pred, average="weighted")
    print(f"\n=== {name} ===")
    print(f"Accuracy: {acc:.4f}")
    print(f"Weighted F1-score: {f1_w:.4f}")
    print("Classification report:")
    print(classification_report(y_true, y_pred))
    cm = confusion_matrix(y_true, y_pred)
    print("Confusion matrix:\n", cm)
    return acc, f1_w, cm

acc_knn, f1_knn, cm_knn = evaluate_model("KNN (baseline)", y_test, y_pred_knn)
acc_dt, f1_dt, cm_dt = evaluate_model("Decision Tree (baseline)", y_test, y_pred_dt)
acc_gb, f1_gb, cm_gb = evaluate_model("Gradient Boosting (baseline)", y_test, y_pred_gb)

# You can use these printed metrics for your baseline screenshot.
