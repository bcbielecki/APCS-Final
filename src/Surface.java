
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Surface extends JPanel {

	private void drawGraphics(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		int height = getHeight();
		int width = getWidth();
		
		int h = (int) (Math.random() * getHeight()) + 1;
		int w = (int) (Math.random() * getWidth()) + 1;
		
		for (int i = 1; i < 100; i++) {
			
			int red = (int) (Math.random() * 255) + 1;
			int green = (int) (Math.random() * 255) + 1;
			int blue = (int) (Math.random() * 255) + 1;
			
			g2d.setColor(new Color(red, green, blue));
			g2d.fillOval(w/i, h/i, w/i, h/i);
		
			
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		drawGraphics(g);
	}
}
