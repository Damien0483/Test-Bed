# --------------------------------------------
# Line chart: feature value vs mean prediction rate
# --------------------------------------------
plt.figure(figsize=(8, 6))
plt.errorbar(
    sens_summary["Feature_Value"],
    sens_summary["Mean_Prediction_Rate"],
    yerr=sens_summary["Std_Prediction_Rate"],
    marker="o",
    capsize=5
)
plt.title(f"Sensitivity of {selected_feature} on Target-Class Prediction Rate")
plt.xlabel(f"{selected_feature} value")
plt.ylabel("Mean Prediction Rate")
plt.show()
