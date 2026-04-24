The structure of this project folder
```
virtusa_assignments/
│
├── README.md
│
├── java/
│   ├── miniProject/
│   │   ├── Account.java
│   │   ├── FinSafe.java
│   │   └── InSufficientFundsException.java
│   └── pretraining/
│       ├── Account.java
│       ├── BankingApp.java
│       └── DBConnection.java
│
├── python/
│   ├── miniProject/
│   │   ├── ops_bot.py
│   │   ├── security_alert_2026-04-13.txt
│   │   ├── security_alert_2026-04-14.txt
│   │   └── server.log
│   └── pretraining/
│       ├── app.py
│       ├── engine.py
│       ├── static/
│       │   └── styles.css
│       └── templates/
│           ├── error.html
│           └── index.html
│
└── sql/
    ├── miniProject/
    │   ├── data.sql
    │   ├── reports.sql
    │   └── schema.sql
    └── pretraining/
        ├── anlayse.sql
        └── schema.sql
```

# 1. Java

## 1.1 Mini Project — FinSafe (CLI Banking Wallet)
## requirements
### java (any version above java 21)
## Instructions
1. navigate to the `java/miniProject/` folder
2. compile using `javac FinSafe.java` in the terminal
3. run using `java FinSafe` in the terminal

#### After running you will see a CLI banking wallet. It will ask for your name and the account balance starts at 0. You can Deposit funds, Spend (deduct) funds, view your Mini Statement (last 5 transactions), or Exit by pressing 4

## 1.2 Pre-training — BankingApp (MySQL-backed Banking CLI)
## requirements
### java (any version above java 21)
### mysql
### mysql-connector-j (JDBC driver jar on the classpath)
## Instructions
1. navigate to the `java/pretraining/` folder
2. open `DBConnection.java` and update the `URL`, `USER`, and `PASS` fields with your MySQL credentials
3. create a database named `JavaBank` in MySQL and set up the `accounts` table
4. compile all files with `javac -cp .;mysql-connector-j-x.x.x.jar *.java` (use `:` instead of `;` on Linux/Mac)
5. run with `java -cp .;mysql-connector-j-x.x.x.jar BankingApp`

#### After running, the CLI will let you Create Account or Login. Once logged in you can Check Balance, Deposit, Withdraw, view Transaction History, or Logout

---

# 2. Python

## 2.1 Mini Project — ops_bot (Log Security Alert Generator)
## requirements
### python 3.12 (or above)
## Instructions
1. navigate to the `python/miniProject/` folder
2. insert your own `server.log` file or use/modify the existing one
3. run `python ops_bot.py` in the terminal
4. you will get the report in a `security_alert_[date].txt` file

#### I have already ran the code using a sample server.log file from the internet and you can see the reports in security_alert_2026-04-13.txt and security_alert_2026-04-14.txt

## 2.2 Pre-training — Weather Dashboard (Flask Web App)
## requirements
### python 3.12 (or above)
### flask, pandas, numpy, scikit-learn, requests
### OpenWeatherMap API key
## Instructions
1. navigate to the `python/pretraining/` folder
2. open `app.py` and set your OpenWeatherMap API key in the `API_KEY` variable
3. optionally update `lattitude` and `longitude` to your desired location
4. install dependencies with `pip install flask pandas numpy scikit-learn requests`
5. run with `python app.py` in the terminal
6. open your browser and go to `http://127.0.0.1:5000`

#### The dashboard shows the current weather (temperature and humidity) along with a chart of the last 10 readings. It also uses a linear regression model to predict next-day temperature and humidity based on the stored history

---

# 3. SQL

## 3.1 Mini Project — Banking Transactions DB
## requirements
### mysql
## Instructions
1. open mysql and run all queries in `sql/miniProject/schema.sql` to create the tables
2. then run `sql/miniProject/data.sql` to insert the sample data
3. then run the queries in `sql/miniProject/reports.sql` to get the required results

## 3.2 Pre-training — MovieStreamingDB (Streaming Analytics)
## requirements
### mysql
## Instructions
1. open mysql and run `sql/pretraining/schema.sql` to create the `MovieStreamingDB` database and tables (Users, Movies, Ratings, Watch_History)
2. insert your own data or use sample inserts
3. run the queries in `sql/pretraining/anlayse.sql` to get the analytics results

#### The analytics queries cover: Top Rated Movies, Most Popular Genres, Movie Recommendations based on similar users, User Behavior Patterns by age group, and Trending Movies based on recent watch activity and ratings
