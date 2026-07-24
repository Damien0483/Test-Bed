# Initialize and train the model
xgb_initial = XGBRegressor(objective="reg:squarederror", random_state=42)
xgb_initial.fit(X_train, y_train)

# Predict on testing data
y_pred_initial = xgb_initial.predict(X_test)

# Evaluate model
initial_mse = mean_squared_error(y_test, y_pred_initial)
initial_r2 = r2_score(y_test, y_pred_initial)

print(f"Initial Model Mean Squared Error (MSE): {initial_mse:.4f}")
print(f"Initial Model R-squared Score: {initial_r2:.4f}")
