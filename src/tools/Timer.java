package tools;

public class Timer
{
	long time;
	
	protected Timer()
	{
		this.time = System.nanoTime();
	}
	
	protected long update()
	{
		long oldTime = this.time;
		this.time = System.nanoTime();
		long diff = this.time - oldTime;
		return diff;
	}
	
	protected float getMilSecs()
	{
		float diff = this.update();
		return diff/1000000;
	}
}
