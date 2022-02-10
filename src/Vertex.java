import java.util.*;

public class Vertex {
	int level = -1;
	int id;
	boolean isVisited = false;
	boolean isLeveled = false;
	boolean inStack = false;
	int parentId = -1;
	int childId = -1;
	int parentCapacity = Integer.MAX_VALUE;
	ArrayList<Vertex> neighbours = new ArrayList<>();
	HashMap<Integer, Integer> edgeCapacity = new HashMap<>();
}
