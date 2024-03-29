# 6주차  

#### :bulb: 단일 책임 원칙에 대해서 설명해 주세요. by.태현   

단일 모듈은 변경의 이유가 하나뿐이어야 하며, 하나의 모듈은 하나의 actor에 대해서만 책임져야 한다는 원칙.

--------

#### :bulb: 트랜잭션 동기화의 개념과 그 효과? by.태현

트랜잭션이란 논리적인 작업 단위를 의미합니다. DB에서 자주 사용되는 단어로 JDBC에서는 트랜잭션을 관리하는 객체는 Connection 객체입니다. 트랜잭션 동기화는 트랜잭션을 시작하기 위한 Connection 객체를 특별한 저장소에 보관해두고 필요할 때 꺼내쓸 수 있도록 하는 기술입니다. 트랜잭션 동기화 저장소는 작업 쓰레드마다 Connection 객체를 독립적으로 관리하기 때문에, 멀티쓰레드 환경에서도 충돌이 발생할 여지가 없다는 것을 가장 큰 효과로 들 수 있습니다.

--------

#### :bulb: 객체지향이 지향하는 프로그래밍 방법에 대해서 알려주세요 by.주연

각 객체가 가지고 있는 데이터나 상태를 조작하거나 그 데이터를 이용한 산출물이 필요한 경우
외부에서 객체의 데이터를 직접 가져와 조작하는 대신, 해당 객체에게 변경을 요청하는 원칙입니다.

--------

#### :bulb: Service와 Dao를 구분하는 이유를 설명해 주세요 by.주연

dao는 DB의 CRUD를 담당하여 Service에 호출되어 동작합니다.  
반면에 Service는 Controller에 의해 호출되어, 비즈니스 로직과 트랜잭션을 처리를 담당합니다.

단순한 데이터 처리는 Service와 dao의 차이가 없을 수 있지만,  

Service는 사용자가 요청한 작업을 처리하는 과정을 하나의 작업으로 묶은 것이고, dao은 CRUD 작업만 담당하여, 하나씩 분할 해 놓은 것이다.

만약 select와 update가 동시에 사용하여 여러 데이터 로직을 처리해야 한다면, 여러 dao를 service안에 조립하여 로직을 처리 할 수 있다.

--------

#### :bulb: 멀티쓰레드 환경에서 안전한 트랜젝션을 구현하기 위한 방법과 절차 by.대연

1) Service : Connection 생성  
2) Service : Connection을 TransactionSynchronizations에 저장  
3) JdbcTemplate: db접근  
4) JdbcTemplate: 트랜잭션 동기화 저장소에 현재 시작된 트랜잭션을 가진 Connection 오브젝트가 존재하는지 확인  
5) JdbcTemplate: 저장소의 Connection으로 PreparedState를 만들고 sql실행  
6) Service: 모든 db 작업이 끝나면 commit  
7) TransactionSynchronizations: 저장된 Connection 제거  
8) Service: 동기화 상태 해체   

--------

#### :bulb: PlatformTransactionManager의 설명 by.대연

PlatformTransactionManager는 트랜잭션 경계설정을 위한 추상 인터페이스입니다. JDBC같은 경우는 먼저 Connection을 생성하고 트랜잭션을 시작합니다. 하지만 PlatformTransactionManager은
트랜잭션을 가져오는 요청인 getTransaction() 메소드를 호출하기만 하면 됩니다. 따라서 어떤 데이터 액세스 기술에 따라서도 사용에 문제가 없고, DB Connection까지 가져오는 작업도 해주는 트랜잭션 매니저라고 요약할 수 있습니다. 

--------

#### :bulb: Enum에 대해 아는 대로 설명해 주세요. by 정수

C/C++의 ENUM은 스트링을 정수같이 사용할 수 있는 기능이 있습니다.
Java에서는 더 강력한 기능을 지원하고, Enum이 하나의 클래스고 각 상수는 인스턴스의 개념입니다.
불변 클래스입니다.
각 상수는 값 뿐 아니라 메서드까지 담을 수 있습니다. abstract 메서드로 Enum에 선언하고 각 상수에서 override하는 방식으로 각 상수만의 메서드를 구현할 수 있습니다.
enum 사용의 장점은 컴파일 타임에 잘못된 enum 사용을 체크할 수 있다는 점입니다.

--------

#### :bulb: final static int보다 enum을 사용하는 것이 더 나은 이유는? by 정수

열거 타입은 컴파일 타임에서의 타입 안전성을 제공합니다.
예를 들어 위의 예제에서 Apple 열거 타입을 매개변수로 받는 메서드를 선언했다면, 건네받은 참조는 Apple의 세 가지 값 중 하나임이 확실합니다. 다른 타입의 값을 넘기려 하면 컴파일 오류가 발생합니다.
또한 관심사 분리도 가능합니다. 
이번 실습에 내용 Level Enum에서, 다음 레벨의 정보도 스스로 오브젝트에 저장하여, 서비스 단에서 다음 레벨의 정보를 처리할 필요 없이 가지고 있을 수 있습니다.

--------
