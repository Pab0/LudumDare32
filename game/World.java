package game;

import java.util.LinkedList;

public class World {

	public LinkedList<Enemy> enemiesList;
	 CoffeeMaker cM;
	 CoffeeHero cH ;
	 
	 public World(){
		 cH = new CoffeeHero(this);
		 enemiesList = new LinkedList<Enemy>();
	 }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
