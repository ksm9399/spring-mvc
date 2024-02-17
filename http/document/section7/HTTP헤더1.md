### `HTTP 헤더 개요`

> `HTTP 헤더`
> - HTTP 전송에 필요한 모든 부가정보
> - ex)  메시지 바디의 내용, 메시지 바디의 크기, 압축, 인증, 요청 클라이언트, 서버 정보, 캐시 관리 정보...
> - 표준 헤더가 너무 많음
> - 필요시 임의의 헤더 추가 가능
>   - ex) helloworld: hihi

> `HTTP BODY (message body RFC7230-최신)`
> - 메시지 본문(message body)을 통해 표현 데이터 전달
> - 메시지 본문 = 페이로드(payload)
> - 표현은 요청이나 응답에서 전달할 실제 데이터
> - 표현 헤더는 표현 데이터를 해석할 수 있는 정보 제공
>   - 데이터 유형(html, json), 데이터 길이, 압축 정보 등등

---

### `표현`

> `표현`
> - Content-Type: 표현 데이터의 형식
> - Content-Encoding: 표현 데이터의 압축 방식
> - Content-Language: 표현 데이터의 자연 언어
> - Content-Length: 표현 데이터의 길이
> - 표현 헤더는 전송, 응답 둘다 사용

> `Content-Type(표현 데이터의 형식 설명)`
> - 미디어 타입, 문자 인코딩
>   -  ex) text/html; charset=utf-8
>   - application/json
>   - image/png

> `Content-Encoding(표현 데이터 인코딩)`
> - 표현 데이터를 압축하기 위해 사용
> - 데이터를 전달하는 곳에서 압축 후 인코딩 헤더 추가
> - 데이터를 읽는 쪽에서 인코딩 헤더의 정보로 압축 해제
>   -  ex) gzip
>   - deflate
>   - identity

> `Content-Language(표현 데이터의 자연 언어)`
> - 표현 데이터의 자연 언어를 표현
>   -  ex) ko
>   - en
>   - en-US

> `Content-Length(표현 데이터의 길이)`
> - 바이트 단위
> - Transfer-Encoding(전송 코딩)을 사용하면 Content-Length를 사용하면 안됨

---

### `협상(콘텐츠 네고시에이션) - 클라이언트가 선호하는 표현 요청`

> - Accept: 클라이언트가 선호하는 미디어 타입 전달
> - Accept-Charset: 클라이언트가 선호하는 문자 인코딩
> - Accept-Encoding: 클라이언트가 선호하는 압축 인코딩
> - Accept-Language: 클라이언트가 선호하는 자연 언어
> - 협상 헤더는 요청시에만 사용

---

### `전송 방식`

> `전송 방식`
> - Transfer-Encoding
> - Range, Content-Range
> - 단순 전송
> - 압축 전송
> - 분할 전송
> - 범위 전송

> `단순 전송(Content-Length)`
> - 컨텐츠에 대한 길이

> `압축 전송(Content-Encoding)`
> - Content-Encoding으로 요청된 타입으로 압축

> `분할 전송(Transfer-Encoding)`
> - Content-Length를 넣으면 안됨 예상이 안되서
> - 데이터를 분할해서 보냄

> `범위 전송(Range, Content-Range)`
> - 이미지 같은 것을 다운 받다가 끊김 -> 다운받은 범위를 알려줌으로서 끊긴 부분에서 다시 받을 수 있음

---

### `일반 정보`

> - From: 유저 에이전트의 이메일 정보
> - Referer: 이전 웹 페이지 주소
> - User-Agent: 유저 에이전트 애플리케이션 정보
> - Server: 요청을 처리하는 오리진 서버의 소프트웨어 정보
> - Date: 메시지가 생성된 날짜

> `From(유저 에이전트의 이메일 정보)`
> - 일반적으로 잘 사용되지 않음
> - 검색 엔진 같은 곳에서, 주로 사용
> - 요청에서 사용

> `Referer(이전 웹 페이지 주소)`
> - 현재 요청된 페이지의 이전 웹 페이지 주소
> - A -> B로 이동하는 경우 B를 요청할 때 Referer: A 를 포함해서 요청
> - Referer를 사용해서 유입 경로 분석 가능
> - 요청에서 사용
> - 참고: referer는 단어 referrer의 오타

> `User-Agent(유저 에이전트 애플리케이션 정보)`
> - user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.183 Safari/537.36
> - 클라이언트의 애플리케이션 정보(웹 브라우저 정보, 등등)
> - 통계 정보
> - 어떤 종류의 브라우저에서 장애가 발생하는지 파악 가능
> - 요청에서 사용

> `Server(요청을 처리하는 ORIGIN 서버의 소프트웨어 정보)`
> - Server: Apache/2.2.22 (Debian)
> - server: nginx
> - 응답에서 사용

> `Date(메세지가 발생한 날짜와 시간)`
> - Date: Tue, 15 Nov 1994 08:12:31 GMT
> - 응답에서 사용

---