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
		for(Floor f:flist)
			f.update();
		do
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
		}while(Sim.heap_count!=0);
		return;
	}
	void downPressed()
	{
		if(flist.get((int)event.token.attr[0]-1).pList.size()==0)
		{
			return;
		}
		System.out.println("Down pressed on floor " + event.token.attr[0]);
		Elevator selected = null;
		for(Elevator e:elist)
		{
			if(e.getCurrentState().directionState == ElevatorState.DirectionState.IDLE&&
					e.getCurrentFloor().floorNumber==event.token.attr[0])
			{
				selected = e;
				e.openDoors();
				return;
			}
			
			if(e.getCurrentState().directionState==ElevatorState.DirectionState.IDLE)
			{
				selected = e;
			}
		}
		if(selected == null)
		{
			int distance = Integer.MAX_VALUE;
			for(Elevator e:elist)
			{
				if(e.getCurrentState().directionState == ElevatorState.DirectionState.GOINGDOWN)
				{
					if(e.getCurrentState().getCurrentDestination().floorNumber>event.token.attr[0]&&
							e.getCurrentState().getCurrentDestination().floorNumber-event.token.attr[0]<distance)
					{
						distance = (int)(e.getCurrentState().getCurrentDestination().floorNumber-event.token.attr[0]);
						selected = e;
					}
				}
				
			}
		}
		if(selected!=null)
		{
			selected.goTo(flist.get((int)event.token.attr[0]-1));
			selected.update();
		}
	}
	void upPressed()
	{
		if(flist.get((int)event.token.attr[0]-1).pList.size()==0)
		{
			return;
		}
		System.out.println("Up pressed on floor " + event.token.attr[0]);
		Elevator selected = null;
		for(Elevator e:elist)
		{
			if(e.getCurrentState().directionState == ElevatorState.DirectionState.IDLE&&
					e.getCurrentFloor().floorNumber==event.token.attr[0])
			{
				selected = e;
				e.openDoors();
				return;
			}
					
			if(e.getCurrentState().directionState==ElevatorState.DirectionState.IDLE)
			{
				selected = e;
				break;
			}
		}
		if(selected == null)
		{
			int distance = Integer.MAX_VALUE;
			for(Elevator e:elist)
			{
				if(e.getCurrentState().directionState == ElevatorState.DirectionState.GOINGUP)
				{
					if(e.getCurrentState().getCurrentDestination().floorNumber<event.token.attr[0]&&
							e.getCurrentState().getCurrentDestination().floorNumber-event.token.attr[0]<distance)
					{
						distance = (int)(e.getCurrentState().getCurrentDestination().floorNumber-event.token.attr[0]);
						selected = e;
					}
				}
				
			}
		}
		if(selected!=null)
		{
			selected.goTo(flist.get((int)event.token.attr[0]-1));
			for(Elevator e2:elist)
				e2.update();
		}
	}
	
	void floorPressed()
	{
		System.out.println("floor pressed");
	}
	
	void elevatorArrived()
	{
		ElevatorSim.egui.update();
		System.out.println("---------------------");
		elist.get((int)event.token.attr[0]-1).getCurrentState().setCurrentLocation(
				flist.get((int)event.token.attr[1]-1));
		System.out.println("elevator " + event.token.attr[0] + " arrived at " + 
				elist.get((int)event.token.attr[0]-1).getCurrentFloor().floorNumber);
		elist.get((int)event.token.attr[0]-1).openDoors();
	}
	
	void elevatorLeft()
	{
		ElevatorSim.egui.update();
		System.out.println("---------------------");
		System.out.print("elevator left " + event.token.attr[0]);
		System.out.println(" current location is " + elist.get((int)event.token.attr[0]-1).getCurrentFloor().floorNumber);
		elist.get((int)event.token.attr[0]-1).goTo(
				elist.get((int)event.token.attr[0]-1).getCurrentDestination());
		return;
		//Elevator e = elist.get(event.id);
		//int traveltime = (e.getCurrentDestination().floorNumber)-e.getCurrentFloor().floorNumber*ElevatorConst.ELEVATOR_SPEED;
		//e.id=ElevatorEvent.ELEVATORARRIVED;
		//Sim.schedule(event, 0);
	}
	
	public static void main(String args[])
	{
		
	}
}
 