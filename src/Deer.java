
public class Deer extends Vertex{
	String destination;
	int capacity;
	int sinkCapacity = 0;
	
	
	public Deer(String destination, int capacity, int id) {
		this.destination = destination;
		this.capacity = capacity;
		this.id = id;
	}
}
