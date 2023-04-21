# basic-crud

- ERD

![ERD.png](src/main/resources/static/images/ERD.png)

- API Statement

| Method | URL               | Request Header                                                                                                | Request                                                                |   
|--------|-------------------|---------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------|
| POST   | /api/user/signup  | -                                                                                                             | {<br/>"username" : "username1",<br/>"password" : "password1"<br/>}     |   
| POST   | /api/user/login   | -                                                                                                             | {<br/>"username" : "username1",<br/>"password" : "password1"<br/>}     |
| GET    | /api/posts        | -                                                                                                             | -                                                                      |   
| GET    | /api/post{id}     | Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VybmFtZTEiLCJleHAiOjE2ODIwNDkzNzYsImlhdCI6MTY4MjA... | -                                                                      |   
| POST   | /api/post         | -                                                                                                             | {<br/>"title": "title1",<br/>"content": "content1"<br/>}               |   
| PUT    | /api/post{id}     | Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VybmFtZTEiLCJleHAiOjE2ODIwNDkzNzYsImlhdCI6MTY4MjA... | {<br/>"title": "modifiedtitle",<br/>"content": "modifiedcontent"<br/>} |  
| DELETE | /api/post{id}     | Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VybmFtZTEiLCJleHAiOjE2ODIwNDkzNzYsImlhdCI6MTY4MjA... | -                                                                      |  

## Points
- JWT를 사용하여 인증/인가를 구현 했을 때의 장점: 인증에 필요한 모든 정보를 담고 있어서 별도의 인증이 필요하지 않음, 쿠키를 전달하지 않음으로 쿠키에 인한 취약점이 사라짐, 서버 측 부하를 낮춤
- JWT를 사용한 인증/인가의 한계점: 구현의 복잡도 증가, JWT에 담는 내용이 커질 수록 네트워크 비용 증가, Secret Key 유출 시 JWT 조작 가능
- 댓글 기능이 있는 블로그에서 댓글이 달려있는 게시글을 삭제하려고 한다면 발생하는 문제 및 해결 방안: 게시글의 댓글이 삭제되지 않을 수 있음, Cascade(영속성 전이)를 통해 해결 가능
- IoC란?: Inversion of Control, 프로그램의 제어 흐름이 뒤바뀜
- DI란?: Dependency Injection, 의존 관계 주입. 용도에 맞게 필요한 객체를 외부에서 결정해서 연결