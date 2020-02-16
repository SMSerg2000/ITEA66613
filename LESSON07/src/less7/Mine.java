package less7;

class Mine
{
    private volatile int counter = 1000;
    private int production = 3;
    
    //Это шахта с 1000 монетами
    
    
    public synchronized void increaseCounter() {
    	//Метод который извлекает из шахты монеты по 3 штуки
    		counter = counter-production;
    }
    
    public int getCounter() {
    	if (counter>0) {
    		return counter;
    	}else return 0;
    }

}