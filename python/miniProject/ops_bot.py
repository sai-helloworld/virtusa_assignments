import re
import os
from datetime import datetime

def analyse(path): 
    c = {"ERROR": 0, "CRITICAL": 0, "FAILED LOGIN": 0}
    temp = []
     
    if not os.path.exists(path):
        print(f"can't find the file in the specified location: {path}")
        return

    print(f"... Scanning thoroughly{path} ...")
     
    with open(path, 'r') as file:
        for line in file:
            modifyed_line = line.upper() 
            for event in c.keys():
                if event in modifyed_line:
                    c[event] += 1 
                    if event in ["CRITICAL", "FAILED LOGIN", "ERROR"]:
                        temp.append(line.strip())
 
    print("\n--> Security Summary:")
    for event, c in c.items():
        print(f"\t- {event}: {c}")
 
    today_date = datetime.now().strftime("%Y-%m-%d")
    report = f"security_alert_{today_date}.txt"
     
    with open(report, 'w') as report_file:
        report_file.write(f"SECURITY ALERT REPORT - {today_date}\n")
        report_file.write("="*35 + "\n")
        if temp:
            report_file.write("\n".join(temp))
        else:
            report_file.write("Everything is clear, No major threats detected.")
 
    if os.path.exists(report):
        file_size = os.path.getsize(report)
        print(f"\n--> Report is ready: {report}")
        print(f"--> File size: {file_size} bytes")
    print('.'*100)
 
if __name__ == "__main__": 
    analyse("server.log")