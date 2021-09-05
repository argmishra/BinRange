### Part 1
Test Case passing 

I have initialize map in `populateCache` cache method.

### Part 2
1. Create Bin Range Info Cache
2. Update Bin Range Info Cache
3. Update Bin Range Info Cache fail as Bin Range Info Cache **ref not present**
4. Delete Bin Range Info Cache
5. Delete Bin Range Info Cache fail as Bin Range Info Cache **ref not present**

```curl
curl -X POST "http://localhost:8080/binRangeInfoCache" -H  "Content-Type: application/json" -d '{"bankName": "ICICI","currencyCode": "IN","end": 4263999999998995,"start": 4253000000000000}'

curl -X PUT "http://localhost:8080/binRangeInfoCache/fc5bf6d5-d06b-4d57-a58f-70ed1699d7e8" -H  "Content-Type: application/json" -d '{"bankName": "KBS","currencyCode": "IRELAND","end": 4263999999998995,"start": 4253000000000000}'

curl -X DELETE "http://localhost:8080/binRangeInfoCache/8894825d-6f12-4af2-9645-d1fbd2c678f1" 
```

### Part 3
log statements for controllers are saved in `audit-log.txt` file in root folder
