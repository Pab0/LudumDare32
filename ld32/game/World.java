package ld32.game;

import java.util.LinkedList;
import java.util.Random;

public class World {

	static final short STARTING_SCREEN = 10;
	static final short PLAYING_SCREEN = 20;
	static final short ENDING_SCREEN = 30;
	
	private static final float ENEMY_SPAWN_RATE = 3000;
	private float spawnTimer = 0;
	
	Random random;
	AssetsLoader assetsLoader;
	public short gameMode;
	public LinkedList<Enemy> enemiesList;
	CoffeeMaker cM;
	CoffeeHero cH ;
	Canvas canvas; 
	 
	 public World(){
		 this.random = new Random();
		 this.assetsLoader = new AssetsLoader();
		 this.assetsLoader.loadAssets();
		 this.cH = new CoffeeHero(this);
		 this.enemiesList = new LinkedList<Enemy>();
		 this.cM = new CoffeeMaker(this);
		 this.setGameMode(World.STARTING_SCREEN);
		 this.canvas = new Canvas(this);
	 }
	 
	 public void setGameMode(short gameMode)
	 {
		 this.gameMode = gameMode;
	 }
	 
	 public void checkForEnemyCreation(float loopTime)
	 {		 
		spawnTimer += loopTime;
		if (spawnTimer > World.ENEMY_SPAWN_RATE)
		{
			spawnTimer -= World.ENEMY_SPAWN_RATE;
			byte direction = (byte)(random.nextInt(2)*2-1);
			Enemy enemy = new Enemy(this, direction, Enemy.AVERAGE_SPEED);
			this.enemiesList.add(enemy);
		}
	 }

}
