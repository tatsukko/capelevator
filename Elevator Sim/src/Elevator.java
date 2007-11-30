//The interface for the elevator
import java.util.*;
public class Elevator {
	private ElevatorState state;
	private ArrayList<Floor> floorList;
	private int totalCapacity;
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

