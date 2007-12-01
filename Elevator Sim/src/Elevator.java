//The interface for the elevator
import java.util.*;

import simpack.*;
public class Elevator {
	private ElevatorState state;
	private ArrayList<Floor> floorList;
	private int totalCapacity;
	public int id;
	public Elevator(ArrayList<Floor> fl, int cap, int id)
	{
		totalCapacity = cap;
		floorList = fl;
		this.id = id;
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
		System.out.println("Elevator " + id + " told to go to floor " + fl.floorNumber);
		SimEvent event = new SimEvent();
		event.id = ElevatorEvent.ELEVATORARRIVED;
		Sim.schedule(event,Math.abs(fl.floorNumber-state.getCurrentLocation().floorNumber));
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
}

