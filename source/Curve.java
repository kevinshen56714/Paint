import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Line2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;


public class Curve extends JToggleButton implements ActionListener{
	public static Shape shape;
	private static CubicCurve2D.Double cubicCurve2D = new CubicCurve2D.Double();
	private static int xx1, xx2, yy1, yy2, xxx, yyy;
	public static int step = 0, curvePullTime = 0;
	public Curve(){
		Icon curveimg = new ImageIcon("img/curve.png");
		setIcon(curveimg);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ToolMgr.toolSelecting = "Curve";
	}
	
	public static void render(int x1, int y1, int x2, int y2){
		shape=cubicCurve2D;
		if(step==0){
			cubicCurve2D.setCurve(x1,y1,x1,y1,x2,y2,x2,y2);
			xx1=x1;
			yy1=y1;
			xx2=x2;
			yy2=y2;
			curvePullTime = 0;
		}
		else if(step==1){
			cubicCurve2D.setCurve(xx1,yy1,x2,y2,x2,y2,xx2,yy2);
			xxx=x2;
			yyy=y2;
			curvePullTime = 1;
		}
		else if(step==2){
			cubicCurve2D.setCurve(xx1,yy1,xxx,yyy,x2,y2,xx2,yy2);
			curvePullTime = 2;
		}
	}
}

class Line extends JToggleButton implements ActionListener{
	public static Shape shape;
	private static Line2D.Double line2D = new Line2D.Double();
	public Line(){
		Icon lineimg = new ImageIcon("img/line.png");
		setIcon(lineimg);
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ToolMgr.toolSelecting = "Line";
	}
	
	public static void render(int x1, int y1, int x2, int y2){
		shape=line2D;
		line2D.setLine(x1, y1, x2, y2);
	}
}