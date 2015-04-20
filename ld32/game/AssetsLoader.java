package ld32.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AssetsLoader {
	
	File f;
	BufferedImage img;
	
	public AssetsLoader()
	{
		
	}
	
	public void loadAssets()
	{
		loadGraphics();
		loadSounds();
	}
	
	private void loadGraphics()
	{
		Canvas.background = loadImage("background.png");
		CoffeeHero.framesL[0] = loadImage("coffeeWarriorMovement1ToL.png");
		CoffeeHero.framesL[1] = loadImage("coffeeWarriorMovement2ToL.png");
		CoffeeHero.framesR[0] = loadImage("coffeeWarriorMovement1ToR.png");
		CoffeeHero.framesR[1] = loadImage("coffeeWarriorMovement2ToR.png");
		CoffeeHero.givingL = loadImage("coffeeWarriorMovementStoppedGivingToL.png");
		CoffeeHero.givingR = loadImage("coffeeWarriorMovementStoppedGivingToR.png");
		CoffeeHero.stopGivingL = loadImage("coffeeWarriorMovementStoppedToL.png");
		CoffeeHero.stopGivingR = loadImage("coffeeWarriorMovementStoppedToR.png");
		CoffeeHero.refill[0] = loadImage("refill1.png");
		CoffeeHero.refill[1] = loadImage("refill2.png");
		Enemy.standingL = loadImage("coffeeEnemyStandingToL.png");
		Enemy.standingR = loadImage("coffeeEnemyStandingToR.png");
		Enemy.framesL[0] = loadImage("coffeeEnemyMovement1ToL.png");
		Enemy.framesL[1] = Enemy.standingL;
		Enemy.framesL[2] = loadImage("coffeeEnemyMovement2ToL.png");
		Enemy.framesL[3] = Enemy.standingL;
		Enemy.framesR[0] = loadImage("coffeeEnemyMovement1ToR.png");
		Enemy.framesR[1] = Enemy.standingR;
		Enemy.framesR[2] = loadImage("coffeeEnemyMovement2ToR.png");
		Enemy.framesR[3] = Enemy.standingR;
		//TODO CoffeeMaker.image =  
		Canvas.placeholder = loadImage("placeholder.png");
		CoffeeMaker.image = loadImage("coffeeMaker.png");
		
//		Enemy.frames[0] = loadImage("enemy.png");
		//TODO etc
	}
	
	private BufferedImage loadImage(String str)
	{
		this.f = new File(str);
		try {
			this.img = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.img;		
	}
	
	private void loadSounds()
	{
		//not gonna happen lol
	}

}
