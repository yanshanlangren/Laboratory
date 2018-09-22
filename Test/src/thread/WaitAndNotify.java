package thread;

public class WaitAndNotify extends Thread{

	private int id = -1;
	
	public static int count = 0;
	
	public WaitAndNotify(int ID) {
		this.id = ID;
	}
	
	public static void main(String[] args) {
		System.out.println("Start to create 100 threads.");
		WaitAndNotify[] arr = new WaitAndNotify[100];
		for(int i=0;i<5;i++) {
			System.out.println("Thread "+i+" created.");
			arr[i] = new WaitAndNotify(i);
			arr[i].start();
		}
		System.out.println("All threads created.");
	}

	public synchronized void run() {

		if(this.id == 4) {
			this.notifyAll();
		}
		else {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		while(true) {
			
			System.out.println("Thread "+this.id+" get count, count is "+count);

			this.notifyAll();
			
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
