def theater_customer(env, name, counter, mean_service_time, log):
    """Models a single customer arriving, waiting, and purchasing a ticket."""
    arrival_time = env.now
    
    with counter.request() as req:
        # Wait for an available ticket-counter employee
        yield req
        wait_time = env.now - arrival_time
        service_start = env.now
        
        # Purchase ticket (Service time)
        yield env.timeout(np.random.exponential(mean_service_time))
        service_end = env.now
        
        # Log customer data
        log.append({
            'arrival_time': arrival_time,
            'service_start': service_start,
            'service_end': service_end,
            'wait_time': wait_time
        })

def theater_process(env, employees, mean_interarrival, mean_service, max_customers, log):
    """Generates customers until the customer limit is reached."""
    counter = simpy.Resource(env, capacity=employees)
    customer_count = 0
    
    while customer_count < max_customers:
        yield env.timeout(np.random.exponential(mean_interarrival))
        customer_count += 1
        env.process(theater_customer(env, f'Customer {customer_count}', counter, mean_service, log))

def run_simulation(employees, interarrival_time, service_time, simulation_time, customer_limit, seed):
    """Runs a single simulation iteration and returns aggregated metrics."""
    np.random.seed(seed)
    env = simpy.Environment()
    log = []
    
    env.process(theater_process(env, employees, interarrival_time, service_time, customer_limit, log))
    env.run(until=simulation_time)
    
    # Calculate aggregate metrics
    if len(log) > 0:
        avg_wait = np.mean([c['wait_time'] for c in log])
        max_wait = np.max([c['wait_time'] for c in log])
        customers_served = len(log)
    else:
        avg_wait, max_wait, customers_served = 0, 0, 0
        
    return {
        'Employees': employees,
        'Interarrival_Time': interarrival_time,
        'Seed': seed,
        'Average_Wait_Time': avg_wait,
        'Maximum_Wait_Time': max_wait,
        'Customers_Served': customers_served
    }

# Run the 180 simulation combinations
results = []
employees_list = [1, 2, 3]
interarrival_list = [3, 5]
seeds = range(42, 72)
service_time = 3
simulation_time = 480
customer_limit = 50

for emp in employees_list:
    for ia in interarrival_list:
        for s in seeds:
            res = run_simulation(emp, ia, service_time, simulation_time, customer_limit, s)
            results.append(res)

# Create DataFrame
simulation_results = pd.DataFrame(results)
print("First several rows of the simulation DataFrame:")
display(simulation_results.head())
