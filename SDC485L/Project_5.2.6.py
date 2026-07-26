# --------------------------------------------
# Run simulations: 3 conditions × 10 seeds = 30 runs
# --------------------------------------------
results = []

for condition in conditions:
    for seed in seeds:
        changes = feature_changes_config[condition]
        run_result = run_simulation(
            model=gb_opt,
            feature_data=df_clean[num_features],
            condition=condition,
            feature_changes=changes,
            sample_size=sample_size,
            seed=seed
        )
        results.append(run_result)

sim_df = pd.DataFrame(results)

print(sim_df.head())
print("\nSimulation DataFrame shape:", sim_df.shape)
print("\nRuns per condition:")
print(sim_df["Condition"].value_counts())
