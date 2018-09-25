import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

public class Snake extends JFrame {

	Figures figures = new Figures();

	ActionMap actionMap = new ActionMap();

	JLabel label1;

	boolean xR = false;
	boolean xL = false;
	boolean yU = false;
	boolean yD = false;

	int xValue;
	int yValue;

	boolean triggerDelayedRight = false;
	boolean triggerDelayedLeft = false;
	boolean triggerDelayedUp = false;
	boolean triggerDelayedDown = false;

	char courseLeftRight;
	char courseUpDown;

	Snake() {

		setSize(500, 500);
		setLocationRelativeTo(null);
		setTitle("Gra W¹¿");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);

		label1 = new JLabel("Snake");
		label1.setBounds(200, 0, 50, 20);
		add(label1);

		label1.setActionMap(actionMap);

		InputMap inputMap = label1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "move_Right");
		inputMap.put(KeyStroke.getKeyStroke("LEFT"), "move_Left");
		inputMap.put(KeyStroke.getKeyStroke("UP"), "move_Up");
		inputMap.put(KeyStroke.getKeyStroke("DOWN"), "move_Down");

		actionMap.put("move_Right", actionRight);
		actionMap.put("move_Left", actionLeft);
		actionMap.put("move_Up", actionUp);
		actionMap.put("move_Down", actionDown);

		add(figures);

	}

	AbstractAction actionRight = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();

			if (source == label1) {

				xValue = figures.getXposition();

				if (yValue + 35 <= figures.getYposition() || yValue - 35 >= figures.getYposition()) {

					yU = false;
					yD = false;

					if (xL != true)
						xR = true;
					else
						xR = false;

				}

				else if (courseLeftRight != 'L') {

					yU = false;
					yD = false;
					triggerDelayedLeft = false;

					if (xL != true)
						xR = true;
					else
						xR = false;
				}

				else if (courseLeftRight == 'L' && triggerDelayedLeft == true) {

					yU = false;
					yD = false;
					triggerDelayedLeft = false;
					xR = true;

				}

				else
					triggerDelayedRight = true;

				courseLeftRight = 'R';

				

			}

		}
	};

	AbstractAction actionLeft = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();

			if (source == label1) {

				if (yValue + 35 <= figures.getYposition() || yValue - 35 >= figures.getYposition()) {

					xValue = figures.getXposition();

					yU = false;
					yD = false;

					if (xR != true)
						xL = true;
					else
						xL = false;
				}

				else if (courseLeftRight != 'R') {

					yU = false;
					yD = false;
					triggerDelayedRight = false;

					if (xR != true)
						xL = true;
					else
						xL = false;
				}

				else if (courseLeftRight == 'R' && triggerDelayedRight == true) {

					yU = false;
					yD = false;
					triggerDelayedRight = false;
					xL = true;

				}

				else {
					triggerDelayedLeft = true;

				}

				courseLeftRight = 'L';

				

			}

		}
	};

	AbstractAction actionUp = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();

			yValue = figures.getYposition();

			if (xValue + 35 <= figures.getXposition() || xValue - 35 >= figures.getXposition()) {

				xR = false;
				xL = false;

				if (yD != true)
					yU = true;

				else
					yU = false;
			}

			else if (courseUpDown != 'D') {

				xR = false;
				xL = false;
				triggerDelayedDown = false;

				if (yD != true)
					yU = true;

				else
					yU = false;
			}

			else if (courseUpDown == 'D' && triggerDelayedDown == true) {

				xR = false;
				xL = false;
				triggerDelayedDown = false;
				yU = true;

			}

			else
				triggerDelayedUp = true;

			courseUpDown = 'U';

			

		}
	};

	AbstractAction actionDown = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();

			yValue = figures.getYposition();

			if (source == label1) {

				if (xValue + 35 <= figures.getXposition() || xValue - 35 >= figures.getXposition()) {

					xR = false;
					xL = false;

					if (yU != true)
						yD = true;

					else
						yD = false;

				}

				else if (courseUpDown != 'U') {

					xR = false;
					xL = false;
					triggerDelayedUp = false;

					if (yU != true)
						yD = true;

					else
						yD = false;

				}

				else if (courseUpDown == 'U' && triggerDelayedUp == true) {

				xR = false;
				xL = false;
				triggerDelayedUp = false;
				yD = true;

			}

				else
					triggerDelayedDown = true;

				courseUpDown = 'D';

				
			}

		}
	};

	public static void main(String[] args) {

		Snake snake = new Snake();

		while (true) {

			if ((snake.xR == true) && (snake.figures.getXposition() < 445))
				snake.goRight();

			else if ((snake.xL == true) && (snake.figures.getXposition() > 5))
				snake.goLeft();

			else if ((snake.yU == true) && (snake.figures.getYposition() > 20))
				snake.goUp();

			else if ((snake.yD == true) && (snake.figures.getYposition() < 405))
				snake.goDown();

			if (snake.xR == false && snake.triggerDelayedRight == true) {

				if (snake.yValue + 35 <= snake.figures.getYposition()
						|| snake.yValue - 35 >= snake.figures.getYposition()) {

					snake.xR = true;
					snake.triggerDelayedRight = false;
					snake.yU = false;
					snake.yD = false;
				}

				else if (snake.yU == true)
					snake.yU = true;

				else if (snake.yD == true)
					snake.yD = true;
			}

			else if (snake.xL == false && snake.triggerDelayedLeft == true) {

				if (snake.yValue + 35 <= snake.figures.getYposition()
						|| snake.yValue - 35 >= snake.figures.getYposition()) {

					snake.xL = true;
					snake.triggerDelayedLeft = false;
					snake.yU = false;
					snake.yD = false;
				}

				else if (snake.yU == true)
					snake.yU = true;

				else if (snake.yD == true)
					snake.yD = true;
			}

			else if (snake.yU == false && snake.triggerDelayedUp == true) {

				if (snake.xValue + 35 <= snake.figures.getXposition()
						|| snake.xValue - 35 >= snake.figures.getXposition()) {

					snake.yU = true;
					snake.triggerDelayedUp = false;
					snake.xR = false;
					snake.xL = false;

				}

				else if (snake.xR == true)
					snake.xR = true;

				else if (snake.xL == true)
					snake.xL = true;
			}

			else if ((snake.yD == false) && (snake.triggerDelayedDown == true)) {

				if (snake.xValue + 35 <= snake.figures.getXposition()
						|| snake.xValue - 35 >= snake.figures.getXposition()) {

					snake.yD = true;
					snake.triggerDelayedDown = false;
					snake.xR = false;
					snake.xL = false;

				}

				else if (snake.xR == true)
					snake.xR = true;

				else if (snake.xL == true)
					snake.xL = true;

			}

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

	public void goRight() {

		figures.setXstep(5);
		figures.addHeadRectList();
		repaint();

	}

	public void goLeft() {
		figures.setXstep(-5);
		figures.addHeadRectList();
		repaint();
	}

	public void goUp() {
		figures.setYstep(-5);
		figures.addHeadRectList();
		repaint();
	}

	public void goDown() {

		figures.setYstep(5);
		figures.addHeadRectList();
		repaint();

	}
}
