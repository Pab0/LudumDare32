package game;

public class CoffeeHazard {

	private int coffeeQuantity;
	private float hazardPosition;
	
	public CoffeeHazard(float position){
		this.setHazardPosition(position);
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
