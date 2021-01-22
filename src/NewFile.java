import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class NewFile extends JButton implements ActionListener{
	public NewFile(){
		Icon newfileimg = new ImageIcon("img/newfile.png");
		setIcon(newfileimg);
		addActionListener(this);
		//setSize(30,30);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Main.boardSizeMgr.remove(Main.boardMgr);
		Main.boardMgr=null;
		Main.boardMgr=new BoardMgr();
		Main.boardSizeMgr.add(Main.boardMgr);
		Main.boardMgr.setBounds(new Rectangle(0, 0, Main.boardMgr.boardInitSX, Main.boardMgr.boardInitSX));
		Main.boardSizeMgr.sizeAdjustPanel.setLocation(Main.boardMgr.boardInitSX, Main.boardMgr.boardInitSX);

		repaint();
		
	}

}

