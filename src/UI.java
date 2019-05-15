import javax.swing.JFrame;
import java.awt.*;


public class UI extends JFrame {
	
	public UI() {
		
		initialize();
	}
	
	public void initialize() {
		
		Surface surface = new Surface();
		add(surface);
		
		setTitle("Dennis the Menace");
		setSize(900, 500);
		setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        
	}
	
	public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                UI ui = new UI();
                ui.setVisible(true);
            }
        });
    }
}

