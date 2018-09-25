import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class Figures extends JPanel {

	private int x = 100;
	private int y = 100;

	private int rX;
	private int rY;

	private int sLong = 15;

	private List<Rectangle> list = new LinkedList<>();

	public void addHeadRectList() {

		list.add(new Rectangle(x, y, 35, 35));

	}

	public void addRect() {

		list.add(new Rectangle(rX, rY, 35, 35));
		repaint();
		
	}

	public void setXstep(int x) {

		this.x += x;
	}

	public void setYstep(int y) {

		this.y += y;
	}
	
	public void setX(int x) {
		
		this.x = x;
	}
	
	public void setY(int y) {
		
		this.y = y;
	}
	
	public int getXposition() {
		
		return x;
	}
	
	public int getYposition() {
		
		return y;
	}

	Figures() {
		repaint();
	}

	public void paint(Graphics g) {
		
		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawRect(5, 20, 475, 420);

		g2d.drawString("Oœ X: " + x, 360, 15);
		g2d.drawString("Oœ Y: " + y, 420, 15);
		
		for (Rectangle rectList : list) {
			g2d.fill(rectList);
			 

		}

		if (list.size() == sLong)
			list.remove(0);
		
		
	}
	

}
