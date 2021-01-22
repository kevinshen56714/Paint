import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;


public class BoardSizeMgr extends JPanel implements MouseListener, MouseMotionListener{
	private static Image cursorimg;
	private static Cursor cursor;
	public int x, y;
	public JPanel sizeAdjustPanel=new JPanel();
	
	public BoardSizeMgr(){
		this.setLayout(null);
		this.add(sizeAdjustPanel);
		//create a Panel for board size adjusting
		sizeAdjustPanel.setBounds(new Rectangle(Main.boardMgr.boardInitSX, Main.boardMgr.boardInitSY, 5, 5));
		//sizeAdjustPanel.setBackground(new Color(0,0,0));
		sizeAdjustPanel.addMouseListener(this);
		sizeAdjustPanel.addMouseMotionListener(this);

	}
	
	public void mouseClicked(MouseEvent e){}
	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){
		Main.boardMgr.boardInitSX=x;
		Main.boardMgr.boardInitSY=y;
		
		sizeAdjustPanel.setLocation(Main.boardMgr.boardInitSX, Main.boardMgr.boardInitSY);

		Main.boardMgr.setSize(x, y);
		Main.boardMgr.sizeChanged();
		repaint();
	}
	
	public void mouseEntered(MouseEvent e){
		cursorimg = Toolkit.getDefaultToolkit().getImage("img/setsize.png");
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg , new Point(10,10) ,"SetSizeCursor");
		setCursor( cursor );
	}
	public void mouseExited(MouseEvent e){
		setCursor( null );
	}
	
	public void mouseDragged(MouseEvent e){
		cursorimg = Toolkit.getDefaultToolkit().getImage("img/setsize.png");
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorimg , new Point(10,10) ,"SetSizeCursor");
		setCursor( cursor );
		
		x = e.getX()+Main.boardMgr.boardInitSX;
		y = e.getY()+Main.boardMgr.boardInitSY;
		repaint();	
	}
	public void mouseMoved(MouseEvent e) {}
	
	public void paint(Graphics g) {
		Graphics2D graphic = (Graphics2D) g;
		super.paint(graphic);
		
		float brushtype[]={3};
		graphic.setPaint( Color.BLACK );
		graphic.setStroke(new BasicStroke(1,  BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 10, brushtype, 0));
		graphic.draw(new Rectangle2D.Double( 0, 0, x-1, y-1));
	}
}