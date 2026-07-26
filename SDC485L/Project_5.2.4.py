# --------------------------------------------
# Simulation parameters
# --------------------------------------------
simulation_runs_per_condition = 10
seeds = list(range(42, 52))  # 42–51
sample_size = 50

# If dataset has fewer than 50 usable records, adjust and document
usable_features = df_clean[num_features].dropna()
if len(usable_features) < sample_size:
    sample_size = len(usable_features)
    print("Adjusted sample_size to:", sample_size)

# --------------------------------------------
# Define conditions and feature changes
# --------------------------------------------
conditions = ["Baseline", "Scenario 1", "Scenario 2"]

# Scenario 1: increase body_mass_g by +300g (within observed range)
# Scenario 2: increase body_mass_g by +300g and flipper_length_mm by +5mm
feature_changes_config = {
    "Baseline": {},
    "Scenario 1": {"body_mass_g": 300.0},
    "Scenario 2": {"body_mass_g": 300.0, "flipper_length_mm": 5.0}
}

# Realistic limits: use min/max from df_clean
feature_limits = {
    f: (df_clean[f].min(), df_clean[f].max())
    for f in num_features
}
