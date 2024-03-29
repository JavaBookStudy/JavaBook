# 8주차  

#### :bulb: 트랜잭션 경계설정 코드를 분리함으로써 얻을 수 있는 장점을 말해주세요 by.주연  

비즈니스 로직을 담당하는 userServiceImpl은 트랜잭션과 같은 기술적인 내용에 신경을 쓰지 않아도 도니다. 그저 DI를 이용하여 트랜잭션 기능을 가진 오브젝트를 실행하고 실제 비즈니스는 타겟 오브젝트에 위임하면 된다.
-> **객체지향의 단일 책임 원칙을 잘 지킬 수 있다.**

--------

#### :bulb: 고립 테스트를 해야하는 이유를 말해주세요 by.주연  

고립 테스트가 등장하게 된 배경 :
비즈니스 로직을 테스트하고자 하는데, DB까지 참여해야 하는, 테스트 관심사 외부의 환경이나 오브젝트가 참여하는 통합 테스트의 경우,
테스트가 실패했을 때 로직의 문제인지 그 외부의 문제인지 알기 어려운 점이 있음. 관심사는 좁은데 디버깅은 전체 기술 스택을 다 봐야하는 경우.
또한 DB 혹은 외부 네트워크에 접속해야 하는 경우 테스트 수행시간이 긴 문제가 있음

고립 테스트는 이러한 문제를 해결하고자, 테스트 대역 및 목 오브젝트를 생성하여 외부의 자원이나 환경에 종속되지 않는 테스트 보장
그래서 테스트의 관심사를 확실하게 좁혀서 수행할 수 있어 디버깅의 범위가 관심사 내부로 제한되는 효과는 물론,
외부 자원이 연관되어 있지 않기 때문에 빠른 테스트 수행이 가능

--------

#### :bulb: 프록시 패턴이란? by 정수

프록시란 클라이언트에게 마치 자신이 사용하려고 하는 실제 대상, 즉 타겟인 것처럼 동작하여, 요청을 받아주는 역할을 합니다. 이를 프록시라고 부릅니다
이를 통해 같은 타겟과 같은 인터페이스를 구현하여, 클라이언트가 타겟에 접근하는 방법을 제어할 수 있습니다.
프록시를 통해 접근 권한을 제어하거나, 생성을 지연하여 필요할 때 생성하는 식으로 사용할 수 있습니다.

--------

#### :bulb: 데코레이터 패턴이 무엇인지, 언제 유용하게 쓰이는지 말해주세요. by 정수

프록시 사용법 2가지 중 하나로, 타겟에 부가적인 기능을 타겟의 코드 수정 없이 다이나믹하게 부여하기 위한 패턴입니다.
코드 상으로는 어떤 방법과 순서로 프록시와 타겟이 연결되는지 정해져 있지 않으며, 요청과 타겟 사이의 프록시 갯수의 제한도 없어
프록시1->프록시2->프록시3->...->타겟과 같은 기능도 수행이 가능합니다.


---------

#### :bulb: 일반적으로 스프링 Bean 오브젝트는 어떻게 관리되고, 팩토리 메서드 패턴으로 생성되는 오브젝트는 왜 Bean으로 등록 불가한지, 이 문제를 해결하기 위한 팩토리 Bean을 설명하시오. by 대연

스프링 빈은 기본적으로 클래스 이름을 가지고 리플렉션을 이용하여 해당 클래스의 오브젝트를 만들지만, 팩토리 메서드 패턴으로 생성되는 오브젝트는 다이나믹하게 내부에서 새롭게 정의해서 사용하기 때문에 사전에 클래스에 대한 정보를 알 수 없습니다.
따라서, 팩토리 빈이라는 인터페이스를 사용하여, 실제 빈의 오브젝트 대신에 팩토리 빈 인터페이스가 만들어주는 오브젝트를 빈 처럼 사용할 수 있습니다.


---------

#### :bulb: 자바 리플렉션은 무엇인지 설명하세요. by 대연

자바의 오브젝트 내부에는 클래스의 메타데이터를 가지고 있는 Class 객체가 존재합니다.
이 class 객체를 다루는 API로 리플렉션을 이용하며, 자바 DI 컨테이너 또한 이를 활용합니다.

- 클래스의 이름
- 클래스 상속 정보
- 인터페이스 구현
- 필드의 목록과 타입
- 메서드의 목록과 시그니쳐
- 오브젝트 필드 값 읽기/쓰기
- 메서드 호출

등을 할 수 있습니다.

---------

#### :bulb:  @Autowired가 사용할 Bean을 특정하는 과정에 대해 설명하세요. by 대연

이 어노테이션을 부여하면 각 상황의 타입에 맞는 IoC컨테이너 안에 존재하는 Bean을 자동으로 주입 
1.	Context 파일을 로딩하고 빈으로 생성되어아 햐는 객체들을 전부 읽어드린다.
2.	빈간 의존관계를 포함해 생성할 빈을 트리를 통해 목록화
3.	Bean 설정 정보 생성하고 의존성 주입
4.	그리고 BeanPostProcessor라는 인터페이스를 통해 빈을 초기화 한다

---------

#### :bulb: 다이나믹 프록시가 무엇인지 설명하세요. by 대연

리플렉션 API를 이용하여 프록시를 구현합니다.

---------

