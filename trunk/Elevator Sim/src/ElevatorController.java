import java.util.*;
import simpack.*;
public class ElevatorController {
	public static void main(String args[])
	{
		Date date = new Date();
		Calendar c = Calendar();
		simpack.SimEvent event = new SimEvent();
		Sim.init(Calendar.get(Calendar.SECOND),Const.HEAP);
		event.id = 0;
		Sim.schedule(event,3);
		event.id = 1;
		Sim.schedule(event,5);
		while(true)
		{
			event = Sim.next_event(c.get(Calendar.SECOND));
			if(event.id!=-1)
				System.out.println("event " + event.id);
		}
	}
}
