package multipleThread;

public class SimpleThread extends Thread{
	
	private int countDown=5;
	private static int threadCount=0;
	private int threadNumber=++threadCount;
	
	@Override
	public void run() {
		while(true) {
			System.out.println("Thread "+threadNumber+"("+countDown+")");
			if(--countDown==0)
				return;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		for (int i=0;i<5;i++)
			new SimpleThread().start();
		System.out.println("All Threads started");
	}

}
