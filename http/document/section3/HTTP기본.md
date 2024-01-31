### `모든 것이 HTTP(HyperText Transfer Protocol)`

> HTTP 메시지에 모든 것을 전송
> - HTML, TEXT, IMAGE, 음성, 영상, 파일
> - JSON, XML (API)
> - 거의 모든 형태의 데이터 전송 가능, 서버간에 데이터를 주고 받을 때도 대부분 HTTP 사용

> `HTTP 특징`
> - 클라이언트 서버 구조
> - 무상태 프로토콜(스테이스리스), 비연결성
> - HTTP 메시지
> - 단순함, 확장 가능

---

### `클라이언트 서버 구조`

> Request Response 구조 <br>
> 클라이언트는 서버에 요청을 보내고, 응답을 대기 <br>
> 서버가 요청에 대한 결과를 만들어서 응답

---

### `Stateful(상태유지), Stateless(무상태)`

> `Stateless(무상태 프로토콜)`
> - 서버가 클라이언트의 상태를 보존X
> - 장점: 서버 확장성 높음(스케일 아웃)
> - 중간에 서버 장애가 나도 다른 서버에서 응답O
> - 단점: 클라이언트가 추가 데이터 전송
> - 로그인이 필요 없는 단순한 서비스 소개 화면에 사용

> `Stateful(상태유지)`
> - 서버가 클라이언트의 상태를 보존O
> - 중간에 서버 장애가 터지면 응답X
> - 로그인등 상태 유지 필요한 경우에 사용
>   - 일반적으로 브라우저 쿠키와 서버 세션등을 사용해서 상태 유지
>   - 상태유지는 최소한만 사용

> `Stateful, Stateless 차이`
> - 상태 유지(Stateful): 중간에 다른 서버로 바뀌면 안된다
> - 무상태(Stateless): 중간에 다른 서버로 바뀌어도 된다.
>   - 갑자기 클라이언트 요청이 증가해도 서버를 대거 투입할 수 있다
>   - 무상태는 응답 서버를 쉽게 바꿀 수 있다. -> 무한한 서버 증설 가능

---

### `비 연결성(connectionless)`

> 클라이언트와 서버 연결을 유지 하는 모델 - 연결을 계속 유지할 경우, 서버 자원 소모 <br>
> 클라이언트와 서버 연결을 유지 하지 않는 모델 - 서버는 연결 유지X, 최소한의 자원 사용

> `비 연결성`
> - HTTP는 기본이 연결을 유지하지 않는 모델
> - 일반적으로 초 단위의 이하의 빠른 속도로 응답
> - 1시간 동안 수천명이 서비스를 사용해도 실제 서버에서 동시에 처리하는 요청은 수십개 이하로 매우 작음
> - 서버 자원을 매우 효율적으로 사용할 수 있음


```
비 연결성 한계와 극복

[한계]
  - TCP/IP 연결을 새로 맺어야 함 - 3 way handshake 시간 추가
  - 웹 브라우저로 사이트를 요청하면 HTML 뿐만 아니라 자바스크립트, css, 추가 이미지등 수 많은 자원이 함께 다운로드

[극복]
  - 지금은 HTTP 지속 연결(Persistent Connections)로 문제 해결
  - HTTP/2, HTTP/3에서 더 많은 최적화
```

---

### `HTTP 메세지`

> `http 요청 메세지`
> - GET /search?q=hello&hl=ko HTTP/1.1 <span style="color:red">*(start-line)*</span>
> - HTTP/1.1 Host: www.google.com <span style="color:yellow">*(header)*</span>
```
GET - HTTP 메서드
  - 종류: GET, POST, PUT, DELETE...
  - 서버가 수행해야 할 동작 지정 => GET: 리소스 조회 / POST: 요청 내역 처리
    등등

/search?q=hello&hl=ko - 요청 메시지 - 요청 대상
  - 절대경로 = "/" 로 시작하는 경로
  - 쿼리스트링 = ?뒤에 key=value형태의 파라미터

HTTP/1.1 - HTTP 버전
```

> `http 응답 메세지`
> - HTTP/1.1 200 OK <span style="color:red">*(start-line)*</span>
> - Content-Type: text/html;charset=UTF-8 <span style="color:yellow">*(header)*</span>
> - Content-Length: 3423 <span style="color:yellow">*(header)*</span>
> - <span style="color:green">*(empty line)*</span>
> - \<html> \<body>...\</body> \</html> <span style="color:skyblue">*(message body)*</span>
```
[시작 라인]
HTTP/1.1 - HTTP 버전
200 - HTTP상태 코드 (요청 성공, 실패를 나타냄)
  - 200번대 : 성공
  - 400번대 : 클라이언트 요청 오류
  - 500번대 : 서버 내부 오류
OK - : 사람이 이해할 수 있는 짧은 상태 코드 설명 글

[http header 용도]
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
Content-Type: text/html;charset=UTF-8
Content-Length: 3423
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
- HTTP 전송에 필요한 모든 부가정보
  - ex) 메시지 바디의 내용, 메시지 바디의 크기, 압축, 인증, 요청 클라이언트 (브라우저) 정보, 서버 애플리케이션 정보, 캐시 관리 정보...

[http message body 용도]
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
<html>
  <body>
    ...
  </body>
</html>
ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

- 실제 전송할 데이터
- HTML 문서, 이미지, 영상, JSON 등등 byte로 표현할 수 있는 모든 데이터 전송 가능
```

> [HTTP 메시지 구조]
> - <span style="color:red">start-line</span> 시작 라인
> - <span style="color:yellow">header</span> 헤더
> - <span style="color:green">empty line</span> 공백 라인 (CRLF)
> - <span style="color:skyblue">message body</span>