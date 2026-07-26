plt.figure(figsize=(10, 6))

# Line chart with error bars
plt.errorbar(
    sens_summary["Feature_Value"],
    sens_summary["Mean_Prediction_Rate"],
    yerr=sens_summary["Std_Prediction_Rate"],
    marker="o",
    linestyle="-",
    linewidth=2,
    capsize=5,
    color="teal"
)

plt.title("Sensitivity Analysis: Effect of Selected Feature on Target-Class Prediction Rate")
plt.xlabel("Selected Feature Value")
plt.ylabel("Mean Target-Class Prediction Rate")

plt.xticks(sens_summary["Feature_Value"])  # ensure all five values appear
plt.grid(True, linestyle="--", alpha=0.5)

plt.show()
