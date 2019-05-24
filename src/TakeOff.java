/**
 * 
 * @author Benjamen Bielecki, Dennis Perepech
 * @version 1.0
 * 
 */

import javafx.scene.text.*;
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
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.input.KeyCode;


public class TakeOff extends Application {
	
	public TakeOff() {
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//create the canvas
		Canvas canvas = new Canvas(1280, 720);
		canvas.setFocusTraversable(true);
		
		//Create GraphicContext object
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		//Loop frequency
		Timeline tme = new Timeline(new KeyFrame(Duration.millis(15), e -> run(gc)));
		tme.setCycleCount(Timeline.INDEFINITE);
		
		//handle click events
		primaryStage.setTitle("TakeOff");
		primaryStage.setScene(new Scene(new StackPane(canvas)));
		primaryStage.show();
		
		primaryStage.setFullScreen(true);
		
		tme.play();
	}
	
	private void run(GraphicsContext gc) {
		Color c = Color.rgb(77, 198, 61);
        gc.setFill(c);
	}
	
	//Run application
	public static void main(String[] args) {
        Application.launch(args);
    }
}
