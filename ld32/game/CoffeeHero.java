package ld32.game;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class CoffeeHero {
	
	private static final float STARTING_POSITION = 100;
	private static final int STARTING_COFFEE_AMOUNT = 3;
	private static final float MOVING_SPEED = 0.2f;
	private static final float IMAGE_CHANGE_RATE = 250f;
	
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
	boolean isRefilling;
	boolean isServing;
	private LinkedList<CoffeeHazard> coffeeHazardsList = new LinkedList<CoffeeHazard>(); 
	World linkWorld;
	float timePassed;

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
	}
	
	public void act(float timePassed)
	{
		this.timePassed += timePassed;
		if (direction==1 || direction==-1)
		{
			this.move(timePassed);
			this.nextMovImage();
		}
		if (this.isServing)
		{
			this.serveCoffee();
		}
		if (this.isRefilling)
		{
			this.refillCoffee();
		}
	}
	
	//make a coffee hazard
	private void serveCoffee(){
		this.coffeeAmount = this.coffeeAmount-1;
		if (direction == 1){					//if you look towards the right
			coffeeHazardsList.add(new CoffeeHazard(this.position + 1));  //make a hazard at the place in front of you
		}else if (direction == -1){									// if you don't look right
			coffeeHazardsList.add(new CoffeeHazard(this.position - 1));  //make a hazard at the place in front of you
		}
		
	}
	//refill when you are at the coffee machine
	private void refillCoffee(){
		if (this.position == linkWorld.cM.getPosition()){
			this.coffeeAmount = coffeeAmount + 1; 
			//TODO add delay and loop in INPUT
		}
		
	}
	
	private void move(float timePassed){
		this.position += timePassed*CoffeeHero.MOVING_SPEED*direction;
	}
	
	private void nextMovImage()
	{
		if (this.timePassed>CoffeeHero.IMAGE_CHANGE_RATE)
		{
			this.timePassed = timePassed%CoffeeHero.IMAGE_CHANGE_RATE;
			this.curImageIndex++;
		}
		if (this.direction==1)	//moving right
		{
			this.curImage = CoffeeHero.framesR[curImageIndex%CoffeeHero.framesR.length];
		}
		if (this.direction==-1)	//moving left
		{
			this.curImage = CoffeeHero.framesL[curImageIndex%CoffeeHero.framesL.length];
		}
	}
}