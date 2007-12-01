import java.util.*;
import simpack.*;
public class ElevatorController {
	public static void main(String args[])
	{
		Date date = new Date();
		//Calendar c = new Calendar.getInstance();
		simpack.SimEvent event = new SimEvent();
		long inittime = System.currentTimeMillis();
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
 