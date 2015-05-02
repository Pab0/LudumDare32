package ld32.game;

import java.awt.image.BufferedImage;

public class Enemy {

	static final float AVERAGE_SPEED = 0.05f;
	static final float SPAWN_DISTANCE = 300;
	static final float HEIGHT = 300;	//position on y axis to be drawn
	private static final byte STARTING_LIFE = 3;
	private static final float DRINKING_SPEED = 500f;
	private static final float IMAGE_CHANGE_RATE = 250f;
	static final short IMAGE_WIDTH = 50;
	static final short IMAGE_HEIGHT = 150;

	private byte life;
	private byte direction;
	private float speed;
	private float position;
	private boolean drinking;
	boolean isMoving;
	private CoffeeHazard myCoffee;
	private World linkWorld;

	private int curImageIndex;
	private float animTimePassed;
	private float drinkTimePassed;
	BufferedImage curImage;
	static BufferedImage[] framesL = new BufferedImage[4];	//looking to the left
	static BufferedImage[] framesR = new BufferedImage[4];	//looking to the right
	static BufferedImage standingL;
	static BufferedImage standingR;

	public float getPosition(){
		return this.position;
	}

	public int getLife(){
		return this.life;
	}

	public Enemy(World linkWorld, byte direction, float speed){
		this.direction = direction;
		this.linkWorld = linkWorld;
		this.speed = speed;
		this.life = Enemy.STARTING_LIFE;
		this.position = linkWorld.cM.getPosition()-direction*Enemy.SPAWN_DISTANCE;	//positive direction -> starts from left
		this.curImageIndex = 0;
		if (direction==1)
		{
			this.curImage = framesR[curImageIndex];
		}
		if (direction==-1){
			this.curImage = framesL[curImageIndex];
		}
	}

	private void drinkCoffee(CoffeeHazard myCoffeeHazard){
		//TODO index might return -1
		if (drinkTimePassed>Enemy.DRINKING_SPEED)
		{
			drinkTimePassed = drinkTimePassed%Enemy.DRINKING_SPEED;
			
			this.isMoving = false;
			int index = linkWorld.cH.getCoffeeHazardsList().indexOf(myCoffee); //find my coffee in the list
			if (index==-1)
			{
				return;	//in case the coffee has already been drunk
			}
			int quantity = linkWorld.cH.getCoffeeHazardsList().get(index).getCoffeeQuantity();
			if (quantity > 0){ //if there is still some coffee left
				linkWorld.cH.getCoffeeHazardsList().get(index).setCoffeeQuantity(quantity -1); //reduce the coffee
			}else{
				linkWorld.cH.getCoffeeHazardsList().remove(index);
				this.drinking = false;
			}

			if (direction==1)
			{
				this.curImage = Enemy.standingR;
			}
			else if (direction==-1)
			{
				this.curImage = Enemy.standingL;
			}
		}
	}

	private void move(float timePassed){
		this.isMoving = true;
		this.position = this.position + direction*timePassed*speed;
	}

	private void checkForCoffee(){

		for (int i = 0; i < linkWorld.cH.getCoffeeHazardsList().size(); i++){ //check every hazard
			//next command might be replaced by the COLLISION TEAM
			if (Math.abs(this.position - linkWorld.cH.getCoffeeHazardsList().get(i).getHazardPosition()) <= CoffeeHazard.HAZARD_RADIUS){ //and if you are standing on one
				this.myCoffee = linkWorld.cH.getCoffeeHazardsList().get(i); //That coffee is MY COFFEE!!!!
				this.drinking = true; // start drinking!
				this.isMoving = false;
				break;
			}
		}
	}

	//the behavior of the class Enemy
	void act(float timePassed){
		this.animTimePassed += timePassed;
		this.drinkTimePassed += timePassed;
		this.checkForCoffee();
		if (!drinking){ //if you 're not drinking coffee
			if (Math.abs(linkWorld.cM.getPosition() - this.position) > CoffeeMaker.LOSE_RADIUS){ //if you are not at the coffee maker
				move(timePassed); //move towards the coffee maker
			}
			else
			{
				//TODO
				this.isMoving = false;
			}
		}else{ //if you are drinking
			this.drinkCoffee(myCoffee); //drink!
		}
		this.nextImage();
	}

	private void nextImage()
	{
		if (this.animTimePassed>Enemy.IMAGE_CHANGE_RATE)
		{
			this.animTimePassed = animTimePassed%Enemy.IMAGE_CHANGE_RATE;
			this.curImageIndex++;
		}
		if (this.isMoving)
		{

			if (this.direction==1)	//moving right
			{
				this.curImage = Enemy.framesR[curImageIndex%Enemy.framesR.length];
			}
			if (this.direction==-1)	//moving left
			{
				this.curImage = Enemy.framesL[curImageIndex%Enemy.framesL.length];
			}
		}
		else
		{
			if (this.direction==1)	//moving to the right
			{
				this.curImage = Enemy.standingR;
			}
			else if (this.direction==-1) //moving to the left
			{
				this.curImage = Enemy.standingL;
			}
		}
	}
}