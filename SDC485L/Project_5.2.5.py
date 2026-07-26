# --------------------------------------------
# Simulation function
# --------------------------------------------
def run_simulation(
    model,
    feature_data,
    condition,
    feature_changes,
    sample_size,
    seed
):
    # Randomly sample records with replacement
    sampled = feature_data.sample(
        n=sample_size,
        replace=True,
        random_state=seed
    ).copy()

    # Apply feature changes and keep within realistic limits
    for feat, delta in feature_changes.items():
        if feat in sampled.columns:
            sampled[feat] = sampled[feat] + delta
            # clamp to realistic limits
            min_val, max_val = feature_limits[feat]
            sampled[feat] = sampled[feat].clip(min_val, max_val)

    # Apply existing preprocessing:
    # Here we assume df_encoded was built from df_clean and we’re using the same X columns.
    # To keep it simple, we’ll rebuild encoded rows for the sampled indices from df_encoded.
    sampled_indices = sampled.index
    sampled_encoded = df_encoded.loc[sampled_indices, X.columns].copy()

    # Generate predictions and probabilities
    preds = model.predict(sampled_encoded)
    proba = model.predict_proba(sampled_encoded)

    # Target-class metrics
    target_mask = (preds == target_class)
    n_target = target_mask.sum()
    rate_target = n_target / len(sampled_encoded)

    class_idx = list(model.classes_).index(target_class)
    avg_prob_target = proba[:, class_idx].mean()

    # Return run-level results
    return {
        "Condition": condition,
        "Seed": seed,
        "Sample_Size": len(sampled_encoded),
        "Target_Class_Predictions": n_target,
        "Target_Class_Prediction_Rate": rate_target,
        "Average_Target_Class_Probability": avg_prob_target
    }
