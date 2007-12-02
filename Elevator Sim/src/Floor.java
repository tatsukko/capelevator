import java.util.*;
public class Floor {
	public double floorNumber;
	public ArrayList<Elevator> elevatorList;
	public ArrayList<Person>	pList;
	public Floor(ArrayList<Elevator> el, double fn)
	{
		floorNumber = fn;
		elevatorList = el;
		pList = new ArrayList<Person>();
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
}
