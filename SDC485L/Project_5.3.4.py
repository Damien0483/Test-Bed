# --------------------------------------------
# Optimized model evaluation on original test set
# --------------------------------------------
y_pred = gb_opt.predict(X_test)
y_proba = gb_opt.predict_proba(X_test)

acc = accuracy_score(y_test, y_pred)
f1_w = f1_score(y_test, y_pred, average="weighted")

print("Optimized Gradient Boosting Accuracy:", acc)
print("Optimized Weighted F1-score:", f1_w)
print("\nClassification report:")
print(classification_report(y_test, y_pred))

cm = confusion_matrix(y_test, y_pred)
print("Confusion matrix:\n", cm)

# --------------------------------------------
# Baseline prediction summary for tracked class
# --------------------------------------------
n_test = len(y_test)
pred_target_mask = (y_pred == target_class)
n_pred_target = pred_target_mask.sum()
pct_pred_target = n_pred_target / n_test

# Find column index for target_class in predict_proba
class_index = list(gb_opt.classes_).index(target_class)
avg_target_proba = y_proba[:, class_index].mean()

print("\nBaseline prediction summary:")
print("Number of test records:", n_test)
print("Number predicted as target class:", n_pred_target)
print("Percentage predicted as target class:", pct_pred_target)
print("Average predicted probability for target class:", avg_target_proba)
