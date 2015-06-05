package ld32.tools;

public class TestTimer {

	public static void main(String[] args)
	{
		Timer timer = new Timer();
		for (int i=0; i<100; i++)
		{
			System.out.println(i + ": " + timer.getMilSecs());
		}
	}
}