import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.KeyStroke;

public class Snake extends JFrame {

	Figures figures = new Figures();

	ActionMap actionMap = new ActionMap();

	
	JButton restartButton;

	private boolean xR = false;
	private boolean xL = false;
	private boolean yU = false;
	private boolean yD = false;

	private int xValue;
	private int yValue;

	private boolean triggerDelayedRight = false;
	private boolean triggerDelayedLeft = false;
	private boolean triggerDelayedUp = false;
	private boolean triggerDelayedDown = false;

	private char courseLeftRight;
	private char courseUpDown;

	private int setSnakelength;
	private int snakeLengthBeforeCollision;

	private boolean pauseGame = false;
	private boolean gameOver = true;

	private long setSpeed = 60;

	private int higherLevelControl = 2;

	Snake() {

		setSize(540, 570);
		setLocationRelativeTo(null);
		setTitle("Gra W¹¿");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);

		restartButton = new JButton("Restart");
		restartButton.setBounds(20, 20, 100, 20);
		add(restartButton);

		restartButton.addActionListener(actionRestart -> {

			xR = false;
			xL = false;
			yU = false;
			yD = false;
			int snakeGrow;

			figures.setLifes(3);
			figures.setPoints(0);
			figures.setPoints(0);
			figures.setLifes(3);
			figures.setDisplayGameOver(false);
			figures.cleanList();
			figures.changeSnakelength(12);
			figures.setStartPosition();

			figures.setColorSnake(0500100);
			figures.addHeadRectList();

			gameOver = false;
			pauseGame = false;

			repaint();
		});

		restartButton.setActionMap(actionMap);

