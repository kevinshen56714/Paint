import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

public class ToolMgr {
	
	public static ToolRegister toolregister;
	public static String toolSelecting = "Pencil";
	public static Shape shape;
	public static Line2D.Double line2D = new Line2D.Double();
	
	public ToolMgr(){
		toolregister = new ToolRegister();
	}
	
	public void render(int x1, int y1, int x2, int y2){
		switch(toolSelecting){
		case "Pencil":
			Pencil.render(x1, y1, x2, y2);
			shape = Pencil.shape;
			break;
			
		case "Eraser":
			Eraser.render(x1, y1, x2, y2);
			shape = Eraser.shape;
			break;
		
		case "Line":
			Line.render(x1, y1, x2, y2);
			shape = Line.shape;
			break;
		
		case "Curve":
			Curve.render(x1, y1, x2, y2);
			shape = Curve.shape;
			break;
		
		case "Square":
			Square.render(x1, y1, x2, y2);
			shape = Square.shape;
			break;
		
		case "FilledSquare":
			FilledSquare.render(x1, y1, x2, y2);
			shape = FilledSquare.shape;
			break;
		
		case "Circle":
			Circle.render(x1, y1, x2, y2);
			shape = Circle.shape;
			break;
		
		case "FilledCircle":
			FilledCircle.render(x1, y1, x2, y2);
			shape = FilledCircle.shape;
			break;
		}	
	}
	
	public Shape getShape(){
		return shape;
	}
	
	
}

