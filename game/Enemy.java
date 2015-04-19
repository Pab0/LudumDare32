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
	
	private void Move(float newPosition){
		this.position = newPosition;
	}
	
	//the behavior of the class Enemy
	private void tryToSteal(){
		if (drinking == false){ //if you 're not drinking coffee
			if (this.position != linkWorld.cM.getPosition()){ //if you are not at the coffee maker
				Move((this.position + this.speed)); //move towards the coffee maker
				for (int i = 0; i < linkWorld.cH.getCoffeeHazardsList().size(); i++){ //check every hazard
					if (this.position == linkWorld.cH.getCoffeeHazardsList().get(i).getHazardPosition()){ //and if you are standing on one
						this.myCoffee = linkWorld.cH.getCoffeeHazardsList().get(i);
						this.drinking = true; // start drinking!
						break;
					}
				}
			}
		}else{ //if you are drinking
			this.drinkCoffee(myCoffee); //drink!
		}
	}

}
