# Final Project: Simulation-Based Impact Analysis
# <Your Name>
# <The Date>
# <Your Student ID>

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

from sklearn.model_selection import train_test_split, GridSearchCV
from sklearn.metrics import (
    classification_report,
    confusion_matrix,
    accuracy_score,
    f1_score
)
from sklearn.ensemble import GradientBoostingClassifier
from sklearn.preprocessing import StandardScaler
from sklearn.decomposition import PCA
from sklearn.cluster import KMeans

sns.set(style="whitegrid")
plt.rcParams["figure.figsize"] = (8, 5)

# --------------------------------------------
# Load cleaned dataset and encoded features
# --------------------------------------------
df_clean = pd.read_csv("penguins_clean.csv")      # includes species, numeric features
df_encoded = pd.read_csv("penguins_encoded.csv")  # numeric + one-hot encoded features

# Classification features and target
y = df_clean["species"]
X = df_encoded.drop(columns=["year"])  # same feature set as Part 4

# Numerical features for clustering / simulation
num_features = ["bill_length_mm", "bill_depth_mm", "flipper_length_mm", "body_mass_g"]

# Target class to track (multiclass -> choose one)
target_class = "Gentoo"  # explain choice in markdown
