package assignments.chapter5;

public class TestClass {

	public static void main(String[] args) {
		KeyLoggerThread keyLoggerThread = new KeyLoggerThread("KeyLogger");
		keyLoggerThread.start();
		ScreenLoggerThread screenLoggerThread = new ScreenLoggerThread("ScreenLogger");
		screenLoggerThread.start();
	}
}
