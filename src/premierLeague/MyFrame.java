package premierLeague;

import java.awt.Color;

import javax.swing.JFrame;

public class MyFrame extends JFrame {

	MyFrame() {
		// JFrame = a GUI window to add components to. It contains all components

		this.setSize(420, 420); // This sets width and height of this
		this.setTitle("Premier League");

        // By default it is HIDE_ON_CLOSE,
		// there are also DO_NOTHING_ON_CLOSE
		this.setDefaultCloseOperation(MyFrame.DO_NOTHING_ON_CLOSE); // exit out of application

		this.setResizable(false); // prevent being resized
		this.setVisible(true); // Make this visible on screen
		this.getContentPane().setBackground(new Color(123,50,49)); // set background colour
	}
}
