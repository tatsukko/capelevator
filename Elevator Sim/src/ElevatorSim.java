import java.util.*;
import simpack.*;

public class ElevatorSim {
	ArrayList<Elevator> elist;
	ArrayList<Floor> flist;
	ElevatorController controller;
	long inittime;
	public ElevatorSim(int floors, int elevators)
	{
		elist = new ArrayList<Elevator>();
		flist = new ArrayList<Floor>();
		for(int i = 0; i < floors; i++)
		{
			flist.add(new Floor(elist, i+1));
		}
		for(int i = 0; i < elevators; i++)
		{
			elist.add(new Elevator(flist, 5, i+1));
		}
		controller = new ElevatorController(elist, flist);
		inittime = System.currentTimeMillis();
		Sim.init(inittime, Const.HEAP);
	}
	public static void main(String args[])
	{
		
	}
	
}
