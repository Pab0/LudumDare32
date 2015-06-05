package ld32.tools;

public class Timer
{
	long time;
	
	public Timer()
	{
		this.time = System.nanoTime();
	}
	
	public long update()
	{
		long oldTime = this.time;
		this.time = System.nanoTime();
		long diff = this.time - oldTime;
		return diff;
	}
	
	public float getMilSecs()
	{
		float diff = this.update();
		return diff/1000000;
	}
}
