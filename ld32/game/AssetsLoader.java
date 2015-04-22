package ld32.game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
		Canvas.background = loadImage("backround.png");
		CoffeeHero.stopGivingL = loadImage("coffeWarriorMovmentStadingToL.png");
		CoffeeHero.stopGivingR = loadImage("coffeWarriorMovmentStadingToR.png");
		CoffeeHero.framesL[0] = loadImage("coffeWarriorMovment1ToL.png");
		CoffeeHero.framesL[1] = CoffeeHero.stopGivingL;
		CoffeeHero.framesL[2] = loadImage("coffeWarriorMovment2ToL.png");
		CoffeeHero.framesL[3] = CoffeeHero.stopGivingL;
		CoffeeHero.framesR[0] = loadImage("coffeWarriorMovment1ToR.png");
		CoffeeHero.framesR[1] = CoffeeHero.stopGivingR;
		CoffeeHero.framesR[2] = loadImage("coffeWarriorMovment2ToR.png");
		CoffeeHero.framesR[3] = CoffeeHero.stopGivingR;
		CoffeeHero.givingL = loadImage("coffeWarriorMovmentStadingGivingToL.png");
		CoffeeHero.givingR = loadImage("coffeWarriorMovmentStadingGivingToR.png");
		CoffeeHero.refill[0] = loadImage("refill1.png");
		CoffeeHero.refill[1] = loadImage("refill2.png");
		Enemy.standingL = loadImage("coffeEnemyStandingToL.png");
		Enemy.standingR = loadImage("coffeEnemyStandingToR.png");
		Enemy.framesL[0] = loadImage("coffeEnemyMovment1ToL.png");
		Enemy.framesL[1] = Enemy.standingL;
		Enemy.framesL[2] = loadImage("coffeEnemyMovment2ToL.png");
		Enemy.framesL[3] = Enemy.standingL;
		Enemy.framesR[0] = loadImage("coffeEnemyMovment1ToR.png");
		Enemy.framesR[1] = Enemy.standingR;
		Enemy.framesR[2] = loadImage("coffeEnemyMovment2ToR.png");
		Enemy.framesR[3] = Enemy.standingR;
		//		Canvas.placeholder = loadImage("imidiafanes.png");
		CoffeeMaker.image = loadImage("coffeeMaker.png");
		CoffeeHazard.image = loadImage("placeholder.png");
	}

	private BufferedImage loadImage(String str)
	{
		this.f = new File("resources/" + str);
		try {
			//trying to read so that it works in JAR
			InputStream is = this.getClass().getResourceAsStream("/" + str);
			System.out.println("Loaded " + this.getClass().getResource("/" + str)) ;
			this.img = ImageIO.read(is);
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
