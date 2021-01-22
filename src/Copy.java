import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Copy extends JButton implements ActionListener{
	public Copy(){
		Icon copyimg = new ImageIcon("img/copy.png");
		setIcon(copyimg);
		addActionListener(this);
		//setSize(30,30);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
