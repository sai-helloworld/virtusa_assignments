import requests
import sqlite3
import pandas as pd
import numpy as np
from datetime import datetime
from sklearn.linear_model import LinearRegression

class WeatherEngine:
    def __init__(self, api_key, latitude, longitude):
        self.api_key = api_key
        self.latitude = latitude
        self.longitude = longitude
        self.base_url = "https://api.openweathermap.org/data/2.5/weather"
        self.db_name = "weather_history.db"
        self._init_db()

    def _init_db(self):
        with sqlite3.connect(self.db_name) as conn:
            conn.execute('''CREATE TABLE IF NOT EXISTS history 
                            (timestamp DATETIME, temp REAL, humidity INTEGER)''')

    def get_current(self):
        params = {"lat": self.latitude, "lon": self.longitude, "appid": self.api_key, "units": "metric"}
        try:
            r = requests.get(self.base_url, params=params)
            r.raise_for_status()
            data = r.json()
            self.save(data['main']['temp'], data['main']['humidity'])
            return data, None
        except Exception as e:
            return None, str(e)

    def save(self, temp, humidity):
        with sqlite3.connect(self.db_name) as conn:
            conn.execute("INSERT INTO history VALUES (?, ?, ?)", (datetime.now(), temp, humidity))

    def insights(self):
        with sqlite3.connect(self.db_name) as conn:
            df = pd.read_sql_query("SELECT * FROM history ORDER BY timestamp ASC", conn)
        
        if df.empty:
            return df, {"temp": "No data", "hum": "No data"}
        df['timestamp'] = pd.to_datetime(df['timestamp'])

        df_filtared = df[df['timestamp'].dt.hour > 20].copy()


        df_filtared['date_only'] = df_filtared['timestamp'].dt.date
        df_daily = df_filtared.groupby('date_only').last().reset_index()

        df_daily['ts_seconds'] = df_daily['timestamp'].values.astype(np.int64) // 10**9
        
        X = df_daily[['ts_seconds']].values
        nxtDayPreds = np.array([[X[-1][0] + 86400]])
        tempereture_model = LinearRegression().fit(X, df_daily['temp'])
        pred_t = tempereture_model.predict(nxtDayPreds)[0]

        humidity_model = LinearRegression().fit(X, df_daily['humidity'])
        humidity_predictions = humidity_model.predict(nxtDayPreds)[0]
        humidity_predictions = max(0, min(100, humidity_predictions))

        return df.tail(10), {"temp": round(pred_t, 2), "hum": round(humidity_predictions, 2)}