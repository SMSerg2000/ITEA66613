package less06;

public class MyThread implements Runnable {
	int rndSum, p1, p2=0;
	String myname;
	
	public MyThread(String myname) {
		this.myname = myname;
	}

	@Override
	public void run() {
		for (int i=0; i<5; i++) {
			int myrnd=(int)(Math.random()*100);
			rndSum = myrnd + rndSum;
			try {
				System.out.println(myname + " на шаге " + (i+1) + " сгенерировал число: " + myrnd);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(myname +  " завершен! " + "Сумма сгенерированных чисел равна: " + rndSum);
	}
	
	public int getRndSum() {
		return rndSum;
	}

}