package pj.ASIC;

import pj.interfaces.Car;

/**
 * 하나의 자동차를 생성하기 윈한 추상 골격 클래스.
 * 
 * For example:
 * <pre>
 * public class CarName extends AbstractCar implements Car{
 * 
 * }
 * </pre>
 * 
 * @author Taxol
 * @since 2021.05.16
 * @version 1.0
 * @see pj.cars.G70
 */
public abstract class AbstractCar implements Car{
	/**
	 * 현재 자동차의 동적인 정보  : 속도
	 * default는 0이다.
	 * 
	 * @see #getSpeed()
	 */
	protected int speed;
	/**
	 * 현재 자동차의 동적인 정보  : 현재 연료의 양
	 * 
	 * @see #getFuel()
	 * @see #fillFuel(int)
	 * @see #spendFuel(int)
	 * @see #outofFuel()
	 */
	protected int fuel;
	/**
	 * 현재 자동차의 동적인 정보  : 최대 연료 용량
	 * 
	 * @see #getFuelCapacity()
	 */
	protected int fuelCapacity;
	/**
	 * 현재 자동차의 동적인 정보  : 연비
	 */
	protected int fuelEfficiency;				
	
	/**
	 * 자동차의 옵션이며 True or False로 구분한다. <br>
	 * 자율 주행 옵션을 나타낸다.
	 */
	protected boolean selfDriving;
	/**
	 * 자동차의 옵션이며 True or False로 구분한다. <br>
	 * 잠김 방지 브레이크 시스템
	 */
	protected boolean ABS;
	/**
	 * 자동차의 옵션이며 True or False로 구분한다. <br>
	 * Electronic Stability Program
	 */
	protected boolean ESP;
	/**
	 * 자동차의 옵션이며 True or False로 구분한다. <br>
	 * 기계식 현가장치.
	 */
	protected boolean ECS;
	/**
	 * 자동차의 옵션이며 True or False로 구분한다. <br>
	 * Traction control system
	 */
	protected boolean TCS;
	
	/**
	 * 자동차의 시동 상태. on은 켜짐, off는 꺼짐
	 */
	public enum State {on, off};		// 열거 타입 사용
	/**
	 * @see #turnOffEngine()
	 * @see #turnOnEngine()
	 */
	protected State engineState; 
	/**
	 * 자동차의 연료 종류. 휘발유와 경유
	 */
	public enum FT {Gasoline, Diesel};		// 연료 종류
	protected FT fuelType; 
	/**
	 * 자동차의 차급. A세그먼트 부터 S 세그먼트
	 */
	public enum Type {A, B, C, D, E, F, S};	// 차급
	protected Type type;
	
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
	
	/**
	 * 시동을 켜는 메서드. 연료가 없으면 시동이 걸리지 않는다.
	 * @see #outofFuel()
	 */
	@Override
	public void turnOnEngine() {
		if(engineState == State.off && !outofFuel()) {
			engineState = State.on;
			speed = 0;
			System.out.println("시동이 켜짐");
		}
		else {
			System.out.println("연료 부족!");
		}
	}
	/**
	 * 시동을 끄는 메서드
	 */
	@Override
	public void turnOffEngine() {
		if(engineState == State.on) {
			engineState = State.off;
			speed = 0;
			sb.append("시동이 꺼졌습니다.").append("\n");
		}
	}
	/**
	 * 현재 연료의 양을 반환한다.
	 * 
	 * @return fuel
	 */
	@Override
	public int getFuel() {
		return fuel;
	}
	/**
	 * 총 연료통의 용량을 반환한다.
	 * 
	 * @return fuelCapacity
	 */
	@Override
	public int getFuelCapacity() {
		return fuelCapacity;
	}
	/**
	 * 연료를 채우는 메서드
	 * 
	 * @param fuel 만큼 연료를 채운다.
	 */
	@Override
	public void fillFuel(int fuel) {
		this.fuel += fuel;
		if(this.fuel > fuelCapacity) {
			int fuelRemaining = this.fuel - fuelCapacity;
			this.fuel = fuelCapacity;
			System.out.println("연료가 꽉참");
		}
		System.out.println("현재 연료: " + getFuel());
	}
	/**
	 * 연료를 사용하는 메서드
	 * 
	 * @param fuel 만큼 연료를 사용한다.
	 * @throws Exception when fuel가 0 이하일 때
	 */
	@Override
	public void spendFuel(int fuel) throws Exception{
		try {
			this.fuel -= fuel;
			if(this.fuel <= 0) {
				throw new Exception();
			}
		}
		catch(Exception e){
			sb.append("연료가 바닥 났습니다.").append("\n");
			turnOffEngine();
			fuel = 0;
		}
	}
	/**
	 * 현재 연료의 유무를 알려주는 메서드
	 * @return true 만약 연료가 0 미만이면
	 */
	@Override
	public boolean outofFuel() {
		if(fuel <= 0)
			return true;
		else
			return false;
	}
	/**
	 * 현재 속도를 반환하는 메서드
	 * @return speed
	 */
	@Override
	public int getSpeed() {
		return speed;
	}
	/**
	 * 시뮬레이션을 위해 n초 동안 가속하는 메서드
	 * @param n 초만큼 가속한다.
	 */
	@Override
	public void process(int n)  throws Exception{
		for(int i = 0; i < n; i++)
			accelPedal();
		
		System.out.println(sb.toString());
		sb.setLength(0);
	}
	
}
