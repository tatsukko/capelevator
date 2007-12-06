import java.util.*;
import simpack.*;
public class Floor {
	public int floorNumber;
	public ArrayList<Elevator> elevatorList;
	public ArrayList<Person>	pList;
	public boolean up;
	public boolean down;
	SimEvent event;
	public Floor(ArrayList<Elevator> el, int fn)
	{
		floorNumber = fn;
		elevatorList = el;
		pList = new ArrayList<Person>();
		up = false;
		down = false;
		event = new SimEvent(); 
	}
	
	public void addPerson(Person p)
	{
		pList.add(p);
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Floor number " + floorNumber);
		for(Person p:pList)
		{
			sb.append("\n\t"+p.toString());
		}
		return sb.toString();
	}
	//transfer as many people into elevator that are going same direction as elevator
	public int transferPeople(Elevator e)
	{
		int transfered = e.pList.size();
		for(int i = 0; i < e.pList.size(); i++)
		{
			Person p = e.pList.get(i);
			if(p.destination.floorNumber == this.floorNumber)
			{
				System.out.println("person leaving elevator " + e.id + " on floor " + this.floorNumber);
				e.pList.remove(i);
				i--;
			}
		}
		transfered-=e.pList.size();
		transfered+=this.pList.size();
		int available = e.getTotalCapacity()-e.getCurrentCapacity();
		if(e.getCurrentState().directionState == ElevatorState.DirectionState.IDLE)
		{
			if(pList.size()!=0&&pList.get(0).destination.floorNumber>this.floorNumber)
			{
				System.out.println(e.id + " Now going up");
				e.getCurrentState().directionState = ElevatorState.DirectionState.GOINGUP;
			}
			else if(pList.size()!=0&&pList.get(0).destination.floorNumber<this.floorNumber)
			{
				System.out.println(e.id + " Now going down");
				e.getCurrentState().directionState = ElevatorState.DirectionState.GOINGDOWN;
			}
			
		}
		for(int i = 0; i < pList.size(); i++)
		{
			
			Person p = pList.get(i);
			if(e.getCurrentState().directionState == ElevatorState.DirectionState.GOINGUP)
			{
				if(p.destination.floorNumber > this.floorNumber)
				{
					System.out.println("person entering elevator " + e.id + " from floor " + this.floorNumber + 
							" destination is " + p.destination.floorNumber);
					pList.remove(p);
					i--;
					p.state = Person.ELEVATOR;
					e.pList.add(p);
					if(!e.getFloorsRequested().contains(p.destination))
					{
						e.getFloorsRequested().add(p.destination);
					}
					available--;
				}
			}
			else if(e.getCurrentState().directionState == ElevatorState.DirectionState.GOINGDOWN)
			{
				if(p.destination.floorNumber < this.floorNumber)
				{
					System.out.println("person entering elevator " + e.id + " from floor " + this.floorNumber + 
							" destination is " + p.destination.floorNumber);
					pList.remove(p);
					p.state = Person.ELEVATOR;
					e.pList.add(p);
					if(!e.getFloorsRequested().contains(p.destination))
					{
						e.getFloorsRequested().add(p.destination);
					}
					available--;
				}
			}
			
			if(available==0)
			{
				e.getCurrentState().capacityState=ElevatorState.CapacityState.FULL;
				System.out.println("Elevator " + e.id + " is now full");
				break;
			}
		}
		transfered-=this.pList.size();
		System.out.println("transfered " + transfered + " on floor " + this.floorNumber + " number of people still on floor is " + pList.size());
		return transfered;
	}
	//Get all the people on the floor to either exit, press up, or press down
	public void update()
	{
		for(int i = 0; i < pList.size(); i++)
		{
			Person p = pList.get(i);
			if(p.destination.floorNumber>this.floorNumber)
			{
				up = true;
			}
			else if(p.destination.floorNumber<this.floorNumber)
			{
				down = true;
			}
			else
			{
				pList.remove(i);
			}
		}
		if(up)
		{
			event.id = ElevatorEvent.UPPRESSED;
			event.token.attr[0]=this.floorNumber;
			Sim.schedule(event,0);
		}
		if(down)
		{
			event.id = ElevatorEvent.DOWNPRESSED;
			event.token.attr[0]=this.floorNumber;
			Sim.schedule(event, 0);
		}
	}
}
