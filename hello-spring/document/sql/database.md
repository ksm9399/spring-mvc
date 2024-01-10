### h2 database
> 개발이나 테스트 용도로 가볍고 편리한 DB

### download
> https://www.h2database.com/html/download-archive.html

```
❗h2 데이터베이스 버전은 스프링 부트 버전에 맞춘다.
  - 스프링 부트 2.x를 사용하면 1.4.200 버전 사용
  - 스프링 부트 3.x를 사용하면 2.1.214 버전 이상 사용
```

```
[데이터베이스 파일 생성 방법]
  - 웹 콘솔로 들어가서 JDBC URL : jdbc:h2:~/test 로 설정 후 연결
  - 루트 경로에 ~/test.mv.db 파일 생성 확인 (C:/Users/username/~)
  - 이후부터는 jdbc:h2:tcp://localhost/~/test로 접속
```

