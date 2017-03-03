# runtime-cron-expression
Restful service que possiblita gerenciar schedules via serviço

Abaixo os endpoints

```java
curl -H "Content-Type: application/json" -X POST \
  -d '{"name": "job-name", "msg": "Hello World", "cron": "* * * * *"}' \
  http://localhost:8080/api/jobs

curl http://localhost:8080/api/jobs

curl -X DELETE http://localhost:8080/api/jobs/job-name
```
