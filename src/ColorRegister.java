import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;


public class ColorRegister extends JPanel implements MouseListener, ActionListener{
	private int choosingLength = 48, choiceLength = 22;
	private int xSpace = 33, ySpace = 18;
	private int colorNum = 21;
	
	private BufferedImage bufferedImg = new BufferedImage(choosingLength ,choosingLength,BufferedImage.TYPE_3BYTE_BGR),
			bufferedImg2 = new BufferedImage(choosingLength ,choosingLength,BufferedImage.TYPE_3BYTE_BGR);
	
	private	JPanel choiceInside[] = new JPanel[colorNum];
	private	JPanel choiceFrame[] = new JPanel[colorNum];
	private JToggleButton choosingButton1 = new JToggleButton("Color1",new ImageIcon(bufferedImg),true);
	private JToggleButton choosingButton2 = new JToggleButton("Color2",new ImageIcon(bufferedImg2),false);
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JButton editColor = new JButton("Edit color", new ImageIcon("img/editcolor.png"));
	
	public Paint color1,color2,drawingColor;
	private Graphics2D graphic;

	private	int colorColumn1[][]={
		{  0,139,255,255,255,255,255},
		{  0,  0,  0,140,165,215,255},
		{  0,  0,  0,  0,  0,  0,  0}
	};
	private	int colorColumn2[][]={
		{169, 85,  0, 34, 50,  0,173},
		{169,107,100,139,205,255,255},
		{169, 47,  0, 34, 50,  0, 47}
	};
	private	int colorColumn3[][]={
		{255, 75,  0,  0, 65, 30,135},
		{255,  0,  0,  0,105,144,206},
		{255,130,205,255,225,255,250}
	};
	
	public ColorRegister(){
		addMouseListener(this);
		this.setLayout(null);
		color1 = drawingColor = new Color(0,0,0);
		color2 = new Color(255,255,255);
		
		for(int i=0;i<choiceInside.length;i++){
			choiceInside[i]=new JPanel();
			choiceInside[i].setLayout(new GridLayout(1,1));
			choiceInside[i].setBounds(new Rectangle(1, 1, choiceLength+1, choiceLength+1));
			if(i<(colorNum/3))
				choiceInside[i].setBackground(new Color(colorColumn1[0][i],colorColumn1[1][i],colorColumn1[2][i]));
			else if(i<(colorNum*2/3) && i>=(colorNum/3))
				choiceInside[i].setBackground(new Color(colorColumn2[0][i-colorNum/3],colorColumn2[1][i-colorNum/3],colorColumn2[2][i-colorNum/3]));
			else
				choiceInside[i].setBackground(new Color(colorColumn3[0][i-colorNum*2/3],colorColumn3[1][i-colorNum*2/3],colorColumn3[2][i-colorNum*2/3]));
		}
		
		for(int i=0;i<choiceFrame.length;i++){
			choiceFrame[i]=new JPanel();
			choiceFrame[i].setLayout(null);
			choiceFrame[i].add(choiceInside[i]);
			this.add(choiceFrame[i]);
			if(i<colorNum/3)
				choiceFrame[i].setBounds(new Rectangle(2*(choosingLength+4)+i*(choiceLength+4)+xSpace, ySpace, choiceLength+4, choiceLength+4));
			else if(i<colorNum*2/3 && i>=colorNum/3)
				choiceFrame[i].setBounds(new Rectangle(2*(choosingLength+4)+(i-colorNum/3)*(choiceLength+4)+xSpace, ySpace+(choiceLength+4), choiceLength+4, choiceLength+4));
			else
				choiceFrame[i].setBounds(new Rectangle(2*(choosingLength+4)+(i-colorNum*2/3)*(choiceLength+4)+xSpace, ySpace+2*(choiceLength+4), choiceLength+4, choiceLength+4));
			choiceFrame[i].setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
		}
		
		Graphics2D graphic = bufferedImg2.createGraphics();
		graphic.setPaint(Color.white);
		graphic.fill( new Rectangle2D.Double( 0, 0, choosingLength, choosingLength ) );
		repaint();

		this.add(choosingButton1);
		this.add(choosingButton2);
		
		choosingButton1.setLocation(10,15);
		choosingButton1.setSize(60,85);
		choosingButton1.setVerticalTextPosition(AbstractButton.TOP);
		choosingButton1.setHorizontalTextPosition(AbstractButton.CENTER);
		choosingButton1.addActionListener(this);
		
		choosingButton2.setLocation(73,15);
		choosingButton2.setSize(60,85);
		choosingButton2.setVerticalTextPosition(AbstractButton.TOP);
		choosingButton2.setHorizontalTextPosition(AbstractButton.CENTER);
		choosingButton2.addActionListener(this);
		
		buttonGroup.add(choosingButton1);
		buttonGroup.add(choosingButton2);
		
		this.add(editColor);
		editColor.setLocation(325,15);
		editColor.setSize(67,85);
		editColor.setVerticalTextPosition(AbstractButton.TOP);
		editColor.setHorizontalTextPosition(AbstractButton.CENTER);
		editColor.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == editColor){
			if(choosingButton1.isSelected()){
				graphic = bufferedImg.createGraphics();
				color1 = drawingColor = JColorChooser.showDialog( this, "Choose the color you like for Color1.", (Color)color1 );
				graphic.setPaint(color1);
				graphic.fill( new Rectangle( 0, 0, choosingLength, choosingLength ) );
				repaint();
			}
			else if(choosingButton2.isSelected()){
				graphic = bufferedImg2.createGraphics();
				color2 = drawingColor = JColorChooser.showDialog( this, "Choose the color you like for Color2.", (Color)color2 );
				graphic.setPaint(color2);
				graphic.fill( new Rectangle( 0, 0, choosingLength, choosingLength ) );
				repaint();
			}
		}	
		else if(e.getSource() == choosingButton1)
			drawingColor = color1;
		else if(e.getSource() == choosingButton2)
			drawingColor = color2;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		int column1Choosed, column2Choosed, column3Choosed;
		if(e.getY() > ySpace && e.getY() < ySpace+(choiceLength+4)){
			column1Choosed = (e.getX()-xSpace-2*(choosingLength+4))/(choiceLength+4);
			if(column1Choosed < colorNum/3){
				if(choosingButton1.isSelected()){
					graphic = bufferedImg.createGraphics();
					graphic.setPaint(new Color(colorColumn1[0][column1Choosed],colorColumn1[1][column1Choosed],colorColumn1[2][column1Choosed]));
					graphic.fill( new Rectangle( 0, 0, choosingLength, choosingLength ) );
					color1 = drawingColor = new Color(colorColumn1[0][column1Choosed],colorColumn1[1][column1Choosed],colorColumn1[2][column1Choosed]);
					repaint();
				}
				else{
					graphic = bufferedImg2.createGraphics();
					graphic.setPaint(new Color(colorColumn1[0][column1Choosed],colorColumn1[1][column1Choosed],colorColumn1[2][column1Choosed]));
					graphic.fill( new Rectangle( 0, 0, choosingLength, choosingLength ) );
					color2 = drawingColor = new Color(colorColumn1[0][column1Choosed],colorColumn1[1][column1Choosed],colorColumn1[2][column1Choosed]);
					repaint();
				}
			}
		}
		
