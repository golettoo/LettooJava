package cn.lettoo.thread;

public class ThreadTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {

			public void run() {
				System.out.println("in runnable() run()."); 
            }

		});
		Thread t2 = new MyThread();
		//t1.start();
		t2.start();
	}

	static class MyThread extends Thread {
		@Override
		public void run() {
			System.out.println("in MyThread() run().");
		}
	}

}
