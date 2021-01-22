import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;


public class BrushTypeMgr extends JPanel implements ItemListener{
	public int currentBrushType = 1;
	public float[] brushtype2 = {10}, brushtype3 = {20,15,3,15};
	
	private int brushTypeNum = 3;
	public JComboBox brushTypeBox;
	ImageIcon brushType1 = new ImageIcon("img/brushtype1.png");
	ImageIcon brushType2 = new ImageIcon("img/brushtype2.png");
	ImageIcon brushType3 = new ImageIcon("img/brushtype3.png");

    String[] brushType = {"1", "2", "3"};

    public BrushTypeMgr() {

        Integer[] intArray = new Integer[brushTypeNum];
 
        for (int i = 0; i < brushTypeNum; i++) {
            intArray[i] = new Integer(i);
        }
        
        JComboBox brushTypeBox = new JComboBox(intArray);
        TypeComboBoxRenderer renderer= new TypeComboBoxRenderer();
        renderer.setPreferredSize(new Dimension(50, 20));
        brushTypeBox.setRenderer(renderer);
        brushTypeBox.setMaximumRowCount(3);
        brushTypeBox.addItemListener(this);
        
        add(brushTypeBox);
    }

    class TypeComboBoxRenderer extends JLabel implements ListCellRenderer {
        public TypeComboBoxRenderer() {
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

            ImageIcon[] images = {brushType1,brushType2,brushType3};
            ImageIcon icon = images[selectedIndex];
            setIcon(icon);
            return this;
        }

    }

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange()==ItemEvent.SELECTED){
			currentBrushType = Integer.parseInt(brushType[(int) e.getItem()]);
			if(currentBrushType == 1)
				Main.brushMgr.stroke = new BasicStroke( Main.brushMgr.currentBrushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 10);
			else if(currentBrushType == 2)
				Main.brushMgr.stroke = new BasicStroke( Main.brushMgr.currentBrushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 10, brushtype2, 0);
			else if(currentBrushType == 3)
				Main.brushMgr.stroke = new BasicStroke( Main.brushMgr.currentBrushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 10, brushtype3, 0);
	       }
	}
}
