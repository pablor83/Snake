import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class Figures extends JPanel {

	private int x = 100;
	private int y = 100;

	private int rX;
	private int rY;

	private int sLong = 20;

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

	public void changeSnakeLong(int i) {

		sLong = i;
	}

	public int listSize() {

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
			g2d.setColor(Color.BLUE);
			g2d.fill(rectList);

		}

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

			int[] xP = { x, x + 10, x + 30, x + 20, x + 40, x + 20, x + 30, x + 10, x, x - 10, x - 30, x - 20, x - 40,
					x - 20, x - 30, x - 10 };
			int[] yP = { y, y + 20, y, y + 30, y + 40, y + 50, y + 80, y + 60, y + 80, y + 60, y + 80, y + 50, y + 40,
					y + 30, y, y + 20 };

			g2d.setColor(Color.yellow);
			g2d.fillPolygon(xP, yP, xP.length);

			Font font = new Font("TimesRoman", Font.BOLD, 30);

			g2d.setColor(Color.red);
			g2d.setFont(font);
			g2d.drawString("GAME OVER!!!", 130, 230);

		}

	}

}
