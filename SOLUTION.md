### Part 1



### Part 2
1. Create Bin Range Info Cache
2. Update Bin Range Info Cache
3. Update Bin Range Info Cache fail as Bin Range Info Cache **ref not present**
4. Delete Bin Range Info Cache
5. Delete Bin Range Info Cache fail as Bin Range Info Cache **ref not present**

```curl
curl -X POST "http://localhost:8080/binRangeInfoCache" -H  "Content-Type: application/json" -d '{"bankName": "ICICI","currencyCode": "IN","end": 4263999999998995,"start": 4253000000000000}'

curl -X PUT "http://localhost:8080/binRangeInfoCache/0d465dc4-5f92-4645-ac2b-014074c4c10b" -H  "Content-Type: application/json" -d '{"bankName": "KBS","currencyCode": "IRELAND","end": 4263999999998995,"start": 4253000000000000}'

curl -X DELETE "http://localhost:8080/binRangeInfoCache/8ec972da-a8bc-476b-a492-85244a478cec" 
```

### Part 3
log statements for controllers are saved in `audit-log.txt` file in root folder