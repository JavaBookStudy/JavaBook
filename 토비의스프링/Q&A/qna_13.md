# 13주차

#### :bulb: DI 설정정보를 분리하는 방법은? by 정수

-----------

#### :bulb: 인터페이스가 필요한 이유를 DI와 연관지어 설명해 주세요. (복습) by 정수

-----------

#### :bulb: 스프링 MVC가 어떻게 작동하는지 설명해주세요. (복습) by 정수

스프링 MVC
1. 클라이언트 요청이 DispatcherServlet으로 전달됨
2. DispatcherServlet은 HandlerMapping에 어떤 컨트롤러가 처리할 지 질의
3. HandlerMapping은 요청에 응답
4. 3에서 지정받은 컨트롤러로 DispatcherServlet가 처리요청
5. 해당 컨트롤러는 ModelAndView를 리턴
6. DispatcherServlet는 ViewResolver로 어떤 View가 나의 요청을 처리할 건지 질의
7. ViewResolver는 View 객체를 반환
8. DispatcherServlet는 해당 View를 이용해 응답생성 요청을 전송
9. View는 요청을 받아 JSP를 생성하여 반환

-----------

#### :bulb: 어노테이션이란 무엇일까요? (복습) by 주연

-----------

#### :bulb: @ContextConfiguration, @Configuration, @Bean에 대하여 알려주세요 by 주연

-----------

#### :bulb: Sql Service를 만들면서 Sql 파일의 경로를 유저로부터 지정받도록 하기 위해 사용한 방법에 대해 설명하시오 by 대연

내부에서 특정 빈을 생성할 때 Sql 파일 위치와 같은 프로퍼티를 외부에서 유저가 직접 지정할 수 있도록 만들고 싶다면,
외부에서 DI 받은 config 객체에서 값을 가져와 빈을 생성할 수 있도록 코드를 작성합니다.
해당 라이브러리를 사용하는 유저는 @Bean으로 해당 config 객체에 원하는 파일위치를 담아 빈으로 등록하면 됩니다.

-----------

#### :bulb: 중첩 클래스를 이용한 프로파일 적용에 대해 설명하세요 by 대연

-----------

#### :bulb: 프로퍼티 소스에 대해서  by 대연

-----------
