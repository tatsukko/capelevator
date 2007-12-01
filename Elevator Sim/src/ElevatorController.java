import java.util.*;
import simpack.*;
public class ElevatorController {
	public static void main(String args[])
	{
		SimEvent event = new SimEvent();
		long inittime = System.currentTimeMillis();
		
		Elevator e1;
		Elevator e2;
		Floor f1;
		Floor f2;
		Floor f3;
		Floor f4;
		Floor f5;
		
		ArrayList<Elevator> elist = new ArrayList<Elevator>();
		ArrayList<Floor> flist = new ArrayList<Floor>();
		
		e1 = new Elevator(flist, 5);
		e2 = new Elevator(flist, 5);
		
		f1 = new Floor(elist, 1);
		f2 = new Floor(elist, 2);
		f3 = new Floor(elist, 3);
		f4 = new Floor(elist, 4);
		f5 = new Floor(elist, 5);
		
		elist.add(e1);
		elist.add(e2);
		
		flist.add(f1);
		flist.add(f2);
		flist.add(f3);
		flist.add(f4);
		flist.add(f5);
		
		Sim.init(inittime,Const.HEAP);
		event.id = 0;
		Sim.schedule(event,3000);
		event.id = 1;
		Sim.schedule(event,5000);
		
		while(true)
		{
			event = Sim.next_event(System.currentTimeMillis(),Const.SYNC);
			if(event.id!=-1)
				System.out.println("event " + event.id + " at " + (System.currentTimeMillis()-inittime)/1000);
		}  
	}
}
 