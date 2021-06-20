package pj.interfaces;
/**
 * 추상 골격 구현 클래스를 위한 인터페이스.
 * 
 * @author Taxol
 * @since 2021.05.16
 * @version 1.0
 */ 
public interface Car {
	public StringBuilder sb = new StringBuilder();
	void turnOnEngine();
    void accelPedal()  throws Exception;
    void breakPedal();
    int getSpeed();
    void turnOffEngine();
    int getFuel();
    int getFuelCapacity();
    void fillFuel(int fuel);
    void spendFuel(int fuel) throws Exception;
    boolean outofFuel();
    void sayHello();
    void process(int n)  throws Exception;
}
