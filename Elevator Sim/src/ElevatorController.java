import java.util.*;
import simpack.*;
public class ElevatorController {
	ArrayList<Elevator> elist;
	ArrayList<Floor> flist;
	SimEvent event;
	public ElevatorController(ArrayList<Elevator> elist, ArrayList<Floor> flist)
	{
		this.elist = elist;
		this.flist = flist;
	}
	public void control()
	{
		while(true)
		{
			event = Sim.next_event(System.currentTimeMillis(), Const.SYNC);
			switch(event.id){
			case -1:
				continue;
			case ElevatorEvent.DOWNPRESSED:
				downPressed();
				break;
			case ElevatorEvent.UPPRESSED:
				upPressed();
				break;
			case ElevatorEvent.FLOORPRESSED:
				floorPressed();
				break;
			case ElevatorEvent.ELEVATORARRIVED:
				elevatorArrived();
				break;
			case ElevatorEvent.ELEVATORLEFT:
				elevatorLeft();
				break;
			
			}
		}
	}
	void downPressed()
	{
		
	}
	void upPressed()
	{
		
	}
	
	void floorPressed()
	{
		
	}
	
	void elevatorArrived()
	{
		
	}
	
	void elevatorLeft()
	{
		
	}
	
	public static void main(String args[])
	{
		
	}
}
 