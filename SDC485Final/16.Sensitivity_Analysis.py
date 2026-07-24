# Group data by Employees and Interarrival_Time
sensitivity_summary = simulation_results.groupby(
    ['Employees', 'Interarrival_Time']
)['Average_Wait_Time'].mean().reset_index()

# Convert Interarrival_Time to categorical string for better legend labels
sensitivity_summary['Traffic Condition'] = sensitivity_summary['Interarrival_Time'].map({
    3: 'Increased Flow (3-min)', 
    5: 'Normal Flow (5-min)'
})

print("Sensitivity Analysis Table:")
display(sensitivity_summary.pivot(index='Employees', columns='Traffic Condition', values='Average_Wait_Time'))

# Create grouped bar chart
plt.figure(figsize=(10, 6))
sns.barplot(
    data=sensitivity_summary, 
    x='Employees', 
    y='Average_Wait_Time', 
    hue='Traffic Condition', 
    palette='Set2'
)

plt.title('Impact of Increased Customer Flow on Wait Times by Staffing Level', fontsize=14)
plt.xlabel('Number of Ticket-Counter Employees', fontsize=12)
plt.ylabel('Average Wait Time (minutes)', fontsize=12)
plt.legend(title='Traffic Condition')
plt.show()
