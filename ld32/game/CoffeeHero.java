package ld32.game;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class CoffeeHero {

	private static final float STARTING_POSITION = 100;
	private static final int STARTING_COFFEE_AMOUNT = 3;
	private static final int MAX_COFFEE_AMOUNT = 3;
	private static final float MOVING_SPEED = 0.2f;
	private static final float IMAGE_CHANGE_RATE = 250f;
	private static final float HAZARD_PLACING_SPEED = 250f;
	static final short IMAGE_WIDTH = Enemy.IMAGE_WIDTH;
	static final short IMAGE_HEIGHT = Enemy.IMAGE_HEIGHT;

	static BufferedImage[] framesL = new BufferedImage[4];
	static BufferedImage[] framesR = new BufferedImage[4];
	static BufferedImage[] refill = new BufferedImage[2];
	static BufferedImage givingL;
	static BufferedImage givingR;
	static BufferedImage stopGivingL;
	static BufferedImage stopGivingR;

	short curImageIndex;
	BufferedImage curImage;
	private int coffeeAmount;
	private float position;
	byte direction;
	boolean isMoving;
	boolean isRefilling;
	boolean isServing;
	LinkedList<CoffeeHazard> coffeeHazardsList = new LinkedList<CoffeeHazard>(); 
	World linkWorld;
	float animTimePassed;
	float serveTimePassed;

	public int getCoffeeAmount() {
		return coffeeAmount;
	}

	public void setCoffeeAmount(int coffeeAmount) {
		this.coffeeAmount = coffeeAmount;
	}

	public float getPosition() {
		return position;
	}

	public void setPosition(float position) {
		this.position = position;
	}


	public LinkedList<CoffeeHazard> getCoffeeHazardsList() {
		return coffeeHazardsList;
	}

	public void setCoffeeHazardsList(LinkedList<CoffeeHazard> coffeeHazardsList) {
		this.coffeeHazardsList = coffeeHazardsList;
	}


	public CoffeeHero(World linkWorld){
		this.linkWorld = linkWorld;
		this.position = CoffeeHero.STARTING_POSITION;
		this.coffeeAmount = CoffeeHero.STARTING_COFFEE_AMOUNT;
		this.curImage = CoffeeHero.stopGivingL;
		this.isRefilling = true;
	}

	public void act(float timePassed)
	{
		this.animTimePassed += timePassed;
		this.serveTimePassed += timePassed;
		if (this.isMoving)
		{
			this.move(timePassed);
		}
		if (this.isServing)
		{
			this.serveCoffee(timePassed);
		}
		if (this.isRefilling)
		{
			this.refillCoffee();
		}
		this.nextImage();
	}

	//make a coffee hazard
	private void serveCoffee(float timePassed){
		if (this.serveTimePassed > CoffeeHero.HAZARD_PLACING_SPEED && this.coffeeAmount>0)
		{
			this.serveTimePassed = this.serveTimePassed%CoffeeHero.HAZARD_PLACING_SPEED;
			this.coffeeAmount--;
			coffeeHazardsList.add(new CoffeeHazard(this.position));
		}
	}

	private void refillCoffee(){
		if (CoffeeMaker.LOSE_RADIUS >(Math.abs(this.position - linkWorld.cM.getPosition())) && this.coffeeAmount < CoffeeHero.MAX_COFFEE_AMOUNT){
			this.coffeeAmount = coffeeAmount + 1; 
			//TODO add delay and loop in INPUT
		}

	}
	

	private void move(float timePassed){
		this.position += timePassed*CoffeeHero.MOVING_SPEED*direction;
		this.checkBounds();
	}
	
	private void checkBounds()
	{
		short canvasCenter = Canvas.WIDTH/2;
		if (this.position < canvasCenter - Enemy.SPAWN_DISTANCE)	//Hero too far to the left
		{
			this.position = canvasCenter - Enemy.SPAWN_DISTANCE;
		}
		else if (this.position > canvasCenter + Enemy.SPAWN_DISTANCE)	//Hero too far to the right
		{
			this.position = canvasCenter + Enemy.SPAWN_DISTANCE;
		}
	}

	private void nextImage()
	{
		if (this.animTimePassed>CoffeeHero.IMAGE_CHANGE_RATE )
		{
			this.animTimePassed = animTimePassed%CoffeeHero.IMAGE_CHANGE_RATE;
			this.curImageIndex++;
		}
		if (this.isMoving)
		{
			if (this.direction==1)	//moving right
			{
				this.curImage = CoffeeHero.framesR[curImageIndex%CoffeeHero.framesR.length];
			}
			if (this.direction==-1)	//moving left
			{
				this.curImage = CoffeeHero.framesL[curImageIndex%CoffeeHero.framesL.length];
			}
		}
		else 	//standing still
		{
			if (this.direction==1)	//looking to the right
			{
				this.curImage = CoffeeHero.stopGivingR;
			}
			else if (this.direction==-1)	//looking to the left
			{
				this.curImage = CoffeeHero.stopGivingL; 
			}
		}
	}
}