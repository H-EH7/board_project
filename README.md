게시판 프로젝트
==========

## 💬 개요
공부한 것들을 연습하기 위해 간단한 게시판을 만들고 추가 기능을 만들어가는 1인 프로젝트

----------

## 🛠 사용 기술
> `Language`: Java 17
>
> `Framework`: Spring Boot 3.1.3
> 
> `Database`: H2
> 
> `Dependencies`: Lombok, Spring Web, Spring Validation, Thymeleaf, JDBC API, H2 Database

----------

## 💡 기능
`회원가입`: 아이디, 비밀번호, 이름으로 회원가입을 할 수 있고 중복 아이디는 불가능함

`로그인`: H2 DB에 저장된 회원을 조회하고 비밀번호 대조 후 세션을 생성해 로그인 처리

`조회수`: 한 게시글에 한 회원 당 한번 최초 조회 시 게시글의 조회수가 올라감

`페이징`: 한 페이지에 몇 개의 게시물만 나눠서 보여줌, 현재 페이지 정보는 쿼리 파라미터로 받음

----------

## 🗂 패키지 구조

```bash
   eh7
    └─board
    │  BoardApplication.java : 메인 어플리케이션
    │  SessionConst.java : 세션 관리를 위한 상수 (interface) - 세션 attribute의 이름 통일
    │
    ├─domain
    │  ├─member
    │  │      JdbcMemberRepository.java : 회원 저장소 구현체
    │  │      Member.java : 회원 클래스
    │  │      MemberRepository.java : 회원 저장소 인터페이스
    │  │      MemberService.java : 회원 서비스 구현체
    │  │
    │  ├─post
    │  │      JdbcPostRepository.java : 게시글 저장소 구현체
    │  │      Post.java : 게시글 클래스
    │  │      PostRepository.java : 게시글 저장소 인터페이스
    │  │      PostService.java : 게시글 서비스 구현체
    │  │
    │  └─view
    │          JdbcViewRepository.java : 조회 저장소 구현체
    │          View.java : 조회수를 위한 조회 클래스
    │          ViewRepository.java : 조회 저장소 인터페이스
    │          ViewService.java : 조회 서비스 구현체
    │
    └─web
        │  HomeController.java : 메인 화면 컨트롤러 ("/", "/board")
        │
        ├─login
        │      LoginController.java : 로그인 및 로그아웃 컨트롤러 ("/login", "/logout")
        │      LoginForm.java : 로그인 DTO 클래스
        │      LoginService.java : 로그인 서비스 구현체
        │
        ├─page
        │      Page.java : 페이징을 위한 클래스
        │      PageConst.java : 페이징 상수 (interface) - 한 페이지 당 노출 게시글, 페이지 네비바에 노출되는 최대 페이지 갯수 정의
        │
        ├─post
        │      PostController.java : 게시글 컨트롤러 ("/write", "/post", "/post/{id}", "/post/{id}/edit", "/post/{id}/delete")
        │      PostForm.java : 게시글 DTO 클래스
        │
        └─signup
                SignController.java : 회원가입 컨트롤러 ("/sign")
```

----------

## 💭 회고
그동안 이론과 예제로만 배워온 것들을 실습하고 온전히 내 것으로 만들기 위해 시작한 프로젝트이다. <br>

역시나 첫 시작부터 안다고 생각한 것이 틀렸다는 느낌을 받았다. <br>

이 프로젝트를 시작한 덕분에 다시금 공부하고 머릿속에서 정리할 수 있었다. <br>

실제 라이브러리의 사용법은 다시 떠올리고 사용하는데 큰 문제가 없었지만 문제는 프레임워크의 구조에 대한 이해에 있었다. <br>

서블릿의 예외처리 순서나 에러 메시지 처리 순서 등 해결하기 위해 찾기 힘든 부분이 있었다. <br>

또한 DB 설계나 클린 코드를 위한 설계 부분에서 많이 부족함을 느꼈다. <br>

생각했던것 보다 예외사항도 많았다. <br>

이 프로젝트에서 부족하다고 느낀 부분을 공부할 뿐만 아니라 해결 과정을 깔끔히 정리할 방법을 고민해봐야할 것 같다. <br>

뿐만 아니라 스프링 프레임워크의 구조와 서블릿 기술에 대해 정리가 필요할 것 같다. <br>

앞으로 검색 기능, 권한 부여, 비밀번호 암호화 등 기능을 계속해서 추가하겠다.
