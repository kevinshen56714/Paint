import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public class OpenFile extends JButton implements ActionListener{
	public OpenFile(){
		Icon openfileimg = new ImageIcon("img/openfile.png");
		setIcon(openfileimg);
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		FileDialog fileDialog = new FileDialog( new Frame() , "please select a file.", FileDialog.LOAD );
		fileDialog.setVisible(true);
		if(fileDialog.getFile()==null) return;
		ImageIcon icon = new ImageIcon(fileDialog.getDirectory()+fileDialog.getFile());
		
		ToolMgr.toolregister.save.filename = fileDialog.getDirectory()+fileDialog.getFile();
		
		//Main.boardSizeMgr.removeAll();
		Main.boardMgr.setVisible(false);
		Main.boardMgr=null;
		Main.boardMgr=new BoardMgr();
		Main.boardSizeMgr.add(Main.boardMgr);

		Main.boardMgr.boardInitSX=icon.getIconWidth();
		Main.boardMgr.boardInitSY=icon.getIconHeight();
		Main.boardMgr.setBounds(new Rectangle(0, 0, Main.boardMgr.boardInitSX, Main.boardMgr.boardInitSY));
		Main.boardSizeMgr.sizeAdjustPanel.setLocation(Main.boardMgr.boardInitSX,Main.boardMgr.boardInitSY);
		
		
		Graphics2D latestImg2D = (Graphics2D) Main.boardMgr.latestImg.getGraphics();

		latestImg2D.drawImage(icon.getImage(),0,0,this);
		
		Main.boardMgr.count++;
		Main.boardMgr.imgRecord[Main.boardMgr.count] = new BufferedImage(Main.boardMgr.boardInitSX, Main.boardMgr.boardInitSX, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D imgRecord2D = (Graphics2D) Main.boardMgr.imgRecord[Main.boardMgr.count].getGraphics();
		imgRecord2D.drawImage(Main.boardMgr.latestImg,0,0,this);
		
		Main.boardMgr.repaint();
	}
}
