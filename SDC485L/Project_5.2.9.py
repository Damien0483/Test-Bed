# --------------------------------------------
# Select one numerical feature from Scenario 1 or 2
# Example: body_mass_g from Scenario 1
# --------------------------------------------
selected_feature = "body_mass_g"

feature_values = np.linspace(
    df_clean[selected_feature].quantile(0.10),
    df_clean[selected_feature].quantile(0.90),
    5
)

print("Selected feature:", selected_feature)
print("Sensitivity values:", feature_values)

# --------------------------------------------
# Sensitivity runs: 5 values × 5 seeds = 25 runs
# --------------------------------------------
sens_seeds = [101, 102, 103, 104, 105]
sens_results = []

for val in feature_values:
    for seed in sens_seeds:
        # sample records
        sampled = df_clean[num_features].sample(
            n=sample_size,
            replace=True,
            random_state=seed
        ).copy()

        # set selected feature to sensitivity value
        sampled[selected_feature] = val

        # clamp to realistic limits
        min_val, max_val = feature_limits[selected_feature]
        sampled[selected_feature] = sampled[selected_feature].clip(min_val, max_val)

        # rebuild encoded features for sampled indices
        sampled_indices = sampled.index
        sampled_encoded = df_encoded.loc[sampled_indices, X.columns].copy()

        # predictions
        preds = gb_opt.predict(sampled_encoded)
        proba = gb_opt.predict_proba(sampled_encoded)

        target_mask = (preds == target_class)
        n_target = target_mask.sum()
        rate_target = n_target / len(sampled_encoded)

        class_idx = list(gb_opt.classes_).index(target_class)
        avg_prob_target = proba[:, class_idx].mean()

        sens_results.append({
            "Feature_Value": val,
            "Seed": seed,
            "Target_Class_Prediction_Rate": rate_target,
            "Average_Target_Class_Probability": avg_prob_target
        })

sens_df = pd.DataFrame(sens_results)
print("\nSensitivity results (head):")
print(sens_df.head())
