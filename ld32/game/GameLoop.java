package ld32.game;

import ld32.tools.Timer;

public class GameLoop 
{
	boolean running;
	static World linkWorld;
	Timer timer;
	float loopTime;
	
	public GameLoop()
	{
		this.running = true;
		this.timer = new Timer();
	}
	
	void start()
	{
		while (running)
		{
			this.loop();
		}
	}
	
	private void loop()
	{
		this.loopTime = this.timer.getMilSecs();
		updateWorld();
		render();
	}
	
	private void updateWorld()
	{
		//TODO:
		updateHero();
		//updateCoffeeMaker();
		//updateCoffeeBean();
		updateEnemies();
	}
	
	private void updateHero()
	{
		this.linkWorld.cH.act(this.loopTime);
	}
	
	private void updateEnemies()
	{
		linkWorld.checkForEnemyCreation(this.loopTime);
		for (Enemy enemy:linkWorld.enemiesList)
		{
			enemy.act(loopTime);
		}
		//TODO existing enemies
	}
	private void render()
	{
		linkWorld.canvas.repaint();
	}
}
