# --------------------------------------------
# Group by feature value
# --------------------------------------------
sens_summary = sens_df.groupby("Feature_Value").agg(
    Mean_Prediction_Rate=("Target_Class_Prediction_Rate", "mean"),
    Std_Prediction_Rate=("Target_Class_Prediction_Rate", "std"),
    Mean_Probability=("Average_Target_Class_Probability", "mean")
).reset_index()

print("\nSensitivity summary table:")
print(sens_summary)
