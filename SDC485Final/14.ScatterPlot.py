plt.figure(figsize=(8, 8))
plt.scatter(y_test, y_pred_tuned, alpha=0.7, color='teal')

# Add diagonal reference line
max_val = max(max(y_test), max(y_pred_tuned))
plt.plot([0, max_val], [0, max_val], color='red', linestyle='--', linewidth=2, label='Perfect Prediction')

plt.title('Actual vs. Predicted Average Customer Wait Times', fontsize=14)
plt.xlabel('Actual Average Wait Time', fontsize=12)
plt.ylabel('Predicted Average Wait Time', fontsize=12)
plt.legend()
plt.grid(True)
plt.show()
