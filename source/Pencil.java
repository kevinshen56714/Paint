import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;


public class Pencil extends JToggleButton implements ActionListener{
	public static Shape shape;
	public static Line2D.Double line2D = new Line2D.Double();
	public Pencil(){
		Icon pencilimg = new ImageIcon("img/pencil.png");
		setIcon(pencilimg);
		addActionListener(this);
		setSelected(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ToolMgr.toolSelecting = "Pencil";
	}
	
	public static void render(int x1, int y1, int x2, int y2){
		Graphics2D graphic= (Graphics2D)Main.boardMgr.latestImg.getGraphics();
		shape=line2D;
		line2D.setLine(x1,y1,x2,y2);
		graphic.setPaint(Main.colorMgr.getDrawingColor());
		graphic.setStroke(Main.brushMgr.getStroke());
		graphic.draw(shape);
	}
}
