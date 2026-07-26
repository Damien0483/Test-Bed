
# --------------------------------------------
# Bar chart: mean prediction rate with std error bars
# --------------------------------------------
plt.figure(figsize=(8, 6))
plt.bar(summary["Condition"], summary["Mean_Prediction_Rate"],
        yerr=summary["Std_Prediction_Rate"], capsize=5)
plt.title("Mean Target-Class Prediction Rate by Condition")
plt.xlabel("Condition")
plt.ylabel("Mean Prediction Rate")
plt.show()
