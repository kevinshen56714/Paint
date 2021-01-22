import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;


public class Undo extends JButton implements ActionListener{
	public Undo(){
		Icon undoimg = new ImageIcon("img/undo.png");
		setIcon(undoimg);
		addActionListener(this);
		setEnabled(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Main.boardMgr.count--;
		
		Main.boardMgr.boardInitSX=Main.boardMgr.imgRecord[Main.boardMgr.count].getWidth();
		Main.boardMgr.boardInitSY=Main.boardMgr.imgRecord[Main.boardMgr.count].getHeight();
		Main.boardMgr.setSize(Main.boardMgr.boardInitSX,Main.boardMgr.boardInitSY);

		Main.boardMgr.latestImg = new BufferedImage(Main.boardMgr.boardInitSX, Main.boardMgr.boardInitSY,BufferedImage.TYPE_3BYTE_BGR);
		Main.boardMgr.labelImg = new JLabel(new ImageIcon(Main.boardMgr.latestImg));
		Main.boardMgr.removeAll();
		Main.boardMgr.add(Main.boardMgr.labelImg);
		Main.boardMgr.labelImg.setBounds(new Rectangle(0, 0, Main.boardMgr.boardInitSX, Main.boardMgr.boardInitSY));
		
		Graphics2D latestImg2D = (Graphics2D) Main.boardMgr.latestImg.getGraphics();
		latestImg2D.setPaint(Color.white);
		latestImg2D.fill(new Rectangle2D.Double(0,0,Main.boardMgr.boardInitSX, Main.boardMgr.boardInitSY));
		latestImg2D.drawImage(Main.boardMgr.imgRecord[Main.boardMgr.count],0,0,this);

		Main.boardSizeMgr.sizeAdjustPanel.setLocation(Main.boardMgr.boardInitSX,Main.boardMgr.boardInitSY);
		
		Main.boardSizeMgr.x=Main.boardMgr.boardInitSX;
		Main.boardSizeMgr.y=Main.boardMgr.boardInitSY;
		
   		if(Main.boardMgr.count<=0)
   			setEnabled(false);
   		ToolMgr.toolregister.redo.setEnabled(true);
    	//cut=3;
		repaint();
	}

}
