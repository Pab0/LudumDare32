package game;

public class CoffeeHazard {

	private int coffeeQuantity;
	private float hazardPosition;
	
	public CoffeeHazard(float position){
		this.hazardPosition = position;
	}
	
	private int getCoffeeQuantity(){
		return this.coffeeQuantity;
	}
	
	private void setCoffeeQuantity(int newQuantity){
		this.coffeeQuantity = newQuantity;
	}
}
