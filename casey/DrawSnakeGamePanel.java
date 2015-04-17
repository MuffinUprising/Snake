package casey;

import java.awt.*;
import java.util.LinkedList;

import javax.swing.JPanel;

/** This class responsible for displaying the graphics, so the snake, grid, kibble, instruction text and high score
 * 
 * @author Clara
 *
 */
public class DrawSnakeGamePanel extends JPanel {
	
	private static int gameStage = SnakeGame.BEFORE_GAME;  //use this to figure out what to paint
	
	private Snake snake;
	private Kibble kibble;
	private Score score;
	
	DrawSnakeGamePanel(Snake s, Kibble k, Score sc){
		this.snake = s;
		this.kibble = k;
		this.score = sc;
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(SnakeGame.xPixelMaxDimension, SnakeGame.yPixelMaxDimension);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        /* Where are we at in the game? 4 phases.. 
         * 1. Before game starts
         * 2. During game
         * 3. Game lost aka game over
         * 4. or, game won
         */

        gameStage = SnakeGame.getGameStage();
        
        switch (gameStage) {
			case 1: {
				displayInstructions(g);
				break;
			}
			case 2: {
				displayGame(g);
				break;
			}
			case 3: {
				displayGameOver(g);
				break;
			}
			case 4: {
				displayGameWon(g);
				break;
			}
			case 5: {
				SnakeGame.displayOptionsGUI();
				break;
			}
		}
        
        
        
    }

	private void displayGameWon(Graphics g) {
		// TODO Replace this with something really special!
		g.clearRect(100,100,350,350);
		g.drawString("YOU WON SNAKE!!!", 150, 150);
		
	}
	private void displayGameOver(Graphics g) {

		g.clearRect(0,0,550,550);
		g.drawString("GAME OVER", 150, 100);
		
		String textScore = score.getStringScore();
		String textHighScore = score.getStringHighScore();
		String newHighScore = score.newHighScore();
		
		g.drawString("SCORE = " + textScore, 150, 150);
		
		g.drawString("HIGH SCORE = " + textHighScore, 150, 200);
		g.drawString(newHighScore, 150, 250);
		
		g.drawString("press a key to play again", 150, 300);
		g.drawString("Press w to turn on warp walls.",150, 350);
		g.drawString("Press h to activate Hard Mode!", 150, 400);
		g.drawString("Press q to quit the game",150,450);

    			
	}

	private void displayGame(Graphics g) {
		displayGameGrid(g);
		displaySnake(g);
		displayKibble(g);


	}

	private void displayGameGrid(Graphics g) {

		int maxX = SnakeGame.xPixelMaxDimension;
		int maxY= SnakeGame.yPixelMaxDimension;
		int squareSize = SnakeGame.squareSize;
		
		g.clearRect(0, 0, maxX, maxY);
		g.fillRect(0,0, maxX, maxY);
		g.setColor(Color.BLACK);

		g.setColor(Color.DARK_GRAY);

		//Draw grid - horizontal lines
		for (int y=0; y <= maxY ; y+= squareSize){			
			g.drawLine(0, y, maxX, y);
		}
		//Draw grid - vertical lines
		for (int x=0; x <= maxX ; x+= squareSize){			
			g.drawLine(x, 0, x, maxY);
		}
	}

	private void displayKibble(Graphics g) {

		//Draw the kibble in green
		g.setColor(Color.CYAN);

		int x = kibble.getKibbleX() * SnakeGame.squareSize;
		int y = kibble.getKibbleY() * SnakeGame.squareSize;

		g.fillOval(x+10, y+10, SnakeGame.squareSize-20, SnakeGame.squareSize-20);
		
	}

	private void displaySnake(Graphics g) {

		LinkedList<Point> coordinates = snake.segmentsToDraw();
		
		//Draw head in dark grey
		g.setColor(Color.RED);
		Point head = coordinates.pop();
		g.fillOval((int)head.getX(), (int)head.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
		
		//Draw rest of snake in gray
		g.setColor(Color.ORANGE);
		for (Point p : coordinates) {
			g.fillRect((int)p.getX()+10, (int)p.getY()+10, SnakeGame.squareSize-20, SnakeGame.squareSize-20);
		}

	}

	private void displayInstructions(Graphics g) {
        g.drawString("Press any key to begin!",100,200);
		g.drawString("Press w to turn on warp walls. (Easier!)",100, 250);
		g.drawString("Press h to activate Hard Mode! (Harder!)", 100, 300);
        g.drawString("Press q to quit the game",100,350);
    	}
	
    
}