		else if(e.getY() > ySpace+(choiceLength+4) && e.getY() < ySpace+2*(choiceLength+4)){
			column2Choosed = (e.getX()-xSpace-2*(choosingLength+4))/(choiceLength+4);
			if(column2Choosed < colorNum/3){
				if(choosingButton1.isSelected()){
					graphic = bufferedImg.createGraphics();
					graphic.setPaint(new Color(colorColumn2[0][column2Choosed],colorColumn2[1][column2Choosed],colorColumn2[2][column2Choosed]));
					graphic.fill( new Rectangle( 0, 0, choosingLength, choosingLength ) );
					color1 = drawingColor = new Color(colorColumn2[0][column2Choosed],colorColumn2[1][column2Choosed],colorColumn2[2][column2Choosed]);
					repaint();
				}
				else{
					graphic = bufferedImg2.createGraphics();
					graphic.setPaint(new Color(colorColumn2[0][column2Choosed],colorColumn2[1][column2Choosed],colorColumn2[2][column2Choosed]));
					graphic.fill( new Rectangle( 0, 0, choosingLength, choosingLength ) );
					color2 = drawingColor = new Color(colorColumn2[0][column2Choosed],colorColumn2[1][column2Choosed],colorColumn2[2][column2Choosed]);
					repaint();
				}
			}
		}
		
		else if(e.getY() > ySpace+2*(choiceLength+4) && e.getY() < ySpace+3*(choiceLength+4)){
			column3Choosed = (e.getX()-xSpace-2*(choosingLength+4))/(choiceLength+4);
			if(column3Choosed < colorNum/3){
				if(choosingButton1.isSelected()){
					graphic = bufferedImg.createGraphics();
					graphic.setPaint(new Color(colorColumn3[0][column3Choosed],colorColumn3[1][column3Choosed],colorColumn3[2][column3Choosed]));
					graphic.fill( new Rectangle( 0, 0, choosingLength, choosingLength ) );
					color1 = drawingColor = new Color(colorColumn3[0][column3Choosed],colorColumn3[1][column3Choosed],colorColumn3[2][column3Choosed]);
					repaint();
				}
				else{
					graphic = bufferedImg2.createGraphics();
					graphic.setPaint(new Color(colorColumn3[0][column3Choosed],colorColumn3[1][column3Choosed],colorColumn3[2][column3Choosed]));
					graphic.fill( new Rectangle( 0, 0, choosingLength, choosingLength ) );
					color2 = drawingColor = new Color(colorColumn3[0][column3Choosed],colorColumn3[1][column3Choosed],colorColumn3[2][column3Choosed]);
					repaint();
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
