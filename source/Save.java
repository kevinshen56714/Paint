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
import javax.swing.JOptionPane;


public class Save extends JButton implements ActionListener{
	public String filename;
	public Save(){
		Icon saveimg = new ImageIcon("img/save.png");
		setIcon(saveimg);
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(filename == null){
		FileDialog fileDialog = new FileDialog( new Frame() , "Please enter your filename.", FileDialog.SAVE );
		fileDialog.setVisible(true);
		filename = fileDialog.getDirectory()+fileDialog.getFile();
		BufferedImage img = Main.boardMgr.latestImg;
		File f = new File(filename);
			try {
				ImageIO.write(img, "PNG", f);
			} 
			catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		else{
			BufferedImage img = Main.boardMgr.latestImg;
			File f = new File(filename);
				try {
					ImageIO.write(img, "PNG", f);
				} 
				catch (IOException e1) {
					e1.printStackTrace();
				}
		}
	}

}
