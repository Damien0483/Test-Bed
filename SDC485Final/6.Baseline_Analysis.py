# Filter for baseline condition
baseline_df = simulation_results[simulation_results['Interarrival_Time'] == 5]

# Calculate average wait times by staffing level
baseline_summary = baseline_df.groupby('Employees')['Average_Wait_Time'].mean().reset_index()
print("Baseline Results Table (5-min Inter-arrival):")
display(baseline_summary)

# Create bar chart
plt.figure(figsize=(8, 5))
sns.barplot(data=baseline_summary, x='Employees', y='Average_Wait_Time', palette='Blues_d')
plt.title('Average Customer Wait Time by Staffing Level (Baseline)', fontsize=14)
plt.xlabel('Number of Ticket-Counter Employees', fontsize=12)
plt.ylabel('Average Wait Time (minutes)', fontsize=12)
plt.show()
