package game;

import java.util.LinkedList;

public class CoffeeMaker {
	
	private int position;
	private int range;
	private boolean targetAquired = false;
	private Enemy target;
	private LinkedList<CoffeeBean> coffeeBeanList = new LinkedList<CoffeeBean>();

	public CoffeeMaker(int position,int range){
		this.range = range;
		this.position = position;
	}
	
	//select a target within range
	private void aquireTarget(){
		for (int i= this.position; i <=range; i--){ //check the tiles from the base of the coffee maker till the range
			if (this.targetAquired = true){
				break;
			}
			 for (int j = 0; j<= World.enemiesList.size();j++){ //search all the enemies
				 if (World.enemiesList.get(j).getPosition() <= i){  //if the enemy is at the tile
					 this.targetAquired = true;  // lock him
					 this.target = World.enemiesList.get(j);
					shoot(target.getPosition()) ; // shoot him!
					
				 }
			 }
		}
		
	}
	
	private void shoot(float position){
		if (this.target.getLife() == 0){
			World.enemiesList.remove(target);
			this.targetAquired = false;
		}else{
			// insert code for shooting
		}
	}

}
