package multipleThread;

public class UserThread implements Runnable {
	
	static Object lock=new Object(); 
	
	private int count;
	
	UserThread(int c){
		this.count=c;
		System.out.println("Thread "+c+" created");
	}
	
	public void run() {
		System.out.println("Thread "+this.count+" started");
		synchronized(lock){
			try {
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Thread "+this.count+" has finished executing");
		}
	}
	

	public static void main(String[] args) throws InterruptedException {
		Thread[] tl = new Thread[100];
		for(int i=0;i<10;i++){
			tl[i]=new Thread(new UserThread(i));
		}
		Thread.sleep(1000);
		for(int i=0;i<10;i++){
			tl[i].start();
		}
		Thread.sleep(1000);
		synchronized(lock){
			lock.notifyAll();
		}
		
	}

}
