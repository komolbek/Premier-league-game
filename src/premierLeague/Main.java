package premierLeague;

import java.awt.Color;
import javax.swing.JPanel;

public class Main {

	public static void main(String[] args) {
		
		// JPanel = a GUI components that is a container for other components
//		
//		JPanel redPanel = new JPanel();
//		redPanel.setBackground(Color.red);
//		redPanel.setBounds(300, 0, 200, 200);
//		redPanel.setLayout(null);
//		
//		JPanel bluePanel = new JPanel();
//		bluePanel.setBackground(Color.blue);
//		bluePanel.setBounds(300, 250, 200, 200);
//		
//		JPanel greenPanel = new JPanel();
//		greenPanel.setBackground(Color.green);
//		greenPanel.setBounds(300,500, 200, 200);
		
		MyFrame myFrame = new MyFrame("Premier League", 400, 400);
		
//		redPanel.add(myLabel);
//		myFrame.add(redPanel);
//		myFrame.add(greenPanel);
//		myFrame.add(bluePanel);
		
		myFrame.revalidate();
	}

}
