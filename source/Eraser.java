import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;


public class Eraser extends JToggleButton implements ActionListener{
	public static Shape shape;
	public static Line2D.Double line2D = new Line2D.Double();
	public Eraser(){
		Icon eraserimg = new ImageIcon("img/eraser.png");
		setIcon(eraserimg);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ToolMgr.toolSelecting = "Eraser";
	}
	
	public static void render(int x1, int y1, int x2, int y2){
		Graphics2D graphic= (Graphics2D)Main.boardMgr.latestImg.getGraphics();
		shape=line2D;
		line2D.setLine(x1,y1,x2,y2);
		graphic.setPaint(Color.white);
		graphic.setStroke(Main.brushMgr.getStroke());
		graphic.draw(shape);
	}
}
