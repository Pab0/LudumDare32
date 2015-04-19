package game;

public class Enemy {
	
	private int life;
	private boolean direction;
	private float position;
	
	public float getPosition(){
		return this.position;
	}
	
	public int getLife(){
		return this.life;
	}
	
	public Enemy(){
		
	}
	
	private void drinkCoffee(){
		
	}
	
	private void Move(float newPosition){
		this.position = newPosition;
	}

}
