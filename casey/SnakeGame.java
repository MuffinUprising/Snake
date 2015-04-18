package casey;

import java.awt.*;
import java.util.Timer;

import javax.swing.*;


public class SnakeGame {

	public final static int xPixelMaxDimension = 501;  //Pixels in window. 501 to have 50-pixel squares plus 1 to draw a border on last square
	public final static int yPixelMaxDimension = 501;

	public static int xSquares ;
	public static int ySquares ;

	public final static int squareSize = 50;

	protected static Snake snake ;
	protected static Kibble kibble;
	protected static Score score;

	//boolean variables
	public static boolean soundOn = true;
	public static boolean warpWallsOn = false;

	//game state variables
	static final int BEFORE_GAME = 1;
	static final int DURING_GAME = 2;
	static final int GAME_OVER = 3;
	static final int GAME_WON = 4;
	static final int PAUSE_MENU = 5;

	private static int gameStage = BEFORE_GAME;  //use this to figure out what should be happening. 
	//Other classes like Snake and DrawSnakeGamePanel will need to query this, and change it's value

	protected static long clockInterval = 500; //controls game speed
	//Every time the clock ticks, the snake moves
	//This is the time between clock ticks, in milliseconds
	//1000 milliseconds = 1  second.

	static JFrame snakeFrame;
//	static JLayeredPane layeredPane;
	static JPanel optionsPanel;
	static DrawSnakeGamePanel snakePanel;
	static SoundPlayer soundPlayer; //for implementation of sound

	//Framework for this class adapted from the Java Swing Tutorial, FrameDemo and Custom Painting Demo. You should find them useful too.
	//http://docs.oracle.com/javase/tutorial/displayCode.html?code=http://docs.oracle.com/javase/tutorial/uiswing/examples/components/FrameDemoProject/src/components/FrameDemo.java
	//http://docs.oracle.com/javase/tutorial/uiswing/painting/step2.html



	private static void createAndShowGUI() {
		//The commented out code was an attempt to use JLayeredPanes to implement the OptionsGUI
		//It did not work, but I left the code in for reference.
		//The window would just blink in and out of existence.
		//It is unfortunate because all of my improvements to the code hinged on them being chosen through the GUI
		//The big issue is trying to implement the panel into an already existing frame. Everything I try does not work.


		//Create and set up the window.
		snakeFrame = new JFrame();
		snakeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//		layeredPane = new JLayeredPane();
		optionsPanel = new JPanel();

		//snakeFrame variables
		snakeFrame.setSize(xPixelMaxDimension, yPixelMaxDimension);
		snakeFrame.setUndecorated(true); //hide title bar
		snakeFrame.setResizable(false);
		snakeFrame.setVisible(true);
//		snakeFrame.setLayout(new BorderLayout());
//		snakeFrame.add(layeredPane, BorderLayout.CENTER);

		//new snake game panel
		snakePanel = new DrawSnakeGamePanel(snake, kibble, score);
		snakePanel.setFocusable(true);
		snakePanel.setVisible(true);
		snakePanel.requestFocusInWindow(); //required to give this component the focus so it can generate KeyEvents

		//options panel
		optionsPanel.setVisible(false);
		optionsPanel.setOpaque(true);
//		optionsPanel.setLayout(null);
//		optionsPanel.setBounds(0,0,xPixelMaxDimension,yPixelMaxDimension);

		snakeFrame.add(snakePanel);
//		snakeFrame.add(optionsPanel);
		snakePanel.addKeyListener(new GameControls(snake));
//		snakePanel.setBounds(0,0,xPixelMaxDimension,yPixelMaxDimension);

		//add panels to layeredPane
//		layeredPane.setBounds(0,0,xPixelMaxDimension,yPixelMaxDimension);
//		layeredPane.add(snakePanel, new Integer(1));
//		layeredPane.add(optionsPanel, new Integer(2));

		//pack the snake frame
		snakeFrame.pack();
		snakeFrame.setLocationRelativeTo(null);
		snakeFrame.setVisible(true);

		//sound player
		soundPlayer = new SoundPlayer(); //sound player

		setGameStage(BEFORE_GAME);

	}

	private static void initializeGame() {
		//set up score, snake and first kibble
		xSquares = xPixelMaxDimension / squareSize;
		ySquares = yPixelMaxDimension / squareSize;

		snake = new Snake(xSquares, ySquares, squareSize);
		kibble = new Kibble(snake);
		score = new Score();
		setSoundsOn(true);

		gameStage = BEFORE_GAME;
	}

	protected static void newGame() {

		Timer timer = new Timer();
		GameClock clockTick = new GameClock(snake, kibble, score, snakePanel);
		timer.scheduleAtFixedRate(clockTick, 0 , clockInterval);
		snakePanel.repaint();


	}

	public static void main(String[] args) {
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				initializeGame();
				createAndShowGUI();
			}
		});
	}


	public static int getGameStage() {
		return gameStage;
	}

	public static boolean gameEnded() {
		if (gameStage == GAME_OVER || gameStage == GAME_WON){
			return true;
		}
		return false;
	}

	public static void setGameStage(int gameStage) {
		SnakeGame.gameStage = gameStage;
	}

	public static void setSoundsOn(boolean soundsOn) { SnakeGame.soundOn = soundsOn; }

	public static void setWarpWallsOn(boolean warpWallsOn) { SnakeGame.warpWallsOn = warpWallsOn; }

	public static void setGameSpeed(int msPerTick) {
		clockInterval = msPerTick;
		return;
	}

}
