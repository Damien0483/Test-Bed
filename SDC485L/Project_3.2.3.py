from sklearn.model_selection import GridSearchCV

# ============================================
# 7. GridSearchCV for KNN
# ============================================
param_grid = {
    "n_neighbors": [3,5,7,9,11],
    "weights": ["uniform", "distance"]
}

knn_base = KNeighborsClassifier()
grid_search = GridSearchCV(
    knn_base,
    param_grid,
    cv=5,
    scoring="accuracy",
    n_jobs=-1
)

grid_search.fit(X_train, y_train)

print("Best KNN parameters:", grid_search.best_params_)
print("Best CV accuracy:", grid_search.best_score_)

# Evaluate tuned KNN on test set
knn_best = grid_search.best_estimator_
y_pred_knn_best = knn_best.predict(X_test)

print("=== Tuned KNN Classification Report ===")
print(classification_report(y_test, y_pred_knn_best))

cm_knn_best = confusion_matrix(y_test, y_pred_knn_best)
plt.figure()
sns.heatmap(cm_knn_best, annot=True, fmt="d", cmap="Purples")
plt.title("Tuned KNN Confusion Matrix")
plt.xlabel("Predicted")
plt.ylabel("True")
plt.show()
