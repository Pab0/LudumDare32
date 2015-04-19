package game;

import java.util.LinkedList;

public class CoffeeHero {
	
	private int coffeeAmount;
	private float position;
	private boolean direction;
	private LinkedList<CoffeeHazard> coffeeHazardsList = new LinkedList<CoffeeHazard>(); 
	
	public LinkedList<CoffeeHazard> getCoffeeHazardsList() {
		return coffeeHazardsList;
	}

	public void setCoffeeHazardsList(LinkedList<CoffeeHazard> coffeeHazardsList) {
		this.coffeeHazardsList = coffeeHazardsList;
	}

	World linkWorld;
	
	public CoffeeHero(World linkWorld){
		this.linkWorld = linkWorld;
		
	}
	
	//make a coffee hazard
	private void serveCoffee(){
		this.coffeeAmount = this.coffeeAmount-1;
		if (direction == true){					//if you look towards the right
			coffeeHazardsList.add(new CoffeeHazard(this.position + 1));  //make a hazard at the place in front of you
		}else{									// if you don't look right
			coffeeHazardsList.add(new CoffeeHazard(this.position - 1));  //make a hazard at the place in front of you
		}
		
	}
	//refill when you are at the coffee machine
	private void refillCoffee(){
		if (this.position == linkWorld.cM.getPosition()){
			this.coffeeAmount = coffeeAmount + 1;
		}
		
	}
	
	private void move(float newPosition){
		this.position = newPosition;
	}

}
