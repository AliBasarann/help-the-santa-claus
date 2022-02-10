

public class Train extends Vertex{
	String destination;
	int capacity;
	int sinkCapacity = 0;

	
	public Train(String destination, int capacity, int id) {
		this.destination = destination;
		this.capacity = capacity;
		this.id = id;
	}
}
