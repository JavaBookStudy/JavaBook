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
- com.pj.dto.ClientInfo : 클라이언트와 포인트 정보를 담는 객체
- com.pj.dto.RequestDTO : 통신용 Enum
  

Client  
- com.pj.main.Main : main 메서드가 있는 클래스  
- com.pj.client.Client : 클라이언트 인터페이스  
- com.pj.client.ClientImpl : 클라이언트 인터페이스 구현 클래스


## 적용 Item  

#### Item 55. Optional 반환은 신중히 하라   
```
Optional<ClientInfo> info = server.getHighestPoint();
					
	if(!info.isPresent()) { // 최고점이 존재하지 않을 경우(랭크 추가 에러시 클라이언트 점수를 최고점으로 한다)
		oos.writeObject(clientInfo);
	}
	else {// 최고점이 존재할 경우
		oos.writeObject(info.get());
	}
```  
-> Optional 객체에 최고점 플레이어 객체를 담아 보냈음.

#### Item 80. 스레드보다는 실행자, 태스크, 스트림을 애용하라   
```
@Override
	public void runConnector() throws ServerInstanceException{
		// TODO Auto-generated method stub
		execConnector = Executors.newSingleThreadExecutor();
		Runnable runnable = new ConnectorThread();
		execConnector.execute(runnable);
	}
```
-> 서버에서 클라이언트 소켓과 연결하는 스레드, 퀴즈 보내는 스레드를 실행할 때 실행자를 이용하였음.  

