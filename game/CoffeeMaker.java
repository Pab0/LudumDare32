package game;

import java.util.LinkedList;

public class CoffeeMaker {
	
	private float position;
	private int range;
	private boolean targetAquired = false;
	private Enemy target;
	private LinkedList<CoffeeBean> coffeeBeanList = new LinkedList<CoffeeBean>();
	
	public float getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public CoffeeMaker(int position,int range){
		this.range = range;
		this.position = position;
	}
	
	//select a target within range
	private void aquireTarget(){
		/*for (int i= this.position; i <=range; i--){ //check the tiles from the base of the coffee maker till the range
			if (this.targetAquired = true){		//if you found someone stop
				break;
			}*/
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
		if (this.target.getLife() == 0){ //if the target is dead 
			World.enemiesList.remove(target); //remove him from the game
			this.targetAquired = false; 
		}else{
			this.coffeeBeanList.addFirst(new CoffeeBean(this.target.getPosition())); //shoot at him!
		}
	}

}
