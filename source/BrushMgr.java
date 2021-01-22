import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;


public class BrushMgr extends JPanel implements ItemListener{
   
	public int currentBrushSize = 1;
	public BasicStroke stroke = new BasicStroke( currentBrushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 10);
	
	public BrushTypeMgr brushtypemgr;
	
	private JPanel sizePanel = new JPanel();
	private JPanel typePanel = new JPanel();
	
	private int brushSizeNum = 4;
	ImageIcon brushSize1 = new ImageIcon("img/brushsize1.png");
	ImageIcon brushSize2 = new ImageIcon("img/brushsize2.png");
	ImageIcon brushSize3 = new ImageIcon("img/brushsize3.png");
	ImageIcon brushSize4 = new ImageIcon("img/brushsize4.png");

    String[] brushSize = {"1", "3", "6", "12"};
    
    public BrushMgr() {
    	
        super(new BorderLayout());
    	brushtypemgr = new BrushTypeMgr();


        Integer[] intArray = new Integer[brushSizeNum];
 
        for (int i = 0; i < brushSizeNum; i++) {
            intArray[i] = new Integer(i);
        }

        //Create the combo box.
        JComboBox brushSizeBox = new JComboBox(intArray);
        ComboBoxRenderer renderer= new ComboBoxRenderer();
        renderer.setPreferredSize(new Dimension(50, 20));
        brushSizeBox.setRenderer(renderer);
        brushSizeBox.setMaximumRowCount(3);

        //Lay out the demo.
        sizePanel.add(new JLabel("Size "),BorderLayout.NORTH);
        sizePanel.add(brushSizeBox,BorderLayout.CENTER);
        add(sizePanel,BorderLayout.NORTH);
        
        typePanel.add(new JLabel("Type"),BorderLayout.NORTH);
        typePanel.add(brushtypemgr,BorderLayout.CENTER);
        add(typePanel,BorderLayout.CENTER);
        
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        brushSizeBox.addItemListener(this);
    }

    class ComboBoxRenderer extends JLabel implements ListCellRenderer {
        public ComboBoxRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        public Component getListCellRendererComponent(JList list,Object value,int index,boolean isSelected,boolean cellHasFocus) {
            int selectedIndex = ((Integer)value).intValue();

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            ImageIcon[] images = {brushSize1,brushSize2,brushSize3,brushSize4};
            ImageIcon icon = images[selectedIndex];
            setIcon(icon);
            return this;
        }

    }

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange()==ItemEvent.SELECTED){
			currentBrushSize = Integer.parseInt(brushSize[(int) e.getItem()]);
			if(brushtypemgr.currentBrushType == 1)
				stroke = new BasicStroke( currentBrushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 10);
			else if(brushtypemgr.currentBrushType == 2)
				stroke = new BasicStroke( currentBrushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 10, brushtypemgr.brushtype2, 0);
			else if(brushtypemgr.currentBrushType == 3)
				stroke = new BasicStroke( currentBrushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 10, brushtypemgr.brushtype3, 0);
	       }
	}
	
	public BasicStroke getStroke(){
		return stroke;
	}
}