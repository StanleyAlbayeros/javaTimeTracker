package core;

import java.io.Serializable;
//import java.util.Calendar;
import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;

public class Clock extends Observable implements Serializable {	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean running = false;
	
    private Timer timer;

	private long length = 0;
	
	private static long interval = 2000;

	private static Date currentDate;

	private static long intervalLength;

	public Clock(){		
		timer = null;
		running = false;
		length = -1;   
	}

	public void stop(){
		running = false;
	}


	private TimerTask notifier = new TimerTask() {
		public void run() {
			if (running) {
				intervalLength = intervalLength + interval;
				setCurrentDate(new Date());
				setChanged();
				notifyObservers();
			}
		}
	};

    public void start(){
            running = true;
            if(length == -1){
                    timer = new Timer();
                    timer.scheduleAtFixedRate(notifier, 0, interval);
            }
            length = 0;
    }

	public boolean isRunning() {
		return running;
	}
	
    public Timer getTimer() {
		return timer;
	}
 
    public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long nlength) {
		this.length = nlength;
	}

    public static Date getCurrentDate() {
        return currentDate;
    }
    
    public static void setCurrentDate(Date ncurrentDate) {
        Clock.currentDate = ncurrentDate;
    }

    public static long getIntervalLength() {
		return intervalLength;
	}

    public static void setIntervalLength(long nintervalLength) {
		Clock.intervalLength = nintervalLength;
	}

    public void setRunning(boolean nrunning) {
		this.running = nrunning;
	}
}

