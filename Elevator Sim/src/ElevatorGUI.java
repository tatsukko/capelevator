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
	//public jpElevator elevator = new jpElevator();
  	public JPanel jpButton = new JPanel();
  	public JPanel jpTextField;
	public Table jpTable = new Table();
	public Selection selection = new Selection();
  	public JTextField jtfFloor;
  	public JTextField jtfElevator;
  	public JButton apply = new JButton("Apply");
  	//public ArrayList<Elevator> elist;
  	public ArrayList<Floor> flist;
  	public ArrayList<Person> pList;
  	public static ElevatorSim esim;
  	
	public ElevatorGUI() {
		ElevatorSim.egui=this;
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
		//container.add(elevator, BorderLayout.WEST);
		container.add(splitPane, BorderLayout.EAST);
		//container.add(selection, BorderLayout.SOUTH);
		//container.add(jpTable, BorderLayout.EAST);

	}
  	public void actionPerformed(ActionEvent e){
  		System.out.println("Action Performed");
	}
	
    public static ElevatorGUI createGUI() {
        //Create the GUI window.
        ElevatorGUI gui = new ElevatorGUI();
        gui.setSize(725,550);
		gui.setVisible(true);
		return gui;
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
 //end of Elevator class
/*class jpElevator extends JPanel{
	public JButton up = new JButton("up");
	public JButton down = new JButton("down");
	private BufferedImage image_person;
	public Selection cFloor;
	
	public jpElevator(){
		setPreferredSize(new Dimension(300,500));
        setBackground(Color.WHITE);
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
        System.out.println("Printing GUI " + ElevatorGUI.esim);
        if(ElevatorGUI.esim==null)
        {
        	return;
        }
        int elevators = ElevatorGUI.esim.elist.size();
        int floors = ElevatorGUI.esim.flist.size();
        
        for(int i = 0; i < floors; i++)
        {
        	g2d.drawLine(0, i*500/4, 300, i*500/4);
        }
        if(true)
        	return;
        /*
        g2d.setColor(Color.BLACK);
        g2d.fillRect(30,400,50,50);
        g2d.drawLine(0,400,300,400);
        
        
        g2d.clearRect(250,400,20,40);
        g.drawImage(image_person, 250, 400, null);
        
        //draw lines for floors/ceilings
        int y1=400, y2=400, y3=400, y4=400;
        for (int i = 1; i < 5; i++){ //i < Integer.parseInt((String)cFloor.getSelectedItem()
        	g2d.drawLine(0,y1,300,y2);
        	y1-=50;
        	y2-=50;
        }
        
        //when the elevator arrives on the floor
        for (int i = 1; i <= 3; i++){ //i<=p.destination.floorNumber
        	g2d.setColor(Color.BLACK);
        	g2d.fillRect(30,y3,50,50);
        	y3-=50;
        	if (i>1){
        		g2d.clearRect(30,y4,50,50);
        		g2d.setColor(Color.DARK_GRAY);
        		g2d.fillRect(30,y4,50,50);
        		y4-=50;
        	}
        }
        /*
        JPanel jpButtons = new JPanel();
        up.setPreferredSize(new Dimension(10,5));
        jpButtons.add(up);
        down.setPreferredSize(new Dimension(10,5));
        jpButtons.add(down);
        add(jpButtons);
        */
    }
 //end of jpElevator class*/
class Selection extends JPanel{
	public static int numFloors, numElevators, numPeople;
  	public JButton extra = new JButton("ExtraB");
  	public JButton apply = new JButton("Apply");
  	public Selection(){
		//super(new BorderLayout());
		setPreferredSize(new Dimension(200,100));
        setBackground(Color.white);
		String[] floor = { "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		//Integer[] floor = {1,2,3,4,5,6,7,8,0};
		String[] elevator = { "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		String[] person = { "1", "2", "3", "4", "5", "6", "7", "8", "9"};

		final JComboBox cFloor = new JComboBox(floor);
		final JComboBox cElevator = new JComboBox(elevator);
		final JComboBox cPerson = new JComboBox(person);
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
        cPerson.setSelectedIndex(0);
              /*  cPerson.addActionListener(new ActionListener() {
            		public void actionPerformed(ActionEvent e) {
                		String num = (String)cElevator.getSelectedItem();
                		numElevators=Integer.parseInt(num);
            		}
        	});*/

		setLayout(new GridLayout(4,2));
		add(new JLabel("# of Floors:"));
        add(cFloor);
		add(new JLabel("# of Elevators:"));
        add(cElevator);
        add(new JLabel("# of Riders:"));
        add(cPerson);
		//add(extra);
		add(apply);
		apply.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			while (Table.model.getRowCount()>0){
    				Table.model.removeRow(0);
    				}
    			javax.swing.SwingUtilities.invokeLater(new Runnable() {
    	            public void run() {
    	            	ElevatorGUI.esim = new ElevatorSim(Integer.parseInt((String)cFloor.getSelectedItem()),
    	    					Integer.parseInt((String)cElevator.getSelectedItem()),Integer.parseInt((String)cPerson.getSelectedItem()));
    	            	//ElevatorSim.egui.elevator.repaint();
    	    			ElevatorGUI.esim.controller.control();
    	    			System.out.println("Done");
    	            }
    	        });
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