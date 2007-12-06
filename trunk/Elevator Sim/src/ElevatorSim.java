import java.util.*;
import simpack.*;

public class ElevatorSim {
	ArrayList<Elevator> elist;
	ArrayList<Floor> flist;
	ElevatorController controller;
	long inittime;
	static ElevatorGUI egui;
	public ElevatorSim(int floors, int elevators, int people)
	{
		ElevatorGUI.esim=this;
		System.out.println("Constructing simulation with " + floors + " floors, " 
				+ elevators + " elevators, and " + people + " people");
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
		for(int i = 0; i < people; i++)
		{
			int current = (int)(Math.random()*floors);
			int destination = (int)(Math.random()*floors);
			if(destination==current)
			{
				i--;
				continue;
			}
			Person p = new Person(flist.get(current), flist.get(destination),i+1);
			p.start();
			flist.get(current).addPerson(p);
		}
		
		inittime = System.currentTimeMillis();
		Sim.init(inittime, Const.HEAP);
		System.out.println("initial state");
		for(Elevator elevator:elist)
		{
			System.out.println(elevator);
		}
		for(Floor floor:flist)
		{
			System.out.println(floor);
		}
		System.out.println("---------------------");
		egui.update();
		controller = new ElevatorController(elist, flist);
	}
	
	public static void main(String args[])
	{
		egui = ElevatorGUI.createGUI();
		
	}
	public String toString()
	{
		String s="";
		for(Elevator elevator:elist)
		{
			s+=(elevator.toString())+"\n";
		}
		for(Floor floor:flist)
		{
			s+=floor+"\n";
		}
		return s;
	}
}
