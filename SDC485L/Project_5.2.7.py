# --------------------------------------------
# Summary by condition
# --------------------------------------------
summary = sim_df.groupby("Condition").agg(
    Mean_Prediction_Rate=("Target_Class_Prediction_Rate", "mean"),
    Std_Prediction_Rate=("Target_Class_Prediction_Rate", "std"),
    Min_Prediction_Rate=("Target_Class_Prediction_Rate", "min"),
    Max_Prediction_Rate=("Target_Class_Prediction_Rate", "max"),
    Mean_Probability=("Average_Target_Class_Probability", "mean")
).reset_index()

print("\nSimulation comparison table:")
print(summary)
