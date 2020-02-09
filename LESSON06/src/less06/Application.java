package less06;

public class Application {
	
	static MyThread MyThread1;
	static MyThread MyThread2;
	
	public static void main(String[] args) {
	
		MyThread1 = new MyThread("Поток №1");
		MyThread2 = new MyThread("Поток №2");
		
		Thread thread1 = new Thread(MyThread1);
		Thread thread2 = new Thread(MyThread2);
		
		thread1.start();
		thread2.start();
		
		while (thread1.isAlive() & thread2.isAlive()) {}
		
		if (MyThread1.getRndSum() > MyThread2.getRndSum()) {
			System.out.println("Победил " + MyThread1.myname);
		} else if (MyThread1.getRndSum() < MyThread2.getRndSum()) {
			System.out.println("Победил " + MyThread2.myname);
		} else System.out.println("НИЧЬЯ!");
	}

}