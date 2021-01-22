import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;


public class SprayPainter extends JToggleButton implements ActionListener{
	public SprayPainter(){
		Icon spraypainterimg = new ImageIcon("img/spraypainter.png");
		setIcon(spraypainterimg);
		addActionListener(this);
		setSelected(true);
		//setSize(30,30);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ToolMgr.toolSelecting = "SprayPainter";
	}
}
