package casey;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameControls implements KeyListener{
	
	Snake snake;

	
	GameControls(Snake s){
		this.snake = s;
	}
	
	public void keyPressed(KeyEvent ev) {

		try{
			DrawSnakeGamePanel panel = (DrawSnakeGamePanel)ev.getComponent();

			if (SnakeGame.getGameStage() == SnakeGame.BEFORE_GAME){
				//Start the game
				//BUGFIX - Fixed the "speeding up" bug by giving the game a Start key instead of any key.
				//What was happening was the game would start immediately if your reaction time was slow and didn't realize the
				//game was over, or if you double tap or tap multiple keys at the very end of a game.
				//This causes the game to immediately start a new game before the clock cycle from the first game could turn itself off.
				//So, it wasn't getting faster for the new game, there were two clock cycles. This made the snake move twice per cycle.
				//Forcing the user to hit a specific button to start a game fixes this problem.
				//NOTE - While this does work in forcing the user to start a game with s, the second game does not have this requirement.
				//I can't figure out why this would be.
				if (ev.getKeyCode() == KeyEvent.VK_S){
					SnakeGame.setGameStage(SnakeGame.DURING_GAME);
					SnakeGame.newGame();
					panel.repaint();
					return;
				}else {
					return;
				}

			}

			if (SnakeGame.getGameStage() == SnakeGame.GAME_OVER){
				snake.reset();
				Score.resetScore();
				panel.repaint();


				//Need to start the timer and start the game again
				SnakeGame.newGame();
				SnakeGame.setGameStage(SnakeGame.DURING_GAME);
				panel.repaint();
				return;
			}

			if (SnakeGame.getGameStage() == SnakeGame.PAUSE_MENU){
				DrawSnakeGamePanel.displayOptionsGUI();
				panel.repaint();
				return;
			}


			if (ev.getKeyCode() == KeyEvent.VK_DOWN) {
				SoundPlayer.playKibbleChomp();
				snake.snakeDown();
			}
			if (ev.getKeyCode() == KeyEvent.VK_UP) {
				SoundPlayer.playKibbleChomp();
				snake.snakeUp();
			}
			if (ev.getKeyCode() == KeyEvent.VK_LEFT) {
				SoundPlayer.playKibbleChomp();
				snake.snakeLeft();
			}
			if (ev.getKeyCode() == KeyEvent.VK_RIGHT) {
				SoundPlayer.playKibbleChomp();
				snake.snakeRight();
			}
			if (ev.getKeyCode() == KeyEvent.VK_ESCAPE) {
				DrawSnakeGamePanel.displayOptionsGUI();
			}
		}catch (ClassCastException cce) {
			cce.printStackTrace();
		}


	}


	@Override
	public void keyReleased(KeyEvent ev) {
		//We don't care about keyReleased events, but are required to implement this method anyway.		
	}


	@Override
	public void keyTyped(KeyEvent ev){
		//keyTyped events are for user typing letters on the keyboard, anything that makes a character display on the screen
		char keyPressed = ev.getKeyChar();
		char q = 'q';
		char w = 'w';

		if( keyPressed == q){
			System.exit(0);    //quit if user presses the q key.
		}
		if( keyPressed == w){
			SnakeGame.setWarpWallsOn(true);
		}
		if (ev.getKeyCode() == KeyEvent.VK_ESCAPE) {
			DrawSnakeGamePanel.displayOptionsGUI();
		}


	}

}
