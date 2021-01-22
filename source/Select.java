import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;


public class Select extends JToggleButton implements ActionListener{
	public Select(){
		Icon selectimg = new ImageIcon("img/select.png");
		setIcon(selectimg);
		addActionListener(this);
		setSelected(true);
		//setSize(30,30);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ToolMgr.toolSelecting = "Select";
	}
}
