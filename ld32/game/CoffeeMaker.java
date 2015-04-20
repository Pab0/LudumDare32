package ld32.game;

import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class CoffeeMaker {

	public static final short WIDTH = (short)(Canvas.WIDTH/2);	//position
	public static final short HEIGHT = (short)(Canvas.HEIGHT/2);//position
	public static final short RANGE = 200;
	public static final float LOSE_RADIUS = 50;
	public static BufferedImage image;
	private float position;
	private int range;
	private boolean targetAquired = false;
	private Enemy target;
	private LinkedList<CoffeeBean> coffeeBeanList = new LinkedList<CoffeeBean>();
	private World linkWorld;

	public float getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public CoffeeMaker(World linkWorld){
		this.range = CoffeeMaker.RANGE;
		this.position = CoffeeMaker.WIDTH;
		this.linkWorld = linkWorld;
	}

	//select a target within range
	private void aquireTarget(){

		float distance_min = this.range + 1;
		for (int j = 0; j<= linkWorld.enemiesList.size();j++){ //search all the enemies

			float targetDistance = Math.abs(linkWorld.enemiesList.get(j).getPosition() - this.position);
			if ((targetDistance <= range) && (targetDistance < distance_min)){  //if the enemy is at the tile
				distance_min = targetDistance;
				this.targetAquired = true;  // lock him
				this.target = linkWorld.enemiesList.get(j);
			}
		}
	}


	private void shoot(float position){
		if (this.target.getLife() <= 0){ //if the target is dead 
			linkWorld.enemiesList.remove(target); //remove him from the game
			this.targetAquired = false; 
		}else{
			this.coffeeBeanList.addFirst(new CoffeeBean(this.target.getPosition())); //shoot at him!

		}
	}

	private void act(){
		this.aquireTarget();
		if (this.targetAquired){
			this.shoot(this.target.getPosition());// shoot him!
		}

	}
}
