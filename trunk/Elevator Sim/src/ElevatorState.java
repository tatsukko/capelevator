import java.util.*;
public class ElevatorState {
	private Floor currentLocation;
	private java.util.ArrayList<Floor> pressedFloors;
	private int capacity;
	public enum DirectionState {IDLE, GOINGUP, GOINGDOWN };
	public enum CapacityState { NOTFULL, FULL };
	public DirectionState directionState;
	public CapacityState capacityState;
	
	public ElevatorState(Floor init)
	{
		pressedFloors = new ArrayList<Floor>();
		capacity = 0;
		directionState = DirectionState.IDLE;
		capacityState = CapacityState.NOTFULL;
		currentLocation = init;		
	}
	public void setCurrentDestination(Floor f)
	{
		if(!pressedFloors.contains(f))
			pressedFloors.add(f);
	}
	
	public Floor getCurrentDestination()
	{
		Floor selected = null;
		for(Floor f: pressedFloors)
		{
			if(f.floorNumber>currentLocation.floorNumber&&(selected == null||f.floorNumber<selected.floorNumber))
				selected=f;
		}
		return selected;
	}
	
	public void setCurrentLocation(Floor f)
	{
		currentLocation = f;
	}
	
	public Floor getCurrentLocation()
	{
		return currentLocation;
	}
	
	public void setPressedFloors(ArrayList<Floor> f)
	{
		pressedFloors = f;
	}
	
	public ArrayList<Floor> getPressedFloors()
	{
		return pressedFloors;
	}
	
	public void setCapacity(int f)
	{
		capacity = f;
	}
	
	public int getCurrentCapacity()
	{
		return capacity;
	}
	
	
	
}
