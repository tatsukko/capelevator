
public class Person {
	Object current;
	Floor destination;
	static final int ELEVATOR = 1, FLOOR = 2;
	int state;
	public Person(Floor current, Floor destination)
	{
		this.current = current;
		state = FLOOR;
		this.destination = destination;
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append((state==FLOOR?"Person at floor " + ((Floor)current).floorNumber:
			"Person in elevator " + ((Elevator)current).id)+ " heading to " + destination.floorNumber);
		return sb.toString();
	}
}
