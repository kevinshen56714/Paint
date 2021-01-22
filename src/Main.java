import java.awt.*;

import javax.swing.*;

public class Main{
	
	public static ToolMgr toolMgr;
	public static ColorMgr colorMgr;
	public static BoardMgr boardMgr;
	public static BoardSizeMgr boardSizeMgr;
	public static BrushMgr brushMgr;

	public static Container c;

	
	public static int frameSX = 850, frameSY = 730;
	

	private static void createAndShowGUI() {
		initFrame();
		DisplayMgr();
	}
	
	public static void  main(String[] args){
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
	}
	public static void initFrame() {
		ImageIcon Painter = new ImageIcon("img/Painter.png");
		JFrame frm = new JFrame("Painter");
		frm.setSize(frameSX, frameSY);
		frm.setLocation(100, 0);
		frm.setVisible(true);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.setIconImage(Painter.getImage());
		c=frm.getContentPane();
	}

	private static void DisplayMgr() {

		JPanel upperMainPanel = new JPanel();
		upperMainPanel.setLayout( new BorderLayout() );
		//upperMainPanel.setLayout( new FlowLayout(FlowLayout.LEFT) );
	
		colorMgr = new ColorMgr();
		boardMgr = new BoardMgr();
		boardSizeMgr = new BoardSizeMgr();
		toolMgr = new ToolMgr();
		brushMgr = new BrushMgr();
		brushMgr.setBorder(BorderFactory.createTitledBorder("Brushes"));
		
		upperMainPanel.add(ToolMgr.toolregister.commandPanel,BorderLayout.WEST);
		//upperMainPanel.add(ToolMgr.toolregister.toolPanel,BorderLayout.EAST);
		upperMainPanel.add(ColorMgr.colorregister,BorderLayout.CENTER);
		upperMainPanel.add(brushMgr,BorderLayout.EAST);
		c.add(upperMainPanel,BorderLayout.NORTH);
		c.add(ToolMgr.toolregister.toolPanel,BorderLayout.WEST);
		
		boardSizeMgr.setLayout(null);
		boardSizeMgr.add(boardMgr);
		c.add(boardSizeMgr, BorderLayout.CENTER);
		boardMgr.setBounds(new Rectangle(0, 0, boardMgr.boardInitSX, boardMgr.boardInitSY));
		//c.add(boardMgr,BorderLayout.CENTER);
	}
}