		InputMap inputMap = restartButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "move_Right");
		inputMap.put(KeyStroke.getKeyStroke("LEFT"), "move_Left");
		inputMap.put(KeyStroke.getKeyStroke("UP"), "move_Up");
		inputMap.put(KeyStroke.getKeyStroke("DOWN"), "move_Down");
		inputMap.put(KeyStroke.getKeyStroke("P"), "pause");
		inputMap.put(KeyStroke.getKeyStroke("ctrl R"), "restart");

		actionMap.put("move_Right", actionRight);
		actionMap.put("move_Left", actionLeft);
		actionMap.put("move_Up", actionUp);
		actionMap.put("move_Down", actionDown);
		actionMap.put("pause", actionPauseGame);
		actionMap.put("restart", actionRestart);

		add(figures);

	}

	AbstractAction actionRestart = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent arg0) {

			restartButton.doClick();
		}
	};

	AbstractAction actionPauseGame = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (pauseGame == false) {
				pauseGame = true;
				figures.setPauseInscription(true);
				repaint();

			} else if (pauseGame == true) {
				pauseGame = false;
				figures.setPauseInscription(false);
				repaint();
			}

		}
	};

	AbstractAction actionRight = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (pauseGame == false || gameOver != true) {

				xValue = figures.getXposition();

				if (yValue + 25 <= figures.getYposition() || yValue - 25 >= figures.getYposition()) {

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

				else
					triggerDelayedRight = true;

			}

		}
	};

	AbstractAction actionLeft = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {

			xValue = figures.getXposition();

			if (pauseGame == false || gameOver != true) {

				if (yValue + 25 <= figures.getYposition() || yValue - 25 >= figures.getYposition()) {

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

				else {
					triggerDelayedLeft = true;

				}

			}

		}
	};

	AbstractAction actionUp = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {

			yValue = figures.getYposition();

			if (pauseGame == false || gameOver != true) {

				if (xValue + 25 <= figures.getXposition() || xValue - 25 >= figures.getXposition()) {

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

				else
					triggerDelayedUp = true;

			}
		}

	};

	AbstractAction actionDown = new AbstractAction() {

		@Override
		public void actionPerformed(ActionEvent e) {

			yValue = figures.getYposition();

			if (pauseGame == false || gameOver != true) {

				if (xValue + 25 <= figures.getXposition() || xValue - 25 >= figures.getXposition()) {

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

				else
					triggerDelayedDown = true;

			}

		}
	};

	public static void main(String[] args) {

		Snake snake = new Snake();

		snake.figures.setRandomPointFood();
		snake.figures.addFood();
		snake.figures.setStartPosition();
		snake.figures.addHeadRectList();

		while (true) {

			if (snake.pauseGame == false) {

				if (snake.xR == true) {
					snake.goRight();
					snake.courseLeftRight = 'R';
				}

				else if (snake.xL == true) {
					snake.goLeft();
					snake.courseLeftRight = 'L';
				}

				else if (snake.yU == true) {
					snake.goUp();
					snake.courseUpDown = 'U';
				}

				else if (snake.yD == true) {
					snake.goDown();
					snake.courseUpDown = 'D';
				}

				if (snake.xR == false && snake.triggerDelayedRight == true) {

					if (snake.yValue + 25 <= snake.figures.getYposition()
							|| snake.yValue - 25 >= snake.figures.getYposition()) {

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

					if (snake.yValue + 25 <= snake.figures.getYposition()
							|| snake.yValue - 25 >= snake.figures.getYposition()) {

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

					if (snake.xValue + 25 <= snake.figures.getXposition()
							|| snake.xValue - 25 >= snake.figures.getXposition()) {

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

					if (snake.xValue + 25 <= snake.figures.getXposition()
							|| snake.xValue - 25 >= snake.figures.getXposition()) {

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

				if (snake.figures.tailCollisionDetection() == true
						|| snake.figures.getHeadRectCoordinates().intersectsLine(24, 84, 501, 84) == true
						|| snake.figures.getHeadRectCoordinates().intersectsLine(501, 84, 501, 506) == true
						|| snake.figures.getHeadRectCoordinates().intersectsLine(501, 506, 24, 506) == true
						|| snake.figures.getHeadRectCoordinates().intersectsLine(24, 506, 24, 84) == true) {

					snake.snakeLengthBeforeCollision = snake.figures.snakelength();
					snake.setSnakelength = snake.figures.snakelength();
					snake.figures.setColorSnake(400100100);
					
					
					for (int i = snake.setSnakelength; i > 1; i--) {

						snake.killSnake();

						try {
							Thread.sleep(50);
						} catch (InterruptedException e) {

							e.printStackTrace();
						}

					}

					snake.cleanSnake();
					int lifes = snake.figures.getLifes();
					snake.figures.setLifes(lifes-=1);

					if (snake.figures.getLifes() > 0) {

						snake.figures.setTimer(true);

						for (int i = 3; i > 0; i--) {

							snake.figures.startCountdownn();
							snake.repaint();

							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {

								e.printStackTrace();
							}
						}

						snake.figures.setTimer(false);
						snake.figures.setTimeSec();
						snake.respawn();

					}

					else if (snake.figures.getLifes() == 0)
						snake.figures.setDisplayGameOver(true);
				}

				if (snake.figures.detectEatenFood() == true) {

					int points = snake.figures.getPoints();
					snake.figures.changeSnakelength(snake.figures.snakelength() + 1);
					snake.figures.setPoints(points+=1);
					
				}

			}

			try {
				Thread.sleep(snake.setSpeed);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			if (snake.figures.getPoints() == 2 && snake.higherLevelControl == 2) {
				snake.setSpeed = 50;
				snake.figures.setFoodSize(2);
				snake.higherLevelControl += 1;
			}

			else if (snake.figures.getPoints()  == 10 && snake.higherLevelControl == 3) {
				snake.setSpeed = 40;
				snake.figures.setFoodSize(3);
				snake.higherLevelControl += 1;
			}

			else if (snake.figures.getPoints()  == 15 && snake.higherLevelControl == 4) {
				snake.setSpeed = 30;
				snake.figures.setFoodSize(4);
				snake.higherLevelControl += 1;
			}

			else if (snake.figures.getPoints()  == 20 && snake.higherLevelControl == 5) {
				snake.setSpeed = 20;
				snake.figures.setFoodSize(5);
				snake.higherLevelControl += 1;
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

	public void killSnake() {

		xR = false;
		xL = false;
		yU = false;
		yD = false;

		triggerDelayedRight = false;
		triggerDelayedLeft = false;
		triggerDelayedUp = false;
		triggerDelayedDown = false;

		gameOver = true;
		pauseGame = true;

		int snakelength = setSnakelength -= 1;

		figures.changeSnakelength(snakelength);
		repaint();

	}

	public void cleanSnake() {

		figures.cleanList();
		repaint();
	}

	public void respawn() {

		figures.setStartPosition();
		figures.changeSnakelength(snakeLengthBeforeCollision);
		figures.setColorSnake(0500100);

		int[] direction = new int[4];
		direction[0] = 1;
		direction[1] = 2;
		direction[2] = 3;
		direction[3] = 4;

		Random randomDirection = new Random();

		triggerDelayedRight = false;
		triggerDelayedLeft = false;
		triggerDelayedUp = false;
		triggerDelayedDown = false;
		

		switch (randomDirection.nextInt(4)+1) {
		case 1:
			xR = true;
			break;
		case 2:
			xL = true;
			break;
		case 3:
			yU = true;
			break;
		case 4:
			yD = true;
			break;
		}

		gameOver = false;
		pauseGame = false;
		
		
	}
}
