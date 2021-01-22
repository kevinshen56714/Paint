import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;


public class Words extends JToggleButton implements ActionListener{
	public Words(){
		Icon wordsimg = new ImageIcon("img/words.png");
		setIcon(wordsimg);
		addActionListener(this);
		setSelected(true);
		//setSize(30,30);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
