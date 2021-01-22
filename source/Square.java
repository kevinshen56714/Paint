import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;


public class Square extends JToggleButton implements ActionListener{
	public static Shape shape;
	public static Rectangle2D.Double rectangle2D = new Rectangle2D.Double();
	public Square(){
		Icon squareimg = new ImageIcon("img/square.png");
		setIcon(squareimg);
		addActionListener(this);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ToolMgr.toolSelecting = "Square";

	}
	public static void render(int x1, int y1, int x2, int y2){
		shape=rectangle2D;
		if(Main.boardMgr.ctrlPressing == true)
			rectangle2D.setRect(x1, y1, x2, y2);
		else
			rectangle2D.setRect(Math.min(x1, x2),Math.min(y1, y2),Math.abs(x1 - x2),Math.abs(y1 - y2));
	}

}
class FilledSquare extends JToggleButton implements ActionListener{
	public static Shape shape;
	public static Rectangle2D.Double rectangle2D = new Rectangle2D.Double();
	public FilledSquare(){
		Icon filledsquareimg = new ImageIcon("img/filledsquare.png");
		setIcon(filledsquareimg);
		addActionListener(this);

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ToolMgr.toolSelecting = "FilledSquare";
	}
	
	public static void render(int x1, int y1, int x2, int y2){
		shape=rectangle2D;
		if(Main.boardMgr.ctrlPressing == true)
			rectangle2D.setRect(x1, y1, x2, y2);
		else
		rectangle2D.setRect(Math.min(x1, x2),Math.min(y1, y2),Math.abs(x1 - x2),Math.abs(y1 - y2));
	}

}