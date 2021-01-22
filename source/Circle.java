import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;


public class Circle extends JToggleButton implements ActionListener{
	public static Shape shape;
	public static Ellipse2D.Double ellipse2D = new Ellipse2D.Double();
	public Circle(){
		Icon circleimg = new ImageIcon("img/circle.png");
		setIcon(circleimg);
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ToolMgr.toolSelecting = "Circle";	
	}
	public static void render(int x1, int y1, int x2, int y2){
		shape=ellipse2D;
		if(Main.boardMgr.ctrlPressing == true)
			ellipse2D.setFrame(x1, y1, x2, y2);
		else
		ellipse2D.setFrame(Math.min(x1, x2),Math.min(y1, y2),Math.abs(x1 - x2),Math.abs(y1 - y2));
	}

}
class FilledCircle extends JToggleButton implements ActionListener{
	public static Shape shape;
	public static Ellipse2D.Double ellipse2D = new Ellipse2D.Double();
	public FilledCircle(){
		Icon filledcircleimg = new ImageIcon("img/filledcircle.png");
		setIcon(filledcircleimg);
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ToolMgr.toolSelecting = "FilledCircle";
	}
	public static void render(int x1, int y1, int x2, int y2){
		shape=ellipse2D;
		if(Main.boardMgr.ctrlPressing == true)
			ellipse2D.setFrame(x1, y1, x2, y2);
		else
		ellipse2D.setFrame(Math.min(x1, x2),Math.min(y1, y2),Math.abs(x1 - x2),Math.abs(y1 - y2));
	}
}