package premierLeague;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MyFrame extends JFrame implements ActionListener {
	
	JButton myButton;
	MyLabel myLabel;

	MyFrame(String title, int width, int height) {
		
		// JFrame = a GUI window to add components to. It contains all components

		this.createButton();
		this.createLabel();
		
		this.setBounds(0, 0, width, height);  // This sets width and height of this
		this.setTitle(title);
		this.setLayout(null);

        // By default it is HIDE_ON_CLOSE,
		// there are also DO_NOTHING_ON_CLOSE
		this.setDefaultCloseOperation(MyFrame.EXIT_ON_CLOSE); // exit out of application
		this.setResizable(true); // prevent being resized
		this.setVisible(true); // Make this visible on screen
		this.getContentPane().setBackground(new Color(123,50,49)); // set background colour
	}
	
	private void createButton() {
		this.myButton = new JButton();
		myButton.setBounds(150,100, 100, 50);
		/* myButton.addActionListener(e -> System.out.println("Hii")); // lambda */
		myButton.addActionListener(this);
		myButton.setText("Click me!");
//		myButton.setFocusable(true);
		myButton.setFont(new Font("Comic sans", Font.BOLD, 15));
		this.add(this.myButton);
	}
	
	private void createLabel() {
		String myLabelString = "My name is Kamol";
		this.myLabel = new MyLabel(myLabelString, 200, 100);
		this.add(this.myLabel);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.myButton) {
			if (this.myLabel.isVisible()) {
				this.myLabel.setVisible(false);
			} else {
				this.myLabel.setVisible(true);
			}
			
		}
	}
}
