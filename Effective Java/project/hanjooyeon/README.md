## 주제  
인터페이스와 추상 골격 구현 클래스를 이용한 자동차 주행 시뮬레이션

### 개요  
이펙티브 자바에서 배운, 인터페이스와 추상 골격 구현 클래스, 빌더 패턴을 기반으로 하여 클래스 들을 설계한다.  
설계 과정에서, 다양한 공부 내용을 적용해 보며 복습해 보는 과정을 거친 프로젝트이다.  

### 프로젝트 구조  

#### Interface
추상 골격 클래스들에 사용되며, 모든 클래스들에 공통적으로 사용되는 메서드들을 선언한다.

#### Abstract Skeletal Implementation Class
Interace로 선언한 메서드들을 구현 형태로 제공하도록 만든다.

#### lots of cars
추상 골격 구현 클래스를 상속 받아 생성하는 클래스   
수 많은 매개변수를 효율적이게 받기 위해 `Builder` 패턴을 이용한다.  

#### Simulation
구현한 자동차 클래스들을 이용하여, 실제로 동작을 시켜보며 클래스 설계에 문제가 없는지 확인한다.

## 적용 Item 

#### Item 2. 생성자에 매개변수가 많다면 빌더를 고려하라 

```java
public static class Builder{
		protected int fuel = 0; 
		protected int fuelCapacity;
		protected int fuelEfficiency;				// 연비
		
		protected State engineState = State.off;
		protected FT fuelType;
		protected Type type;
		
		protected Info carInfo;
		
		// 옵션 
		protected boolean selfDriving = false;		// 자율 주행
		protected boolean ABS = false;
		protected boolean ESP = false;
		protected boolean ECS = false;
		protected boolean TCS = false;
		
		public Builder(Type type, FT fuelType, int fuelCapacity, int fuelEfficiency, Info carInfo) {
			this.type = type;
			this.fuelType = fuelType;
			this.fuelCapacity = fuelCapacity;
			this.fuelEfficiency = fuelEfficiency;
			this.carInfo = carInfo;
		}
		
		public Builder selfDriving(boolean flag) {
			selfDriving = flag;
			return this;
		}
		
		public Builder ABS(boolean flag) {
			ABS = flag;
			return this;
		}
		
		public Builder ESP(boolean flag) {
			ESP = flag;
			return this;
		}
		
		public Builder ECS(boolean flag) {
			ECS = flag;
			return this;
		}
		
		public Builder TCS(boolean flag) {
			TCS = flag;
			return this;
		}
		
		public G70 build() {
			return new G70(this);
		}
	}
```
-> 빌더패턴을 사용하여 수 많은 생성자의 매개변수를 효율적으로 받아드릴 수 있도록 하였다.  

#### Item 20. 추상 클래스보다는 인터페이스를 우선하라

인터페이스와 추상 골격 구현 클래스를 사용하여, 여러 클래스의 중복되는 메서드와 필드를 구현하였다.

**Car**
-> 인터페이스

**AbstractCar**
-> 추상 골격 구현 클래스  

#### Item 51. 메서드 시그니처를 신중히 설계하라

```java
  /**
	 * 메서드 시그니처 설계
	 */
	public static final class Info {
		private int Emission; 					// 배기량
		private int width;						// 전폭	
		private int height;						// 전고
		private int whole_length;				// 전장
		public Info(int emission, int width, int height, int whole_length) {
			super();
			Emission = emission;
			this.width = width;
			this.height = height;
			this.whole_length = whole_length;
		}
		
	}
	protected Info carInfo;
```
-> 같은 자료형의 매개변수가 이어지는 경우, 메서드 시그니처를 클래스로 전달하는 방식으로 구현하였다.

#### Item 56. 공개된 API 요소에는 항상 문서화 주석을 작성하라

JavaDoc을 사용하여 클래스의 매서드, 필드, 그리고 클래스 자체의 설명을 만들었다.
