package assignments.chapter5;

public class ScreenLoggerThread extends Thread {
	private Thread t;
	private String threadName;

	ScreenLoggerThread(String name) {
		threadName = name;
		System.out.println("Creating " + threadName);
	}

	public void run() {
		System.out.println("Running " + threadName);
		for (int i = 0; i < 4; i++) {
			try {
				ScreenLogger.count = String.valueOf(i);
				ScreenLogger.main(null);
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		System.out.println("Thread " + threadName + " run without exception.");
	}

	public void start() {
		System.out.println("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}

	}
}
