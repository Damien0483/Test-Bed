import matplotlib.pyplot as plt

plt.figure(figsize=(10, 6))

# Line plot with error bars
plt.errorbar(
    sens_summary["Feature_Value"],
    sens_summary["Mean_Prediction_Rate"],
    yerr=sens_summary["Std_Prediction_Rate"],
    linestyle="-",
    linewidth=2,
    capsize=5,
    color="teal",
    label="Mean ± Std"
)

# Scatter plot on top
plt.scatter(
    sens_summary["Feature_Value"],
    sens_summary["Mean_Prediction_Rate"],
    color="darkorange",
    s=80,
    label="Mean Prediction Rate"
)

plt.title("Sensitivity Analysis: Effect of Selected Feature on Target-Class Prediction Rate")
plt.xlabel("Selected Feature Value")
plt.ylabel("Mean Target-Class Prediction Rate")

plt.xticks(sens_summary["Feature_Value"])
plt.grid(True, linestyle="--", alpha=0.5)
plt.legend()

plt.show()
