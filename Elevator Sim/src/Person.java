
public class Person {
	Object current;
	Floor destination;
	static final int ELEVATOR = 1, FLOOR = 2;
	int state;
	int id;
	long start;
	long enter;
	long exit;
	Floor startFloor;
	public Person(Floor current, Floor destination, int id)
	{
		this.current = current;
		this.startFloor = current;
		state = FLOOR;
		this.destination = destination;
		this.id=id;
	}
	public void start()
	{
		start=System.currentTimeMillis();
	}
	public void enter()
	{
		enter=System.currentTimeMillis();
		//System.out.println("enter " + id);
	}
	public void exit()
	{
		exit=System.currentTimeMillis();
		Table.model.addRow(new Object[]{new Integer(id), new Integer((int)startFloor.floorNumber), new Integer((int)destination.floorNumber),
				new Double((double)(enter-start)/1000), new Double((double)(exit-enter)/1000), new Double((double)(exit-start)/1000), 
		});
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		if(current.getClass().getName().equals(Floor.class.getName()))
		{
			sb.append("Person at floor " + ((Floor)current).floorNumber);
		}
		else if(current.getClass().getName().equals(Elevator.class.getName()))
		{
			sb.append("Person in elevator " + ((Elevator)current).id + " heading to " + destination.floorNumber);
		}
		else
		{
			System.out.println("Lost person");
		}
		return sb.toString();
	}
}
