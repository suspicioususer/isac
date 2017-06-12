package assignments.chapter5;

import java.io.IOException;

public class KeyLoggerThread extends Thread {

	private Thread t;
	private String threadName;

	KeyLoggerThread(String name) {
		threadName = name;
		System.out.println("Creating " + threadName);
	}

	public void run() {
		System.out.println("Running " + threadName);
		try {
			BadKeyLogger.main(null);
		} catch (IOException e) {
			e.printStackTrace();
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
