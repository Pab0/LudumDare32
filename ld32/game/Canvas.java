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

	static BufferedImage placeholder;

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
		drawCoffeeMaker(g);
		drawHero(g);
		drawEnemies(g);
	}

	private void drawCoffeeMaker(Graphics g)
	{
		//TODO
		g.drawImage(CoffeeMaker.image, (int)(Canvas.WIDTH/2 - CoffeeMaker.image.getWidth()/2), 235, this);

	}

	private void drawHero(Graphics g)
	{
		g.drawImage(linkWorld.cH.curFrame, (int)linkWorld.cH.getPosition(), (int)(Canvas.HEIGHT/2), this);	//TODO specify exact height 
	}

	private void drawEnemies(Graphics g)
	{
		for (Enemy enemy:linkWorld.enemiesList)
		{
			g.drawImage(enemy.curImage, (int)enemy.getPosition(), 380, this);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_A :
			this.linkWorld.cH.direction = -1;
			break;
		case KeyEvent.VK_D:
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
			this.linkWorld.cH.direction = 0;
			break;
		case KeyEvent.VK_D:
			this.linkWorld.cH.direction = 0;
			break;
		case KeyEvent.VK_SPACE:
			this.linkWorld.cH.direction = 0;
			break;
		case KeyEvent.VK_W:
			this.linkWorld.cH.direction = 0;
			break;
		}	

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
