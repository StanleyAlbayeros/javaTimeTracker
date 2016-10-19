package core;

public class Client {

    public static void main(String[] args) throws InterruptedException {
    	
   	 	Interface t = new Interface();   	
   	 	
        Project p1 = new Project("p1", "project root", root, root.getActivityList());
        
        Task t3 = new BasicTask ("t3", "root project task", p1, p1.getActivityList());
        
        Project p2 = new Project("p2", "root project subproject", p1, p1.getActivityList());
        
        @SuppressWarnings("unused") //remove later
		Task t1 = new BasicTask("t1", "p2 task", p2, p2.getActivityList());
        
        Task t2 = new BasicTask("t2", "p2 task", p2, p2.getActivityList());
	
   	 	try {
			Clock clock = new Clock();
			
			clock.addObserver(t);
			
			clock.start();
			
			t3.stopwatch("interval", "task 3 interval", clock);
			Thread.sleep(3000);
			t3.stopTask(clock);
			Thread.sleep(2000);
			Thread.sleep(7000);
			t2.stopwatch("interval", "task 2 interval", clock);
			Thread.sleep(10000);
			t2.stopTask(clock);
			t3.stopwatch("interval", "task 3 interval", clock);
			Thread.sleep(2000);
			t3.stopTask(clock);
			clock.stop();
		} catch (Exception e) {
			// TODO: handle exception
		}
   	 	
   	 	Interface.printTable(root);
    }
    
	public static Project root = new Project();
	
	public Project getRoot() {
		return root;
	}

	public void setRoot(Project nroot) {
		Client.root = nroot;
	}
}

