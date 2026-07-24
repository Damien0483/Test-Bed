# Import necessary libraries
import simpy
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
from sklearn.model_selection import train_test_split, GridSearchCV
from sklearn.metrics import mean_squared_error, r2_score
from xgboost import XGBRegressor
import warnings

# Suppress warnings for cleaner output
warnings.filterwarnings('ignore')
sns.set_theme(style="whitegrid")
