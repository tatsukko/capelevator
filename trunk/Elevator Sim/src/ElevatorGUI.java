import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

//import java.awt.Font;
//import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
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
  	public ArrayList<Elevator> elist;
  	public ArrayList<Floor> flist;
  	public ElevatorSim esim;
  	
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
  		System.out.println("Action Performed");
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

		jmiQuit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
		});
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
	public JButton up = new JButton("up");
	public JButton down = new JButton("down");
	private BufferedImage image_person;
	//public int x,y;
	
	public jpElevator(){
		setPreferredSize(new Dimension(300,500));
        setBackground(Color.gray);
        BufferedImage img = null;
		try {
			File f = new File("person.gif");
    		image_person = ImageIO.read(f);
		} catch (IOException e) {
			System.out.println("error reading gif "+ e);
		}
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(Color.WHITE);
        g2d.drawRect(0,0,300,500);
        	
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(30,0,50,500);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(30,400,50,50);
        g2d.drawLine(0,400,300,400);
        
        g2d.clearRect(250, 400, 20, 40);
        g.drawImage(image_person, 250, 400, null);
        
        //up.setPreferredSize(new Dimension(10,5));
        //add(up);
        //down.setPreferredSize(new Dimension(10,5));
        //add(down);
        
        /*int centerX = 5 / 2;  //getWidth()=5,getHeight()=10
    	int hipsHeight = 10 / 3;
    	int hipsY = 10 - hipsHeight;
    	int neckY = hipsY - 10 / 3;
    	int armsY = 10 - 10 / 2;
    	int headX = 5 / 2 - 10 / 6;
    
    	g.setColor(Color.white);
    	// draw legs
    	g.drawLine(0, 10, centerX, hipsY);
    	g.drawLine(centerX, hipsY, 5, 10);
    	//draw torso
    	g.drawLine(centerX, hipsY, centerX, neckY);
    	// draw arms
    	g.drawLine(0, armsY, 5, armsY);
    	// draw head
    	g.fillOval(headX, 0, 5/3, 10/3);
    	*/

    }
} //end of jpElevator class
class Selection extends JPanel{
	public static int numFloors, numElevators;
  	public JButton extra = new JButton("ExtraB");
  	public JButton apply = new JButton("Apply");
  	public ElevatorSim esim;
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
      /*  cFloor.addActionListener(new ActionListener() {
            		public void actionPerformed(ActionEvent e) {
                		String num = (String)cFloor.getSelectedItem();
                		numFloors=Integer.parseInt(num);
                    }
        	});*/

		cElevator.setSelectedIndex(0);
      /*  cElevator.addActionListener(new ActionListener() {
            		public void actionPerformed(ActionEvent e) {
                		String num = (String)cElevator.getSelectedItem();
                		numElevators=Integer.parseInt(num);
            		}
        	});*/

		setLayout(new GridLayout(3,2));
		add(new JLabel("# of Floors:"));
        add(cFloor);
		add(new JLabel("# of Elevators:"));
        add(cElevator);
		//add(extra);
		add(apply);
		apply.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			esim = new ElevatorSim(Integer.parseInt((String)cFloor.getSelectedItem()),
    					Integer.parseInt((String)cElevator.getSelectedItem()),3*Integer.parseInt((String)cFloor.getSelectedItem()));
    			esim.controller.control();
    		}

		});
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