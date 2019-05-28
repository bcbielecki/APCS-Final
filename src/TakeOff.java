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
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;

public class TakeOff extends Application {
	private int screenWidth, screenHeight;
	private Player player;
	private Cloud[] cloud;
	
	public TakeOff() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth()/2;
		screenHeight = gd.getDisplayMode().getHeight();
		
		player = new Player(screenWidth / 2 + (0.5 * 25), screenHeight - 250, 50, 500);
		//cloud = new Cloud(screenWidth / 2, screenHeight / 2, 100, 50, 5, 5);
		cloud = new Cloud[10];
		for(int i = 0; i < 5; i++) {
			cloud[i] = new Cloud(Math.random() * screenWidth, Math.random() * 20 - 20, 200.0, 100.0, 0, 10.0);
		}
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		//create the canvas
		Canvas canvas = new Canvas(screenWidth, screenHeight);
		canvas.setFocusTraversable(true);
		
		//Create GraphicContext object
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		//Loop frequency
		Timeline tme = new Timeline(new KeyFrame(Duration.millis(20), e -> run(gc)));
		tme.setCycleCount(Timeline.INDEFINITE);
		

		primaryStage.setTitle("TakeOff");
		primaryStage.setScene(new Scene(new StackPane(canvas)));
		primaryStage.show();
		primaryStage.setFullScreen(true);
		tme.play(); 
		
	      // handle key events
        canvas.setOnKeyPressed(e -> {
        	if(e.getCode() == KeyCode.W) {
        		if(player.getY() > 0)
                player.setVY(-5f);
            }if (e.getCode() == KeyCode.A && player.getX() > 0) {
                player.setVX(-5f);
            }if (e.getCode() == KeyCode.S && player.getY() < screenHeight) {
                player.setVY(5f);
            }if (e.getCode() == KeyCode.D && player.getX() < screenWidth) {
                player.setVX(5f);
            }
        });

        canvas.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.W) {
            	player.setVY(0f);
            } else if (e.getCode() == KeyCode.A) {
            	player.setVX(0f);
            } else if (e.getCode() == KeyCode.S) {
            	player.setVY(0f);
            } else if (e.getCode() == KeyCode.D) {
            	player.setVX(0f);
            }
        });
	}
	
		
	//Loop method
	private void run(GraphicsContext gc) {
		gc.setFill(Color.rgb(119, 193, 239));
		gc.fillRect(0, 0, screenWidth, screenHeight);
		player.move(gc);
		player.draw(gc);
		
		gc.setStroke(Color.WHITE);
		gc.strokeRect(player.getX(), player.getY(), player.getWidth(), player.getHeight());
		
		for(int i = 0; i < 10; i++) {
			cloud[i].move(gc);
			cloud[i].draw(gc);
			gc.strokeRect(cloud[i].getX(), cloud[i].getY(), cloud[i].getWidth(), cloud[i].getHeight());
			gc.setFill(Color.BLACK);
			gc.fillText(String.valueOf(cloud[i].checkCollision(player)), 500, 500);
			System.out.println(cloud[i].checkCollision(player));
		}
	}
	
	//Run application
	public static void main(String[] args) {
        Application.launch(args);
    }
}
