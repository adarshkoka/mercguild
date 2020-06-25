package frame.utils;

public class GTimer {
	
	private boolean isPaused;
	private long startTime, elapsedTime, pauseTime;
	
	public GTimer() {}
	
	public void start() {
		if (startTime == 0)
			pauseTime = startTime = System.currentTimeMillis();
	}
	
	public void resume() {
		pauseTime = System.currentTimeMillis();
		isPaused = false;
	}
	
	public void pause() {
		elapsedTime += -(pauseTime - (pauseTime = System.currentTimeMillis()));
		isPaused = true;
	}
	
	public long getStartTimeMillis() {
		return startTime;
	}
	
	public long getElapsedTimeMillis() {
		if (!isPaused && startTime != 0) 
			return elapsedTime + System.currentTimeMillis() - pauseTime;
		return elapsedTime;
	}
}
