package game;

public class Enemy {

	private int life;
	private boolean direction;
	private float speed;
	private float position;
	private boolean drinking;
	private CoffeeHazard myCoffee;
	private World linkWorld;


	public float getPosition(){
		return this.position;
	}

	public int getLife(){
		return this.life;
	}

	public Enemy(World linkWorld, float position, float speed){

		this.linkWorld = linkWorld;
		this.position = position;
		this.speed = speed;
		if (this.position < this.linkWorld.cM.getPosition()){
			this.direction = true;
		}
	}

	private void drinkCoffee(CoffeeHazard myCoffeeHazard){

		int index = linkWorld.cH.getCoffeeHazardsList().indexOf(myCoffee); //find my coffee in the list
		int quantity = linkWorld.cH.getCoffeeHazardsList().get(index).getCoffeeQuantity();
		if (quantity > 0){ //if there is still some coffee left
			linkWorld.cH.getCoffeeHazardsList().get(index).setCoffeeQuantity(quantity -1); //reduce the coffee
		}else{
			linkWorld.cH.getCoffeeHazardsList().remove(index);
			this.drinking = false;
		}
	}

	private void move(){
		if (direction){
		this.position = this.position + speed ;
		}else{
			this.position = this.position - speed ;
		}
	}

	private void checkForCoffee(){

		for (int i = 0; i < linkWorld.cH.getCoffeeHazardsList().size(); i++){ //check every hazard
			//next command might be replaced by the COLLISION TEAM
			if (Math.abs(this.position - linkWorld.cH.getCoffeeHazardsList().get(i).getHazardPosition()) <= CoffeeHazard.HAZARD_RADIUS){ //and if you are standing on one
				this.myCoffee = linkWorld.cH.getCoffeeHazardsList().get(i); //That coffee is MY COFFEE!!!!
				this.drinking = true; // start drinking!
				break;
			}
		}
	}

	//the behavior of the class Enemy
	private void act(){
		this.checkForCoffee();
		if (!drinking){ //if you 're not drinking coffee
			if (Math.abs(linkWorld.cM.getPosition() - this.position) > CoffeeMaker.LOSE_RADIUS){ //if you are not at the coffee maker
				move(); //move towards the coffee maker
			}
		}else{ //if you are drinking
			this.drinkCoffee(myCoffee); //drink!
		}
	}

}
