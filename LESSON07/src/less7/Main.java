package less7;

public class Main 
{
    public static void main(String[] args) {
    	Mine working = new Mine();
        for(int i=1; i<6; i++) {
            Thread ct = new Thread(new WorkingThread(working, "Ðàáî÷èé ¹ " + i));
            ct.setName("Ðàáî÷èé ¹ " + i);
            System.out.println("Èç áàðàêà âûïóùåí " + ct.getName());
            ct.start();    
            System.out.println("ÊÎËÈ×ÅÑÒÂÎ ÏÎÒÎÊÎÂ: " + Thread.activeCount());
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        }
    }
}