import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.TextComponent.*;
import java.util.*;

class ElevatorGUI extends JFrame implements ActionListener{

	public JLabel jlFloor = new JLabel();
	public JLabel jlElevator = new JLabel();
	public jpElevator elevator = new jpElevator();
  	public JPanel jpButton = new JPanel();
  	public JPanel jpTextField;
	public Table jpTable = new Table();
	public Selection selection = new Selection();
  	public JTextField jtfFloor;
  	public JTextField jtfElevator;
  	public JButton apply = new JButton("Apply");
  	
	public ElevatorGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMenu(); //sets up the file menu
		setTitle("Elevator Simulator");
/*
		jtfFloor = new JTextField("");
		jtfElevator = new JTextField("");
		jpTextField = new JPanel();
		jpTextField.setLayout(new GridLayout(3,2));
		jlFloor.setText("Number of floors: ");
		jlElevator.setText("Number of elevators: ");
		jpTextField.add(jlFloor);
		jpTextField.add(jtfFloor);
		jpTextField.add(jlElevator);
		jpTextField.add(jtfElevator);
*/
		//jpTextField.add(apply);
		//jpButton.add(apply);
		//apply.addActionListener(this);

		//elevator.setBackground(Color.white);
        	JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        	splitPane.add(jpTable);
		splitPane.add(selection);
		Container container = getContentPane();	
		container.setLayout(new BorderLayout());
		container.add(elevator, BorderLayout.WEST);
		container.add(splitPane, BorderLayout.EAST);
		//container.add(selection, BorderLayout.SOUTH);
		//container.add(jpTable, BorderLayout.EAST);

	}
  	public void actionPerformed(ActionEvent e){

	}
	
    private static void createGUI() {
        //Create the GUI window.
        ElevatorGUI gui = new ElevatorGUI();
        gui.setSize(700,500);
	gui.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGUI();
            }
        });
    }
  	
  	private void addMenu(){
  		JMenuItem jmiQuit=new JMenuItem("Quit");
  		JMenu jmFile=new JMenu("File");
  		JMenuBar jmbMenu=new JMenuBar();
		jmbMenu=new JMenuBar();
		jmbMenu.add(jmFile);
		jmFile.add(jmiQuit);
		setJMenuBar(jmbMenu);
		jmiQuit.addActionListener(this);
  	}
/*
	public int getNumFloors(){
		return jtfFloor.getText();
	}

	public int getNumElevators(){
		return jtfElevator.getText();
	}
*/
} //end of Elevator class
class jpElevator extends JPanel{
	public jpElevator(){
		setPreferredSize(new Dimension(300,500));
        	setBackground(Color.gray);
	}
	
	public void paintComponent(Graphics g) {
        	super.paintComponent(g);
        	Graphics2D g2d = (Graphics2D)g;
        	g2d.setColor(Color.WHITE);
        	g2d.drawRect(0,0,300,500);
    	}
} //end of jpElevator class
class Selection extends JPanel{
	public static int numFloors, numElevators;
  	public JButton extra = new JButton("ExtraB");
  	public JButton apply = new JButton("Apply");
	public Selection(){
		//super(new BorderLayout());
		setPreferredSize(new Dimension(200,100));
        	setBackground(Color.white);
		String[] floor = { "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		String[] elevator = { "1", "2", "3", "4", "5", "6", "7", "8", "9"};

		final JComboBox cFloor = new JComboBox(floor);
		final JComboBox cElevator = new JComboBox(elevator);
        	//JPanel controlPane = new JPanel();

        	cFloor.setSelectedIndex(0);
        	cFloor.addActionListener(new ActionListener() {
            		public void actionPerformed(ActionEvent e) {
                		String num = (String)cFloor.getSelectedItem();
                		if (num.equals("1"))
                    			numFloors=1;
                		else if (num.equals("2"))
                    			numFloors=2;
                		else if (num.equals("3"))
                    			numFloors=3;
                		else if (num.equals("4"))
                    			numFloors=4;
                		else if (num.equals("5"))
                    			numFloors=5;
                		else if (num.equals("6"))
                    			numFloors=6;
                		else if (num.equals("6"))
                    			numFloors=6;
                		else if (num.equals("7"))
                    			numFloors=7;
                		else if (num.equals("8"))
                    			numFloors=8;
                		else if (num.equals("9"))
                    			numFloors=9;
            		}
        	});

		cElevator.setSelectedIndex(0);
        	cElevator.addActionListener(new ActionListener() {
            		public void actionPerformed(ActionEvent e) {
                		String num = (String)cElevator.getSelectedItem();
                		if (num.equals("1"))
                    			numElevators=1;
                		else if (num.equals("2"))
                    			numElevators=2;
                		else if (num.equals("3"))
                    			numElevators=3;
                		else if (num.equals("4"))
                    			numElevators=4;
                		else if (num.equals("5"))
                    			numElevators=5;
                		else if (num.equals("6"))
                    			numElevators=6;
                		else if (num.equals("6"))
                    			numElevators=6;
                		else if (num.equals("7"))
                    			numElevators=7;
                		else if (num.equals("8"))
                    			numElevators=8;
                		else if (num.equals("9"))
                    			numElevators=9;
            		}
        	});

		setLayout(new GridLayout(3,2));
		add(new JLabel("# of Floors:"));
        	add(cFloor);
		add(new JLabel("# of Elevators:"));
        	add(cElevator);
		add(extra);
		add(apply);

	}
/*
	private static void createAndShowGUI() {
        	//Create and set up the window.
        	JFrame frame = new JFrame("ListSelectionDemo");
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        	//Create and set up the content pane.
        	Selection a = new Selection();
        	a.setOpaque(true);
        	frame.setContentPane(a);

        	//Display the window.
        	frame.pack();
        	frame.setVisible(true);
    	}

    	public static void main(String[] args) {
        	//Schedule a job for the event-dispatching thread:
        	//creating and showing this application's GUI.
        	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            		public void run() {createAndShowGUI();}
        	});
    	}
*/
} //end of ListSelection class