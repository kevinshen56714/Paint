import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class Cut extends JButton implements ActionListener{
	public Cut(){
		Icon cutimg = new ImageIcon("img/cut.png");
		setIcon(cutimg);
		addActionListener(this);
		//setSize(30,30);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
