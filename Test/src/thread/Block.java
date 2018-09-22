package thread;

public class Block extends Thread{
	
	private int id = 0;
	
	public static int count = 0;
	
	public static Object lock = new Object();
	
	public Block(int ID) {
		this.id = ID;
	}

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Thread started.");
		
//		Thread.sleep(1000);
//		
//		System.out.println("Sleep ended.");
		for(int i = 0;i < 100; i++) {
			Block block = new Block(i);
			block.start();
		}
	}
	
	public void run() {
		System.out.println("Thread block "+id+" started");
		
		try {
			for(int i = 0; i < 10; i++) {
//				count(this.id);
				
				synchronized(Block.class) {
					System.out.println("accumulator accessed by thread " + id + ", current count is: "+ count++);
				}
				
				Block.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Thread block "+id+" ended");
	}
	
	public static synchronized void count(int id) {
		System.out.println("accumulator accessed by thread " + id + ", current count is: "+ count);
		count++;
	}
}
