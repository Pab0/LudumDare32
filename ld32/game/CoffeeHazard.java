package ld32.game;

import java.awt.image.BufferedImage;

public class CoffeeHazard {

	public static final float HAZARD_RADIUS = 35;
	public static final int FULL_COFFEE = 5;
	private int coffeeQuantity;
	private float hazardPosition;	
	static final short IMAGE_WIDTH = 50;
	static final short IMAGE_HEIGHT = 50;
	
	static BufferedImage image;
	
	public CoffeeHazard(float position){
		this.setHazardPosition(position);
		this.coffeeQuantity =FULL_COFFEE;
	}
	
	public int getCoffeeQuantity(){
		return this.coffeeQuantity;
	}
	
	public void setCoffeeQuantity(int newQuantity){
		this.coffeeQuantity = newQuantity;
	}

	public float getHazardPosition() {
		return hazardPosition;
	}

	public void setHazardPosition(float hazardPosition) {
		this.hazardPosition = hazardPosition;
	}

}
