import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BoardMgr extends JPanel implements MouseMotionListener, MouseListener{
	public static int count, x1, x2, y1, y2, xinit, yinit, redoLimit;
	public BufferedImage latestImg;
	public BufferedImage imgRecord[];
	public JLabel labelImg;
	public int boardInitSX = Main.frameSX-100, boardInitSY = Main.frameSY-155;
	public boolean mousePressing, ctrlPressing = false, mouseClicked = false;
	private static Image cursorimg;
	private static Cursor cursor;
	
	public BoardMgr(){
		imgRecord = new BufferedImage[100];
		latestImg = new BufferedImage(boardInitSX, boardInitSY,BufferedImage.TYPE_3BYTE_BGR);
		labelImg = new JLabel(new ImageIcon(latestImg));

		this.setLayout(null);
		this.add(labelImg);
		labelImg.setBounds(new Rectangle(0, 0, boardInitSX, boardInitSY));
	
		//set the board to be totally white
		Graphics2D latestImg2D = (Graphics2D) latestImg.getGraphics();
		latestImg2D.setPaint(Color.WHITE);
		latestImg2D.fill(new Rectangle2D.Double(0,0,boardInitSX,boardInitSY));
	
		imgRecord[count] = new BufferedImage(boardInitSX, boardInitSY, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D imgRecord2D = (Graphics2D) imgRecord[count].getGraphics();
		imgRecord2D.drawImage(latestImg,0,0,this);
	
		repaint();
		addMouseListener(this);
		addMouseMotionListener(this);
		
		//
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyEventDispatcher(){
        	 public boolean dispatchKeyEvent(KeyEvent e) {
                 if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_CONTROL ) {
                	 ctrlPressing = true;
                 } else if (e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == KeyEvent.VK_CONTROL) {
                	 ctrlPressing = false;
                 } 
                 return false;
             }
        });
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		if(ToolMgr.toolSelecting == "Pencil"){
			Pencil.render(x1,y1,x2,y2);
			x1=e.getX();
			y1=e.getY();
		}
		else if(ToolMgr.toolSelecting == "Eraser"){
			Eraser.render(x1,y1,x2,y2);
			x1=e.getX();
			y1=e.getY();
		}
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x2 = e.getX();
		y2 = e.getY();
		if(ToolMgr.toolSelecting == "Pencil" ||ToolMgr.toolSelecting == "Eraser")
			repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(mouseClicked = false)
			mouseClicked = true;
		else
			mouseClicked = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		setCursorImg(); //custom method, in order to set cursor image
		setCursor( cursor );
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		x1 = e.getX();
		y1 = e.getY();
		xinit = x1;//for ctrlPressingDrawing use
		yinit = y1;
		
		mousePressing=true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {//for image showed when finish drawing
		x2 = e.getX();
		y2 = e.getY();
		if(Curve.curvePullTime == 0)//in order to control curve
			Curve.step=1;
		else if(Curve.curvePullTime == 1)
			Curve.step=2;
		
		if(ToolMgr.toolSelecting != "Curve"|| (Curve.curvePullTime == 2 && Curve.step == 2)){
			if(x1<0 || y1<0) return;
			if(ctrlPressing == true){
				ctrlPressingDrawing();
			}
			else if(ctrlPressing == false){
				Main.toolMgr.render(x1,y1,x2,y2);
			}
			
			Graphics2D latestImg2D = (Graphics2D) latestImg.getGraphics();
			//if(cut!=2){
			if(ToolMgr.toolSelecting != "Eraser"){
				if(ToolMgr.toolSelecting == "FilledSquare"|| ToolMgr.toolSelecting == "FilledCircle"){
					latestImg2D.setPaint(Main.colorMgr.getNotDrawingColor());
					latestImg2D.fill(Main.toolMgr.getShape());
				}
				
				latestImg2D.setPaint(Main.colorMgr.getDrawingColor());
				latestImg2D.setStroke(Main.brushMgr.getStroke());
				latestImg2D.draw(Main.toolMgr.getShape());
			}
			
			repaint();
			clear();

			redoLimit=count++;
			ToolMgr.toolregister.redo.setEnabled(false);

			imgRecord[count] = new BufferedImage(boardInitSX, boardInitSX, BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D imgRecord2D = (Graphics2D) imgRecord[count].getGraphics();
			imgRecord2D.drawImage(latestImg,0,0,this);

			mousePressing=false;
			if(count>0)
				ToolMgr.toolregister.undo.setEnabled(true);
		}
	}
	
	public void paint(Graphics g) {//for image showed when drawing
		Graphics2D graphic = (Graphics2D) g;
		super.paint(graphic);

		if(mousePressing==true && x1>0 && y1>0) {//paint only when mouse is pressing and also avoid mousepressed out of the board 
			if(ctrlPressing == true){
				ctrlPressingDrawing();
			}
			else if(ctrlPressing == false){
				Main.toolMgr.render(x1,y1,x2,y2);
			}
			if(ToolMgr.toolSelecting == "Eraser") return;
			
			if(ToolMgr.toolSelecting == "FilledSquare"|| ToolMgr.toolSelecting == "FilledCircle"){
				graphic.setPaint(Main.colorMgr.getNotDrawingColor());
				graphic.fill(Main.toolMgr.getShape());
				}
			graphic.setPaint(Main.colorMgr.getDrawingColor());
			graphic.setStroke(Main.brushMgr.getStroke());
			graphic.draw(Main.toolMgr.getShape());
		}
		
		if(mouseClicked = true && ToolMgr.toolSelecting == "FillIn"){
			g.drawImage(latestImg,
                   0, 0, boardInitSX, boardInitSY,     /* dst rectangle */
                   xinit/4, yinit/4, xinit*3/4, yinit*3/4, /* src area of image */
                   null);
		}
		/*if(drawMethod==10 && cut==0){
			g2d.setPaint(Color.black);
			g2d.setStroke(basicStroke_select);
			rectangle2D_select.setRect(Math.min(x1,x2),Math.min(y1,y2),Math.abs(x1-x2),Math.abs(y1-y2));
			g2d.draw(rectangle2D_select);
		}
		if(cut==1){
			g2d.setPaint(Color.black);
			g2d.setStroke(basicStroke_select);
			rectangle2D_select.setRect(select_x,select_y,select_w,select_h);
			g2d.draw(rectangle2D_select);
		}
		if(cut==2){
				g2d.drawImage(bufImg_cut,x2,y2,this);
			}*/
	}
	
	public void clear(){
		//cut=select_x=select_y=select_w=select_h=step_chk_arc=step_arc=first=step_chk=step=0;
		Curve.curvePullTime = Curve.step = 0;
		x1 = x2 = y1 = y2 = -1;
	}
	
	public static void setCursorImg(){
		switch(ToolMgr.toolSelecting){
			case "Pencil":
				cursorimg = Toolkit.getDefaultToolkit().getImage("img/pencil.png");
				cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg , new Point(0,26) ,"PencilCursor" );
				break;
			case "Eraser":
				cursorimg = Toolkit.getDefaultToolkit().getImage("img/eraser.png");
				cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg , new Point(3,23) ,"EraserCursor" );
				break;
			case"Line":
				cursorimg = Toolkit.getDefaultToolkit().getImage("img/line.png");
				cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg , new Point(0,5) ,"LineCursor" );
				break;
			case"Curve":
				cursorimg = Toolkit.getDefaultToolkit().getImage("img/curve.png");
				cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg , new Point(0,5) ,"CurveCursor" );
				break;
			case"SprayPainter":
				cursorimg = Toolkit.getDefaultToolkit().getImage("img/spraypainter.png");
				cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg , new Point(0,25) ,"SprayPainterCursor" );
				break;
			case"FillIn":
				cursorimg = Toolkit.getDefaultToolkit().getImage("img/fillin.png");
				cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg , new Point(0,25) ,"FillInCursor" );
				break;
			case"Select":
				cursorimg = Toolkit.getDefaultToolkit().getImage("img/select.png");
				cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg , new Point(0,25) ,"SelectCursor" );
				break;
			case"Square":
				cursorimg = Toolkit.getDefaultToolkit().getImage("img/square.png");
				cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg , new Point(4,4) ,"SquareCursor" );
				break;
			case"FilledSquare":
				cursorimg = Toolkit.getDefaultToolkit().getImage("img/filledsquare.png");
				cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg , new Point(4,4) ,"FilledSquareCursor" );
				break;
			case"Circle":
				cursorimg = Toolkit.getDefaultToolkit().getImage("img/circle.png");
				cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg , new Point(0,25) ,"CircleCursor" );
				break;
			case"FilledCircle":
				cursorimg = Toolkit.getDefaultToolkit().getImage("img/filledcircle.png");
				cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg , new Point(0,25) ,"FilledCircleCursor" );
				break;
		}
		/*if(Main.boardSizeMgr.sizeAdjusting = true){
			cursorimg = Toolkit.getDefaultToolkit().getImage("img/setsize.png");
			cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg , new Point(10,10) ,"SetSizeCursor");
		}*/
	}
	
	public static void ctrlPressingDrawing(){
		if(ToolMgr.toolSelecting == "Line"){
			if(Math.abs(x2-x1) > Math.abs(y2-y1))
				Main.toolMgr.render(x1, y1, x2, y1);//horizontal line
			else
				Main.toolMgr.render(x1, y1, x1, y2);//vertical line
		}
		
		else if(ToolMgr.toolSelecting == "Square" || ToolMgr.toolSelecting == "FilledSquare" || 
				ToolMgr.toolSelecting == "Circle" || ToolMgr.toolSelecting == "FilledCircle"){
			if(Math.abs(x2-x1) > Math.abs(y2-y1))
				Main.toolMgr.render(xinit, yinit, Math.abs(x2-xinit), Math.abs(x2-xinit));
			else
				Main.toolMgr.render(xinit, yinit, Math.abs(y2-yinit), Math.abs(y2-yinit));
		}
	}
	
	public void sizeChanged(){//for sizeAdjust in BoardSizeMgr
		
		latestImg = new BufferedImage(boardInitSX, boardInitSY,BufferedImage.TYPE_3BYTE_BGR);
		labelImg = new JLabel(new ImageIcon(latestImg));
		this.removeAll();
		this.add(labelImg);
		labelImg.setBounds(new Rectangle(0, 0, boardInitSX, boardInitSY));

		Graphics2D latestImg2D = (Graphics2D) latestImg.getGraphics();
		latestImg2D.setPaint(Color.WHITE);
		latestImg2D.fill(new Rectangle2D.Double(0, 0, boardInitSX, boardInitSY));
		latestImg2D.drawImage(imgRecord[count], 0, 0, this);
		
		redoLimit=count++;
		if(count>0)
   			ToolMgr.toolregister.undo.setEnabled(true);
		
		imgRecord[count] = new BufferedImage(boardInitSX,  boardInitSY, BufferedImage.TYPE_3BYTE_BGR);
		Graphics2D imRecord2D = (Graphics2D) imgRecord[count].getGraphics();
		imRecord2D.drawImage(latestImg , 0, 0, this);
			
		mousePressing=false;
	}
}

