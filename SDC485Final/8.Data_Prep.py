# Select predictors and target
X = simulation_results[["Employees", "Interarrival_Time"]]
y = simulation_results["Average_Wait_Time"]

# Split the data (80% training, 20% testing)
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.20, random_state=42)

print(f"Training data shape (X, y): {X_train.shape}, {y_train.shape}")
print(f"Testing data shape (X, y): {X_test.shape}, {y_test.shape}")
