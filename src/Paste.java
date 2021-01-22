import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Paste extends JButton implements ActionListener{
	public Paste(){
		Icon pasteimg = new ImageIcon("img/paste.png");
		setIcon(pasteimg);
		addActionListener(this);
		//setSize(30,30);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
