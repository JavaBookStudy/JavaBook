# 6주차  

#### :bulb: 단일 책임 원칙에 대해서 설명해 주세요. by.태현   

단일 모듈은 변경의 이유가 하나뿐이어야 하며, 하나의 모듈은 하나의 actor에 대해서만 책임져야 한다는 원칙.

--------

#### :bulb: 트랜잭션 동기화의 개념과 그 효과? by.태현

--------

#### :bulb: 객체지향이 지향하는 프로그래밍 방법에 대해서 알려주세요 by.주연

--------

#### :bulb: Service와 Dao를 구분하는 이유를 설명해 주세요 by.주연

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

--------

#### :bulb: Enum에 대해 아는 대로 설명해 주세요. by 정수

--------

#### :bulb: final static int보다 enum을 사용하는 것이 더 나은 이유는? by 정수

--------
