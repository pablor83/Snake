import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

public class Figures extends JPanel {

	private int x;
	private int y;

	private int rX;
	private int rY;

		
	private int sLong = 20;
	private int foodListSize = 1;
	private boolean pauseInscription = false;

	private int colorSnake = 0500100;

	private List<Rectangle> list = new LinkedList<>();
	private List<Rectangle> foodList = new LinkedList<>();

	private Random randomFoodAndStartPosition = new Random();

	Color colorFood = new Color(300300300);

	public void setStartPosition() {

		int[] x = new int[52];
		int[] y = new int[52];
		int startX = 100;
		int startY = 100;

		for (int i = 0; i < 52; i++) {
			int stepByFiveX = startX += 5;
			int stepByFiveY = startY += 5;

			x[i] = stepByFiveX;
			y[i] = stepByFiveY;

		}

		int randomX = randomFoodAndStartPosition.nextInt(51);
		int randomy = randomFoodAndStartPosition.nextInt(51);

		this.x = x[randomX];
		this.y = y[randomy];

	}

	public void addHeadRectList() {

		list.add(new Rectangle(x, y, 25, 25));

	}

	public void setRandomPoint() {

		rX = randomFoodAndStartPosition.nextInt(426) + 25;
		rY = randomFoodAndStartPosition.nextInt(311) + 85;

	}

	public void addFood() {

		foodList.add(new Rectangle(rX, rY, 25, 25));
	}

	public void setColorSnake(int c) {

		colorSnake = c;
	}

	public void setFoodSize(int i) {

		foodListSize = i;
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

	public boolean tailCollisionDetection() {

		boolean collision = false;

		if (list.size() > 16) {

			for (int i = 0; i < list.size() - 16; i++) {
				if (list.get(list.size() - 1).intersects(list.get(i)))

					collision = true;
			}
		}

		return collision;
	}

	public boolean detectEatenFood() {

		boolean food = false;

		if (list.size() > 0) {
			for (int i = 0; i < foodList.size(); i++) {

				if (list.get(list.size() - 1).intersects(foodList.get(i)))
					food = true;
			}
		}

		return food;
	}

	public void changeSnakeLong(int i) {

		sLong = i;
	}

	public int getListSize() {

		int listSize = list.size();

		return listSize;
	}

	public int snakeLong() {

		int snakeLong = sLong;

		return snakeLong;
	}

	public void cleanList() {

		list.removeAll(list);
	}

	public Rectangle rectAreaFrame() {

		Rectangle rect = new Rectangle(25, 85, 475, 420);

		return rect;
	}

	public Rectangle getHeadRectCoordinates() {

		Rectangle rectHead = new Rectangle(x, y, 25, 25);

		if (list.size() > 0)
			rectHead = list.get(list.size() - 1);

		return rectHead;
	}

	public void setPauseInscription(boolean b) {

		pauseInscription = b;
	}

	Figures() {
		repaint();
	}

	public void paint(Graphics g) {

		super.paint(g);

		Graphics2D g2d = (Graphics2D) g;

//		g2d.drawString("Oœ X: " + x, 360, 15);
//		g2d.drawString("Oœ Y: " + y, 420, 15);
				
		for (Rectangle rFood : foodList) {

			g2d.setColor(colorFood);
			g2d.fill(rFood);
		}
		

		for (Rectangle rectList : list) {

			g2d.setColor(new Color(colorSnake));

			g2d.fill(rectList);
		}

		if (foodList.size() > foodListSize)
			foodList.remove(0);

		if (list.size() == sLong)
			list.remove(0);

		else if (list.size() > sLong && sLong >= 1) {

			int setSizeList = list.size() - sLong;

			for (int i = 0; i <= setSizeList; i++) {
				list.remove(0);
			}
		}

		if (sLong == 1) {

			list.removeAll(list);

			int[] xP = { x+10, x+20, x+40, x+30, x+50, x+30, x+40, x+20, x+10, x, x-20, x-10, x-30,
					x-10, x-20, x };
			int[] yP = { y-20, y, y-20, y+10, y+20, y+30, y+60, y+40, y+60, y+40, y+60, y+30, y+20,
					y+10, y-20, y };

			GradientPaint gp = new GradientPaint(x, y, Color.yellow, x+40, y+60, Color.RED);
			g2d.setPaint(gp);
			g2d.fillPolygon(xP, yP, xP.length);

			Font font = new Font("TimesRoman", Font.BOLD, 30);

			g2d.setColor(Color.red);
			g2d.setFont(font);
			g2d.drawString("GAME OVER!!!", 155, 280);

		}
		
		
		if (pauseInscription == true) {

			Font font = new Font("TimesRoman", Font.BOLD, 24);
			g2d.setColor(Color.red);
			g2d.setFont(font);
			
			g2d.drawString("Pauza", 215, 250);
		}

		g2d.setColor(Color.lightGray);
		g2d.setStroke(new BasicStroke(15));
		g2d.drawRect(19, 79, 488, 433);

		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawRect(25, 85, 475, 420);
	}

}
