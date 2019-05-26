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
	
	public TakeOff() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		screenWidth = gd.getDisplayMode().getWidth();
		screenHeight = gd.getDisplayMode().getHeight();
		
		player = new Player(60, 60, 25, 250, 5, 5);
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
                player.changeVel(0, -player.getVY());
            } else if (e.getCode() == KeyCode.A && player.getX() > 0) {
                player.changeVel(-player.getVX(), 0);
            } else if (e.getCode() == KeyCode.S && player.getY() < screenHeight) {
                player.changeVel(0, player.getVY());
            } else if (e.getCode() == KeyCode.D && player.getX() < screenWidth) {
                player.changeVel(player.getVX(), 0);
            }
        });

        canvas.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.W) {
                player.changeVel(0, 0);
            } else if (e.getCode() == KeyCode.A) {
                player.changeVel(0, 0);
            } else if (e.getCode() == KeyCode.S) {
                player.changeVel(0, 0);
            } else if (e.getCode() == KeyCode.D) {
                player.changeVel(0, 0);
            }
        });
	}
	
		
	//Loop method
	private void run(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, screenWidth, screenHeight);
		player.move(gc);
		player.draw(gc);
		gc.strokeRect(player.getX(), player.getY(), player.getWidth(),  player.getHeight());
		
		
	}
	
	//Run application
	public static void main(String[] args) {
        Application.launch(args);
    }
}
