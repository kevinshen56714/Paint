import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;


public class ToolRegister {
    private ButtonGroup buttonGroup;
    private ArrayList<JToggleButton> tools;
    private ArrayList<JButton> commands;
    public JPanel toolPanel = new JPanel();
    public JPanel commandPanel = new JPanel();
    public Redo redo;
    public Undo undo;
    public Save save;
    
 
	public ToolRegister(){
		Pencil pencil = new Pencil();
	    Eraser eraser = new Eraser();
	    Line line = new Line();
	    Curve curve = new Curve();
	    SprayPainter spraypainter = new SprayPainter();
	    FillIn fillin = new FillIn();
	    Select select = new Select();
	    Square square = new Square();
	    FilledSquare filledsquare = new FilledSquare();
	    Circle circle = new Circle();
	    FilledCircle filledcircle = new FilledCircle();
	    
		tools = new ArrayList<JToggleButton>();
		tools.add(pencil);
		tools.add(eraser);
		tools.add(line);
		tools.add(curve);
		tools.add(spraypainter);
		tools.add(fillin);
		tools.add(select);
		tools.add(square);
		tools.add(filledsquare);
		tools.add(circle);
		tools.add(filledcircle);
		
		
		NewFile newfile = new NewFile();
		OpenFile openfile = new OpenFile();
		save = new Save();
		SaveNew savenew = new SaveNew();
		undo = new Undo();
		redo = new Redo();
		Cut cut = new Cut();
		Copy copy = new Copy();
		Paste paste = new Paste();
		
		commands = new ArrayList<JButton>();
		commands.add(newfile);
		commands.add(openfile);
		commands.add(save);
		commands.add(savenew);
		commands.add(new JButton());
		commands.add(undo);
		commands.add(redo);
		commands.add(copy);
		commands.add(cut);
		commands.add(paste);
		
		buttonGroup = new ButtonGroup();
		for(JToggleButton buttons : tools)
			buttonGroup.add(buttons);
		
		JToolBar ToolBar=new JToolBar("ToolBar",JToolBar.HORIZONTAL);
		for(JToggleButton buttons : tools)
			ToolBar.add(buttons);
		
		JToolBar CommandBar=new JToolBar("Command Bar",JToolBar.HORIZONTAL);
		for(JButton buttons : commands)
			CommandBar.add(buttons);
		
		ToolBar.setFloatable(false);
		ToolBar.setLayout( new GridLayout(12,1) );
		toolPanel.add(ToolBar);
		toolPanel.setBorder(BorderFactory.createTitledBorder("Tools"));
		
		CommandBar.setFloatable(false);
		CommandBar.setLayout( new GridLayout(2,2,2,2) );
		commandPanel.add(CommandBar);
		commandPanel.setBorder(BorderFactory.createTitledBorder("Commands"));
		
		
		//Main.c.add(CommandBar,BorderLayout.NORTH);
		//Main.c.add(ToolBar,BorderLayout.WEST);
	}
}
