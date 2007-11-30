import java.util.*;
public class ElevatorState {
	private Floor currentDestination;
	private Floor currentLocation;
	private java.util.ArrayList<Floor> pressedFloors;
	private int capacity;
	
	public void setCurrentDestination(Floor f)
	{
		currentDestination = f;
	}
	
	public Floor getCurrentDestination()
	{
		return currentDestination;
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
