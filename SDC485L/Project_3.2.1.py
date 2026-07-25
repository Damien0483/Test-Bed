# ============================================
# 1. Identify features and target (classification)
# ============================================

# Target: species (multiclass classification)
y_cls = df_clean["species"]   # Adelie / Gentoo / Chinstrap

# Features: all encoded columns except year (optional) and any target-like columns
X_cls = df_encoded.drop(columns=["year"])  # keep all numeric + one-hot features

print("Classification features shape:", X_cls.shape)
print("Target shape:", y_cls.shape)

# ============================================
# 2. Numerical features for clustering
# ============================================

num_features = ["bill_length_mm", "bill_depth_mm", "flipper_length_mm", "body_mass_g"]
X_clust_raw = df_clean[num_features].dropna()

print("Clustering numeric features shape:", X_clust_raw.shape)

# ============================================
# 3. Train/test split for classification
# ============================================
from sklearn.model_selection import train_test_split

X_train, X_test, y_train, y_test = train_test_split(
    X_cls, y_cls, test_size=0.2, random_state=42, stratify=y_cls
)

print("Train size:", X_train.shape[0])
print("Test size:", X_test.shape[0])
