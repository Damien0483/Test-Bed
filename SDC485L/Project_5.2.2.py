# --------------------------------------------
# Train/test split (same as Part 4)
# --------------------------------------------
X_train, X_test, y_train, y_test = train_test_split(
    X, y,
    test_size=0.2,
    random_state=42,
    stratify=y
)

# --------------------------------------------
# Recreate optimized Gradient Boosting model
# (use your actual best_params_ from Part 4)
# --------------------------------------------
best_params_gb = {
    "n_estimators": 100,
    "learning_rate": 0.05,
    "max_depth": 2,
    "subsample": 0.8
    # adjust to match your GridSearchCV results
}

gb_opt = GradientBoostingClassifier(
    random_state=42,
    **best_params_gb
)

gb_opt.fit(X_train, y_train)
