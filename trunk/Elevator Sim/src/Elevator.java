//The interface for the elevator
import java.util.*;

import simpack.*;
public class Elevator {
	private ElevatorState state;
	private ArrayList<Floor> floorList;
	public ArrayList<Person> pList;
	private int totalCapacity;
	public int id;
	public Elevator(ArrayList<Floor> fl, int cap, int id)
	{
		totalCapacity = cap;
		floorList = fl;
		this.id = id;
		pList = new ArrayList<Person>();
		state = new ElevatorState(fl.get(0));
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
		this.state.getPressedFloors().add(fl);
		System.out.println("Elevator " + id + " told to go to floor " + fl.floorNumber + ", current floor " + this.getCurrentFloor().floorNumber);
		SimEvent event = new SimEvent();
		event.id = ElevatorEvent.ELEVATORARRIVED;
		Sim.schedule(event,5);//Math.abs(fl.floorNumber-state.getCurrentLocation().floorNumber));
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
		return	state.getCurrentCapacity();
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

