import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class SaveNew extends JButton implements ActionListener{
	public SaveNew(){
		Icon savenewimg = new ImageIcon("img/savenew.png");
		setIcon(savenewimg);
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
			FileDialog fileDialog = new FileDialog( new Frame() , "Please enter your filename.", FileDialog.SAVE );
			fileDialog.setVisible(true);
			ToolMgr.toolregister.save.filename = fileDialog.getDirectory()+fileDialog.getFile();
			BufferedImage img = Main.boardMgr.latestImg;
			File f = new File(ToolMgr.toolregister.save.filename);
				try {
					ImageIO.write(img, "PNG", f);
				} 
				catch (IOException e1) {
					e1.printStackTrace();
				}
	}
}
