package game;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Canvas extends JPanel
{
	private static final short WIDTH = 800;
	private static final short HEIGHT = 600;
	
	public Canvas()
	{
		super();
		this.setBackground(Color.CYAN);
		JFrame frame  = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Canvas.WIDTH, Canvas.HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		frame.setVisible(true);
	}
}
