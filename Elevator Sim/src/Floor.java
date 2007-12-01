import java.util.*;
public class Floor {
	public double floorNumber;
	public ArrayList<Elevator> elevatorList;
	public Floor(ArrayList<Elevator> el, double fn)
	{
		floorNumber = fn;
		elevatorList = el;
	}
}
