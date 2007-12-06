import java.util.*;
public class ElevatorState {
	private Floor currentLocation;
	private java.util.ArrayList<Floor> pressedFloors;
	private int capacity;
	public enum DirectionState {IDLE, GOINGUP, GOINGDOWN, BUSY };
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
		//System.out.println("Finding current destination");
		//System.out.println("Current location is " + currentLocation.floorNumber);
		if(pressedFloors.size()==0)
		{
			directionState = DirectionState.IDLE;
			return getCurrentLocation();
		}
		if(directionState == DirectionState.GOINGUP)
		{
			for(Floor f: pressedFloors)
			{
				//System.out.println(f.floorNumber);
				if(f.floorNumber>=currentLocation.floorNumber&&(selected == null||f.floorNumber<selected.floorNumber))
					selected=f;
			}
			if(selected == null)
			{
				//System.out.println("switching directions to find destination" );
				directionState = DirectionState.GOINGDOWN;
				selected = getCurrentDestination();
				
			}
			//System.out.println(selected.floorNumber + "selected");
			return selected;
		}
		else if(directionState == DirectionState.GOINGDOWN)
		{
			for(Floor f: pressedFloors)
			{
				//System.out.println(f.floorNumber);
				if(f.floorNumber<=currentLocation.floorNumber&&(selected == null||f.floorNumber>selected.floorNumber))
					selected=f;
			}
			if(selected == null)
			{
				//System.out.println("switching directions to find destination" );
				directionState = DirectionState.GOINGUP;
				selected = getCurrentDestination();
				
			}
			//System.out.println(selected.floorNumber + "selected");
			return selected;
		}
		else 
		{
			//System.out.println("direction err, forcing elevator to go up");
			directionState = DirectionState.GOINGUP;
			return getCurrentDestination();
		}
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
	
	/*public int getCurrentCapacity()
	{
		return capacity;
	}*/
	
	
	
}
