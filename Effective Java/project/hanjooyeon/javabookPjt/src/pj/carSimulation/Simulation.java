package pj.carSimulation;

import java.util.Scanner;

import pj.ASIC.AbstractCar.FT;
import pj.ASIC.AbstractCar.Info;
import pj.ASIC.AbstractCar.Type;
import pj.cars.G70;
import pj.interfaces.Car;

public class Simulation {
	
	public static void main(String[] args) throws Exception {
		Car g70 = new G70.Builder(Type.E, FT.Gasoline, 80, 10, new Info(2000, 1850, 1400, 4685))
				.selfDriving(true).ABS(true).ECS(true).ESP(true).TCS(true)
				.build();
		
		Scanner sc = new Scanner(System.in);
		int order, num;
		end : while(true) {
			System.out.println("명령을 선택해 주세요");
			System.out.println("1 : 시동 on");
			System.out.println("2 : 가속");
			System.out.println("3 : 브레이크");
			System.out.println("4 : 시동 off");
			System.out.println("5 : 연료 채우기");
			
			order = sc.nextInt();
			switch(order) {
			case 1:
				g70.turnOnEngine();
				break;
			case 2:
				System.out.println("몇 초간 가속?");
				num = sc.nextInt();
				g70.process(num);
				break;
			case 3:
				g70.breakPedal();
				break;
			case 4:
				g70.turnOffEngine();
				break end;
			case 5:
				System.out.println("얼마나 채우실?");
				num = sc.nextInt();
				g70.fillFuel(num);
				break;
			}
			
		}
		sc.close();
	}
}
