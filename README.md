The structure of this project folder 
```
mini_project_virtusa/
│
├── README.md
│
├── java/
│   ├── Account.java
│   ├── Account.class
│   ├── FinSafe.java
│   ├── FinSafe.class
│   ├── FinSafeApp.class
│   ├── InSufficientFundsException.java
│   └── InSufficientFundsException.class
│
├── python/
│   ├── ops_bot.py
│   ├── security_alert_2026-04-13.txt
│   ├── security_alert_2026-04-14.txt
│   └── server.log
│
└── sql/
    ├── data.sql
    ├── reports.sql
    └── schema.sql
```

# 1. Java Project
## requirements
### java (any version above java21)
## Instructions
1. first Compile FinSafeApp.java file using javac FinSafeApp.java command in the terminal
2. then run java FinSafeApp in the terminal to execute the code

#### After running java FinSafeApp in the terminal you will be displayed with a CLI of a sample bank it will ask for the name and initially the account balance will be 0, you have to add in order to perform actions you need. There are operations like Check Balance, Check Transaction History, Spend, Deposit(you have to enter respective number assosiated with that operation). And if you want to exit you have to select Exit by pressing 4

# 2. python Project
## requirements
### python 3.12(or above)
## Instructions
1. first insert server.log file or replace or insert data in the already existing file
2. the run ops_bot.py file using python ops_bot.py command in the terminal
3. you will get the report in sercurity_alert_[date].txt file
#### I have already ran the code using a sample server.log file from the internet and you can see the reports in security_alert_2026-04-14.txt file

# 3. SQL 
## requirements
### mysql
## Instructions
1. open mysql database and first run all the queries in schema.sql in order to create tables
2. then open data.sql and run in order to insert data into tables
3. then run queries in reports.sql to get required results
