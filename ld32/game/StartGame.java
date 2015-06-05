package ld32.game;

public class StartGame 
{
	public static void main(String[] args)
	{
		World world  = new World();
		GameLoop gameLoop = new GameLoop();
		GameLoop.linkWorld = world;
		gameLoop.start();
	}

}