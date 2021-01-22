import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;


public class FillIn extends JToggleButton implements ActionListener{
	public FillIn(){
		Icon fillinimg = new ImageIcon("img/fillin.png");
		setIcon(fillinimg);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ToolMgr.toolSelecting = "FillIn";
	}
}
