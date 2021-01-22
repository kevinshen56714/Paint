import java.awt.BorderLayout;
import java.awt.Paint;

import javax.swing.BorderFactory;


public class ColorMgr {
public static ColorRegister colorregister;
	
	public ColorMgr(){
		colorregister = new ColorRegister();
		colorregister.setBorder(BorderFactory.createTitledBorder("Colors"));
		//Main.c.add(colorregister,BorderLayout.EAST);
	}
	
	public Paint getColor1(){
		return colorregister.color1;
	}
	
	
	public Paint getColor2(){
		return colorregister.color2;
	}
	
	public Paint getDrawingColor(){
		return colorregister.drawingColor;
	}
	
	public Paint getNotDrawingColor(){
		if(colorregister.drawingColor == colorregister.color1)
			return colorregister.color2;
		else
			return colorregister.color1;
	}
}
