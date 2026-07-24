# Define parameter grid
param_grid = {
    "n_estimators": [50, 100],
    "learning_rate": [0.05, 0.1, 0.2],
    "max_depth": [2, 3, 4]
}

# Configure and fit GridSearchCV
grid_search = GridSearchCV(
    estimator=XGBRegressor(objective="reg:squarederror", random_state=42),
    param_grid=param_grid,
    scoring='neg_mean_squared_error',
    cv=5,
    n_jobs=-1
)

grid_search.fit(X_train, y_train)

# Display best parameters and CV score
print("Best Hyperparameters:", grid_search.best_params_)
# Note: neg_mean_squared_error returns negative values, so we multiply by -1 for the actual MSE
print(f"Best Cross-Validation Score (MSE): {-grid_search.best_score_:.4f}")

# Predict using the best model
best_xgb_model = grid_search.best_estimator_
y_pred_tuned = best_xgb_model.predict(X_test)

# Evaluate tuned model
tuned_mse = mean_squared_error(y_test, y_pred_tuned)
tuned_r2 = r2_score(y_test, y_pred_tuned)

# Compare results
comparison_df = pd.DataFrame({
    'Metric': ['Mean Squared Error (MSE)', 'R-squared (R2)'],
    'Initial Model': [initial_mse, initial_r2],
    'Tuned Model': [tuned_mse, tuned_r2]
})
print("\nModel Comparison:")
display(comparison_df)
