# 12주차  

#### :bulb: @Annotation의 개념과 XML과 비교했을 때의 장단점을 설명해 주세요. by 정수  

-----

#### :bulb: 내장형 DB의 장단점을 설명해 주세요. by 정수

-----

#### :bulb: ConcurrentHashMap vs HashMap 동기화의 차이 by 대연

HashMap은 조회/수정 상관 없이 객체에 접근할 때마다 lock을 획득해야 합니다.
반면 ConcurrentHashMap은 좀 더 효율적인 동기화 기능을 제공하는데, lock 획득 여부 없이 조회가 가능하고 수정할 때는 수정 대상 데이터에만 lock 관리를 해 줍니다.

-----

#### :bulb: <tx:annotation-driven /> 등의 특별한 태그를 애너테이션으로 대체할 수 있는 방법? by 대연

-----

#### :bulb: @Autowired가 부착된 필드에 주입할 빈을 특정하는 3단계 (복습) by 대연

-----

#### :bulb: 메타 애너테이션이 뭔지를 설명하고, @Component가 메타 애너테이션으로 부착된 애너테이션 예시 최소 3개 by 대연

메타 애너테이션은 애노테이션을 정의할 때 붙이는 애노테이션으로, 여러 개의 메타 애노테이션의 정의를 하나의 애노테이션을 붙였을 때 모두 적용시킬 수 있어 편리합니다. 
@Controller, @Repository, @Service 애너테이션은 모두 @Component가 메타 애너테이션으로 부착되어 있습니다.

-----

#### :bulb: 테스트 상속을 이용하면 좋은 상황과 방법 by 대연

-----

#### :bulb: @ComponentScan 애너테이션으로 클래스패스를 지정하는 이유 by 대연

@ComponentScan으로 빈으로 등록할 컴포넌트의 범위를 지정할 수 있기 때문에 실행 환경(테스트, 운영)에 따라 다른 범위를 지정할 수 있게 됩니다.

-----

#### :bulb: Spring Framework를 사용하는 이유? by 주연

-----

#### :bulb: EmbeddedDbSqlRegistry의 updateSql에서 동시에 sql을 업데이트하면 생기는 문제점과 해결 방안? by 
