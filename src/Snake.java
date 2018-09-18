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

				yU = false;
				yD = false;

				if (xL != true)
					xR = true;
				else
					xR = false;

			}

		}
	};

	AbstractAction actionLeft = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();

			if (source == label1) {

				yU = false;
				yD = false;

				if (xR != true)
					xL = true;
				else
					xL = false;

			}

		}
	};

	AbstractAction actionUp = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();

			xR = false;
			xL = false;

			if (yD != true)
				yU = true;

			else
				yU = false;

		}
	};

	AbstractAction actionDown = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();

			if (source == label1) {

				xR = false;
				xL = false;

				if (yU != true)
					yD = true;

				else
					yD = false;
			}

		}
	};

	public static void main(String[] args) {

		Snake snake = new Snake();

		while (true) {

			if ((snake.xR == true) && (snake.figures.getXposition() < 445))
				snake.goRight();

			if ((snake.xL == true) && (snake.figures.getXposition() > 5))
				snake.goLeft();

			if ((snake.yU == true) && (snake.figures.getYposition() > 20))
				snake.goUp();

			if ((snake.yD == true) && (snake.figures.getYposition() < 405))
				snake.goDown();

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
