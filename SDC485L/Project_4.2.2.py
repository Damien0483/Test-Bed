# ============================================
# 6. Baseline performance for chosen model (Gradient Boosting)
# ============================================
baseline_acc = acc_gb
baseline_f1 = f1_gb
print("\nBaseline Gradient Boosting Accuracy:", baseline_acc)
print("Baseline Gradient Boosting Weighted F1:", baseline_f1)

# ============================================
# 7. Hyperparameter tuning with GridSearchCV
# ============================================
param_grid_gb = {
    "n_estimators": [50, 100, 200],
    "learning_rate": [0.01, 0.05, 0.1, 0.2],
    "max_depth": [1, 2, 3],
    "subsample": [0.8, 1.0]
}

gb_base = GradientBoostingClassifier(random_state=42)

grid_gb = GridSearchCV(
    gb_base,
    param_grid_gb,
    cv=5,
    scoring="accuracy",
    n_jobs=-1
)

grid_gb.fit(X_train, y_train)

print("\nBest Gradient Boosting hyperparameters:", grid_gb.best_params_)
print("Best CV accuracy:", grid_gb.best_score_)

# ============================================
# 8. Overfitting control (explained in markdown)
# ============================================
# Example: using smaller max_depth, lower learning_rate, subsample < 1.0
# These hyperparameters help reduce overfitting by limiting model complexity
# and introducing stochasticity (subsample).

# ============================================
# 9. Retrain optimized model with best hyperparameters
# ============================================
gb_opt = grid_gb.best_estimator_
gb_opt.fit(X_train, y_train)
y_pred_gb_opt = gb_opt.predict(X_test)

opt_acc = accuracy_score(y_test, y_pred_gb_opt)
opt_f1 = f1_score(y_test, y_pred_gb_opt, average="weighted")

print("\n=== Optimized Gradient Boosting ===")
print(f"Accuracy: {opt_acc:.4f}")
print(f"Weighted F1-score: {opt_f1:.4f}")
print("Classification report:")
print(classification_report(y_test, y_pred_gb_opt))

cm_gb_opt = confusion_matrix(y_test, y_pred_gb_opt)
print("Confusion matrix:\n", cm_gb_opt)

# ============================================
# 10. Comparison table (original vs optimized)
# ============================================
comparison_df = pd.DataFrame({
    "Model": ["Original Gradient Boosting", "Optimized Gradient Boosting"],
    "Accuracy": [baseline_acc, opt_acc],
    "Weighted F1-score": [baseline_f1, opt_f1]
})

print("\nOriginal vs Optimized Gradient Boosting:")
print(comparison_df)
