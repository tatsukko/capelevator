//The interface for the elevator
import java.util.*;

import simpack.*;
public class Elevator {
	private ElevatorState state;
	private ArrayList<Floor> floorList;
	public ArrayList<Person> pList;
	private int totalCapacity;
	public int id;
	private SimEvent event;
	public Elevator(ArrayList<Floor> fl, int cap, int id)
	{
		totalCapacity = cap;
		floorList = fl;
		this.id = id;
		pList = new ArrayList<Person>();
		state = new ElevatorState(fl.get(0));
		event = new SimEvent();
	}
	Floor getCurrentDestination()
	{
		return state.getCurrentDestination();
	}
	void setCurrentDestination(Floor f)
	{
		state.setCurrentDestination(f);
	}
	java.util.ArrayList<Floor> getFloorsRequested()
	{
		return state.getPressedFloors();
	}
	java.util.ArrayList<Floor> getFloorList()
	{
		return floorList;
	}
	void goTo(Floor fl)
	{
		if(fl.pList.size()==0)
			return;
		//System.out.println("goto");
		if(!this.state.getPressedFloors().contains(fl) && fl.floorNumber!=this.state.getCurrentLocation().floorNumber)
		{
			this.state.getPressedFloors().add(fl);
			this.state.getCurrentDestination();
		}
		
		if(this.state.directionState==ElevatorState.DirectionState.IDLE)
		{
			System.out.println("Elevator e " + this.id + " is now idle");
			for(Floor f:floorList)
			{
				f.update();
			}	
			return;
		}
		System.out.print("Elevator " + id);
		System.out.print(" told to go to floor " + fl.floorNumber);
		System.out.println(", current floor " + this.getCurrentFloor().floorNumber);
		event.id = ElevatorEvent.ELEVATORARRIVED;
		event.token.attr[0]=this.id;
		event.token.attr[1]=getCurrentDestination().floorNumber;
		int traveltime = Math.abs(((this.getCurrentDestination().floorNumber)-
				this.getCurrentFloor().floorNumber)*ElevatorConst.ELEVATOR_SPEED);
		System.out.println("travel time is " + traveltime);
		event.id=ElevatorEvent.ELEVATORARRIVED;
		Sim.schedule(event, traveltime);
	}
	public void openDoors()
	{
		System.out.println("opening gates " + id + " at floor " + this.getCurrentFloor().floorNumber);
		this.getCurrentState().getPressedFloors().remove(this.getCurrentFloor());
		int trans = state.getCurrentLocation().transferPeople(this);
		if(trans==0&&pList.size()==0)
		{
			System.out.println("empty and no one wants in, switching direction ");
			if(this.getCurrentState().directionState==ElevatorState.DirectionState.GOINGUP)
				this.getCurrentState().directionState = ElevatorState.DirectionState.GOINGDOWN;
			else if(this.getCurrentState().directionState==ElevatorState.DirectionState.GOINGDOWN)
				this.getCurrentState().directionState = ElevatorState.DirectionState.GOINGUP;
			else System.out.println("weird state " + this.getCurrentState().directionState);
			trans = state.getCurrentLocation().transferPeople(this);
			
		}
		System.out.print("after people transfered current destination is ");
		System.out.print(this.getCurrentDestination().floorNumber);
		event.id=ElevatorEvent.ELEVATORLEFT;
		event.token.attr[0]=this.id;
		this.state.directionState = ElevatorState.DirectionState.BUSY;
		Sim.schedule(event, ElevatorConst.ELEVATOR_ENTRY*(trans)+
				ElevatorConst.ELEVATOR_OPEN + ElevatorConst.ELEVATOR_CLOSE);
		for(Person p:pList)
		{
			if(!state.getPressedFloors().contains(p.destination))
			{
				state.getPressedFloors().add(p.destination);
			}
		}
		
	}
	public void update()
	{
		if(this.state.getPressedFloors().isEmpty())
			this.state.directionState = ElevatorState.DirectionState.IDLE;
		else if(this.state.getPressedFloors().get(0).floorNumber>this.getCurrentFloor().floorNumber)
			this.state.directionState = ElevatorState.DirectionState.GOINGUP;
		else if(this.state.getPressedFloors().get(0).floorNumber<this.getCurrentFloor().floorNumber)
			this.state.directionState = ElevatorState.DirectionState.GOINGDOWN;
		else System.out.println(""+this.state.getPressedFloors().get(0).floorNumber);
	}
	Floor getCurrentFloor()
	{
		return state.getCurrentLocation();
	}
	int	getCurrentCapacity()
	{
		return	pList.size();
	}
	int	getTotalCapacity()
	{
		return	totalCapacity;
	}
	ElevatorState getCurrentState()
	{
		return state;
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Elevator id " + id );
		return sb.toString();
	}
}

