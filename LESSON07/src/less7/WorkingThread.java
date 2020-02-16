package less7;

class WorkingThread implements Runnable
{
    private Mine counter;
    private String name;
    private int monet = 0;
    private int production = 3;
    
	public WorkingThread(Mine counter, String name) {
        this.counter = counter;
        this.name = name;
    }
    
    @Override
    public void run() {
        while (counter.getCounter() > 0) {
            if (counter.getCounter() > production) {
            	monet = monet + production;
            } else {
            	monet = monet + counter.getCounter();
            };
            counter.increaseCounter();
            System.out.println(name + " намайнил " + monet + " монет. "
            		+ "В шахте осталось: " + counter.getCounter() + " монет");

            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            
        }
        System.out.println(name + " намайнил " + monet + " монет");
    }
}