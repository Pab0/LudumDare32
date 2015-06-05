package ld32.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Canvas extends JPanel implements KeyListener
{
	static final short WIDTH = 800;
	static final short HEIGHT = 640;

//	static BufferedImage placeholder;

	World linkWorld;
	static BufferedImage background;
	public Canvas(World linkWorld)
	{
		super();
		this.linkWorld = linkWorld;
		this.setBackground(Color.CYAN);
		this.setFocusable(true);
		this.addKeyListener(this);
		JFrame frame  = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Canvas.WIDTH, Canvas.HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(true);
	}

	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(Canvas.background, 0, 0, Canvas.WIDTH, Canvas.HEIGHT, this);
//		drawCoffeeMaker(g);	TODO: Need background without CoffeeMaker in order to draw it
		drawHero(g);
		drawCoffee(g);
		drawEnemies(g);
	}

	private void drawCoffeeMaker(Graphics g)
	{
		g.drawImage(CoffeeMaker.image, (int)(Canvas.WIDTH/2 - CoffeeMaker.image.getWidth()/2), 235, this);
	}

	private void drawHero(Graphics g)
	{
		g.drawImage(linkWorld.cH.curImage, (int)(linkWorld.cH.getPosition()-CoffeeHero.IMAGE_WIDTH/2), World.HEIGHT_AT_DOOR_LEVEL-CoffeeHero.IMAGE_HEIGHT, this); 
	}
	
	private void drawCoffee(Graphics g)
	{
		for (CoffeeHazard hazard:linkWorld.cH.coffeeHazardsList)
		{
			g.drawImage(CoffeeHazard.image, (int)hazard.getHazardPosition(), World.HEIGHT_AT_DOOR_LEVEL-CoffeeHazard.IMAGE_WIDTH, CoffeeHazard.IMAGE_WIDTH, CoffeeHazard.IMAGE_HEIGHT, this);
		}
	}

	private void drawEnemies(Graphics g)
	{
		for (Enemy enemy:linkWorld.enemiesList)
		{
			g.drawImage(enemy.curImage, (int)(enemy.getPosition()-Enemy.IMAGE_WIDTH/2), World.HEIGHT_AT_DOOR_LEVEL-Enemy.IMAGE_HEIGHT, this);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_A :
			this.linkWorld.cH.isMoving = true;
			this.linkWorld.cH.direction = -1;
			break;
		case KeyEvent.VK_D:
			this.linkWorld.cH.isMoving = true;
			this.linkWorld.cH.direction = 1;
			break;
		case KeyEvent.VK_SPACE:
			this.linkWorld.cH.isServing = true;
			break;
		case KeyEvent.VK_W:
			this.linkWorld.cH.isRefilling = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_A :
			this.linkWorld.cH.isMoving = false;
			break;
		case KeyEvent.VK_D:
			this.linkWorld.cH.isMoving = false;
			break;
		case KeyEvent.VK_SPACE:
			this.linkWorld.cH.isServing = false;
			break;
		case KeyEvent.VK_W:
			this.linkWorld.cH.isRefilling = false;
			break;
		}	

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
