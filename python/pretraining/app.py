import os

from flask import Flask, render_template
from flask.cli import load_dotenv
import pandas as pd
from engine import WeatherEngine

load_dotenv()
app = Flask(__name__)

API_KEY = MY_API_KEY
lattitude = "17.071230" 
longitude = "78.204887"

engine = WeatherEngine(API_KEY, lattitude, longitude)

@app.route('/')
def dashboard():
    current_data, error = engine.get_current()
    if current_data is None:
        return f"<h1>Error</h1><p>{error}</p>"

    df_history, predictions = engine.insights()
    
    labels = [pd.to_datetime(t).strftime('%H:%M:%S') for t in df_history['timestamp']]
    
    return render_template('index.html', 
                           w=current_data, 
                           labels=labels, 
                           temps=df_history['temp'].tolist(),
                           humidities=df_history['humidity'].tolist(),
                           pred=predictions)

if __name__ == '__main__':
    app.run(debug=True)