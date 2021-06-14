package pj.cars;

import pj.ASIC.AbstractCar;
import pj.interfaces.Car;

/**
 * 추상 골격 클래스를 사용하여 만든 하나의 자동차 클래스
 * 
 * @author taxol
 * @since 2021.05.16
 * @version 1.0
 */
public class Avante extends AbstractCar implements Car{
	/**
	 * 자동차의 가속력에 따르게 속도를 증가시킨다. 
	 * 
	 * @throws Exception 만약 시동이 꺼져 있을 때
	 */
	@Override
	public void accelPedal() throws Exception{
		try {
			if(engineState == State.off) {
				sb.append("시동을 켜주세요").append("\n");
				throw new Exception();
			}
			else {
				speed += 5;
				sb.append("현재 속도 : ").append(getSpeed()).append("\n");
				spendFuel(fuelEfficiency/10);
			}
		}catch (Exception e) {
			System.out.println(sb.toString());
			System.exit(0);
		}
	}
	/**
	 * 자동차의 제동 성능에 따르게 속도를 감소시킨다. 
	 */
	@Override
	public void breakPedal() {
		speed -= 5;
		if(speed < 0) {
			speed = 0;
		}
		System.out.println("현재 속도 : " + getSpeed());
	}
	/**
	 * 자동차를 구분하기 위한 메서드 
	 */
	@Override
	public void sayHello() {
		System.out.println("이 차는 Hyundi Avante 입니다.");
	}
	
	/**
	 * 자동차를 생성하는데 필요한 빌더 패턴 
	 */
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
		
		public Avante build() {
			return new Avante(this);
		}
	}
	/**
	 * 빌더 패턴을 받는 생성자
	 * 
	 * @param builder 를 이용하여 생성한다.
	 */
	private Avante(Builder builder) {
		fuel = builder.fuel;
		fuelCapacity = builder.fuelCapacity;
		fuelEfficiency = builder.fuelEfficiency;
		
		// 옵션
		selfDriving = builder.selfDriving;
		ABS = builder.ABS;
		ESP = builder.ESP;
		ECS = builder.ECS;
		TCS = builder.TCS;
		
		engineState = builder.engineState;
		fuelType = builder.fuelType;
		type = builder.type;
		
		carInfo = builder.carInfo;
	}
	
}
