프로젝트에는 embedded redis 가 포함되어 있기 때문에  
프로젝트를 실행하면 localhost:6379 redis 도 함께 실행된다.  
이에 redis-cli 로 접속해서 캐싱되는 것을 확인할 수 있다.
```
redis-cli -h localhost -p 6379
```

맥북 기준 redis-cli 설치 및 명령어는 [여기](https://velog.io/) 를 참고하자.
