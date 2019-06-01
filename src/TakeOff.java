/**
 *
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 *
 */
import javafx.scene.text.*;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;

public class TakeOff extends Application {
	private int screenWidth, screenHeight;
	private Player player;
	private boolean gameOver;
	private ObstacleGen obstacleGen;
	Timeline tme;

	int[] rVals = {240, 0, 0, 0};
	int[] gVals = {245, 10, 0, 0};
	int[] bVals = {250, 96, 0, 0};
	int[] r2Vals = {120, 240, 0, 0};
	int[] g2Vals = {190, 245, 10, 0};
	int[] b2Vals = {250, 250, 96, 0};


	public TakeOff() {
		gameOver = false;
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth()/2;
		screenHeight = gd.getDisplayMode().getHeight();


		player = new Player(screenWidth / 2 + (0.5 * 25), screenHeight - 250, 30, 300);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		//create the canvas
		Canvas canvas = new Canvas(screenWidth, screenHeight);
		canvas.setFocusTraversable(true);

		//Create GraphicContext object
		GraphicsContext gc = canvas.getGraphicsContext2D();

		obstacleGen = new ObstacleGen(gc);

		//Loop frequency
		Timeline tme = new Timeline(new KeyFrame(Duration.millis(16.66), e -> run(gc)));
		Timeline clock = new Timeline(new KeyFrame(Duration.millis(10), e -> obstacleGen.counterAdd()));
		tme.setCycleCount(Timeline.INDEFINITE);
		clock.setCycleCount(Timeline.INDEFINITE);

		primaryStage.setTitle("TakeOff");
		primaryStage.setScene(new Scene(new StackPane(canvas)));
		primaryStage.show();
		primaryStage.setFullScreen(true);
		tme.play();
		clock.play();

	      // handle key events
        canvas.setOnKeyPressed(e -> {
        	if(!gameOver) {
		    	if(e.getCode() == KeyCode.W && player.getY() > 0) {
		            player.setVY(-5f);
		        }if (e.getCode() == KeyCode.A && player.getX() > 0) {
		            player.setVX(-5f);
		        }if (e.getCode() == KeyCode.S && player.getY() < screenHeight) {
		            player.setVY(5f);
		        }if (e.getCode() == KeyCode.D && player.getX() < screenWidth) {
		            player.setVX(5f);
		    	}
        	}
        	else {
        		primaryStage.close();
        		Platform.runLater( () -> {
					try {
						new TakeOff().start( new Stage() );
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} );
        	}
        });

        canvas.setOnKeyReleased(e -> {
        	if(!gameOver) {
	            if (e.getCode() == KeyCode.W) {
	            	player.setVY(0f);
	            } else if (e.getCode() == KeyCode.A) {
	            	player.setVX(0f);
	            } else if (e.getCode() == KeyCode.S) {
	            	player.setVY(0f);
	            } else if (e.getCode() == KeyCode.D) {
	            	player.setVX(0f);
	            }
        	}
        	else {
        		primaryStage.close();
        		Platform.runLater( () -> {
					try {
						new TakeOff().start( new Stage() );
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} );
        	}
        });
	}



	//Loop method
	private void run(GraphicsContext gc) {

		int r = 0;
		int g = 0;
		int b = 0;
		int r2 = 0;
		int g2 = 0;
		int b2 = 0;



		if(obstacleGen.getCounter() >= 0 && obstacleGen.getCounter() < obstacleGen.STAGE_1) {
			r = rVals[0];
			g = gVals[0];
			b = bVals[0];
			r2 = r2Vals[0] + (int) ((r2Vals[1] - r2Vals[0]) * (double) obstacleGen.getCounter()/obstacleGen.STAGE_1);
			g2 = g2Vals[0] + (int) ((g2Vals[1] - g2Vals[0]) * (double) obstacleGen.getCounter()/obstacleGen.STAGE_1);
			b2 = b2Vals[0];
		}
		else if(obstacleGen.getCounter() >= obstacleGen.STAGE_1 && obstacleGen.getCounter() < obstacleGen.STAGE_2) {
			r = rVals[0] + (int) ((rVals[1] - rVals[0]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_1)/(obstacleGen.STAGE_2  - obstacleGen.STAGE_1));
			g = gVals[0] + (int) ((gVals[1] - gVals[0]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_1)/(obstacleGen.STAGE_2  - obstacleGen.STAGE_1));
			b = bVals[0] + (int) ((bVals[1] - bVals[0]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_1)/(obstacleGen.STAGE_2  - obstacleGen.STAGE_1));
			r2 = r2Vals[1];
			g2 = g2Vals[1];
			b2 = b2Vals[1];
		}
		else if(obstacleGen.getCounter() >= obstacleGen.STAGE_2 && obstacleGen.getCounter() < obstacleGen.STAGE_3) {
			r = rVals[1] + (int) ((rVals[2] - rVals[1]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_2)/(obstacleGen.STAGE_3  - obstacleGen.STAGE_2));
			g = gVals[1] + (int) ((gVals[2] - gVals[1]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_2)/(obstacleGen.STAGE_3  - obstacleGen.STAGE_2));
			b = bVals[1] + (int) ((bVals[2] - bVals[1]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_2)/(obstacleGen.STAGE_3  - obstacleGen.STAGE_2));
			r2 = r2Vals[1] + (int) ((r2Vals[2] - r2Vals[1]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_2)/(obstacleGen.STAGE_3  - obstacleGen.STAGE_2));
			g2 = g2Vals[1] + (int) ((g2Vals[2] - g2Vals[1]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_2)/(obstacleGen.STAGE_3  - obstacleGen.STAGE_2));
			b2 = b2Vals[1] + (int) ((b2Vals[2] - b2Vals[1]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_2)/(obstacleGen.STAGE_3  - obstacleGen.STAGE_2));
		}
		else if(obstacleGen.getCounter() >= obstacleGen.STAGE_3 && obstacleGen.getCounter() < obstacleGen.STAGE_4) {
			r = rVals[2] + (int) ((rVals[3] - rVals[2]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_3)/(obstacleGen.STAGE_4  - obstacleGen.STAGE_3));
			g = gVals[2] + (int) ((gVals[3] - gVals[2]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_3)/(obstacleGen.STAGE_4  - obstacleGen.STAGE_3));
			b = bVals[2] + (int) ((bVals[3] - bVals[2]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_3)/(obstacleGen.STAGE_4  - obstacleGen.STAGE_3));
			r2 = r2Vals[2] + (int) ((r2Vals[3] - r2Vals[2]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_3)/(obstacleGen.STAGE_4  - obstacleGen.STAGE_3));
			g2 = g2Vals[2] + (int) ((g2Vals[3] - g2Vals[2]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_3)/(obstacleGen.STAGE_4  - obstacleGen.STAGE_3));
			b2 = b2Vals[2] + (int) ((b2Vals[3] - b2Vals[2]) * (double) (obstacleGen.getCounter()  - obstacleGen.STAGE_3)/(obstacleGen.STAGE_4  - obstacleGen.STAGE_3));

			gc.setFill(Color.BLACK);
			gc.fillText("" + obstacleGen.checkCollisionPlayerAll(player), 50, 50);

			if(obstacleGen.checkCollisionPlayerAll(player)) {
				gameOver = true;
				gc.fillText("NICE JOB! YOU LASTED " + obstacleGen.getCounter() + " SECONDS!", 50, 200);
			}

			gc.fillText("" + obstacleGen.getCounter(), 100, 50);
			System.out.println(obstacleGen.getCounter());

			if(obstacleGen.getCounter() > 0 && obstacleGen.getCounter() < 240 && obstacleGen.getCounter() % 10 == 0) {
				obstacleGen.makeHarder();
			}
			else if(obstacleGen.getCounter() % 11 == 0) {
				obstacleGen.makeReadyToUpdate();
			}


			gc.setStroke(Color.WHITE);
			gc.strokeRect(player.getCornerCoords()[0][0], player.getCornerCoords()[0][1], player.getWidth(), player.getHeight());
		}
		else if(obstacleGen.getCounter() >= obstacleGen.STAGE_4) {

		}

        Stop[] stops = new Stop[] {new Stop(1, Color.rgb(r, g, b)), new Stop(0, Color.rgb(r2, g2, b2))};
	    LinearGradient lg1 = new LinearGradient(0, 1, 0, 0, true, CycleMethod.NO_CYCLE, stops);
        gc.setFill(lg1);
        gc.fillRect(0, 0, screenWidth, screenHeight);

		player.move(gc);
		player.draw(gc);

		obstacleGen.drawAll();
		obstacleGen.moveAll();

		gc.setFill(Color.BLACK);
		gc.fillText("" + obstacleGen.checkCollisionPlayerAll(player), 50, 50);
		gc.fillText("" + obstacleGen.getCounter(), 100, 50);
		System.out.println(obstacleGen.getCounter());

		gc.setStroke(Color.WHITE);
		gc.strokeRect(player.getCornerCoords()[0][0], player.getCornerCoords()[0][1], player.getWidth(), player.getHeight());
	}

	//Run application
	public static void main(String[] args) {
        Application.launch(args);
    }
}
