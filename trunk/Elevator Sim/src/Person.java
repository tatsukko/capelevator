
public class Person {
	Floor current;
	Floor destination;
	public Person(Floor current, Floor destination)
	{
		this.current = current;
		this.destination = destination;
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Person at floor " + current.floorNumber + " heading to " + destination.floorNumber);
		return sb.toString();
	}
}
