## 주제  
콘솔에서 하는 퀴즈 게임

### 개요  
서버는 퀴즈 문제를 클라이언트에게 보낸다. 이때 문제 객체는 직렬화한다.  
클라이언트는 퀴즈 문제를 풀고 포인트를 적립할 수 있다. 게임 종료시 포인트 정보를 서버로 보낸다.  
서버가 포인트 정보를 클라이언트로부터 받으면 최고 점수를 다시 클라이언트에게 보낸다. 클라이언트 측에서는 최고 점수를 표시한 후 종료한다.  

### 프로젝트 구조  
Server    
- com.pj.main.Main : main 메서드가 있는 클래스  
- com.pj.server.Server : 서버 인터페이스  
- com.pj.server.ServerImpl : 서버 인터페이스 구현 클래스  
- com.pj.server.QuizThread : 퀴즈 문제를 보내주는 스레드  
- com.pj.server.PointThread : 클라이언트 종료시 포인트 정보를 받고 최고 점수를 보내주는 스레드
- com.pj.dto.Quiz : 퀴즈 문제 객체
- com.pj.dto.Point : 클라이언트와 포인트 정보를 담는 객체
  
Client  
- com.pj.main.Main : main 메서드가 있는 클래스  
- com.pj.client.Client : 클라이언트 인터페이스  
- com.pj.client.ClientImpl : 클라이언트 인터페이스 구현 클래스


## 적용 Item  


... 추가중
