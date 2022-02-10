import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;
public class project4main {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		ArrayList<String[]> lineList = new ArrayList<>();
		ArrayList<Train> greenTrainList = new ArrayList<>();
		ArrayList<Train> redTrainList = new ArrayList<>();
		ArrayList<Deer> greenDeerList = new ArrayList<>();
		ArrayList<Deer> redDeerList = new ArrayList<>();
		ArrayList<Bag> bagList = new ArrayList<>();
		HashMap<Integer, Vertex> vertexMap = new HashMap<>();
		LinkedList<Vertex> vertexList = new LinkedList<>();
		Queue<Vertex> levelQueue = new LinkedList<>();
		
		for (int i = 0; i < 10; i++) {
			String line = in.nextLine();
			String[] splittedLine = line.split(" ");
			lineList.add(splittedLine);
			if(i==8 && Integer.parseInt(splittedLine[0]) == 0) {
				break;
			}
		}
		
		int numOfGreenTrains = 0;
		int numOfRedTrains = 0;
		int numOfGreenDeers = 0;
		int numOfRedDeers = 0;
		int numOfBags = 0;
		
		Vertex source = new Vertex();
		Vertex sink = new Vertex();
		
		String[] s = lineList.get(0);
		numOfGreenTrains = Integer.parseInt(s[0]);
		s = lineList.get(2);
		numOfRedTrains = Integer.parseInt(s[0]);
		s = lineList.get(4);
		numOfGreenDeers = Integer.parseInt(s[0]);
		s = lineList.get(6);
		numOfRedDeers = Integer.parseInt(s[0]);
		s = lineList.get(8);
		numOfBags = Integer.parseInt(s[0]);	
		
		int sinkId = numOfGreenTrains + numOfRedTrains + numOfGreenDeers + numOfRedDeers + numOfBags + 1;
		sink.id = sinkId;
		source.id = 0;
		vertexMap.put(sinkId,sink);
		vertexMap.put(0,source);
		vertexList.add(source);
		
		s = lineList.get(1);
		int id = 1;
		for(int i = 0; i<numOfGreenTrains; i++) {
			if(Integer.parseInt(s[i])>0) {
				Train train = new Train("green", Integer.parseInt(s[i]), id );
				source.neighbours.add(train);
				source.edgeCapacity.put(id, train.capacity);
				greenTrainList.add(train);
				vertexMap.put(id,train);
				vertexList.add(train);
				id += 1;
			}
		}
		
		s = lineList.get(3);
		for(int i = 0; i<numOfRedTrains; i++) {
			if(Integer.parseInt(s[i])>0) {
				Train train = new Train("red", Integer.parseInt(s[i]), id );
				source.neighbours.add(train);
				source.edgeCapacity.put(id, train.capacity);
				redTrainList.add(train);
				vertexMap.put(id,train);
				vertexList.add(train);
				id += 1;
			}
		}
		
		s = lineList.get(5);
		for(int i = 0; i<numOfGreenDeers; i++) {
			if(Integer.parseInt(s[i])>0) {
				Deer deer = new Deer("green", Integer.parseInt(s[i]), id );
				source.neighbours.add(deer);
				source.edgeCapacity.put(id, deer.capacity);
				greenDeerList.add(deer);
				vertexMap.put(id,deer);
				vertexList.add(deer);
				id += 1;
			}
		}
		
		s = lineList.get(7);
		for(int i = 0; i<numOfRedDeers; i++) {
			if(Integer.parseInt(s[i])>0) {
				Deer deer = new Deer("red", Integer.parseInt(s[i]), id );
				source.neighbours.add(deer);
				source.edgeCapacity.put(id, deer.capacity);
				redDeerList.add(deer);
				vertexMap.put(id,deer);
				vertexList.add(deer);
				id += 1;
			}
		}
		int totalGifts = 0;
		if(numOfBags != 0) {
			s = lineList.get(9);
		}
		
		for(int i = 0; i<numOfBags*2; i+=2) {
			
			if(Integer.parseInt(s[i+1])!=0) {
				Bag bag = new Bag(s[i], Integer.parseInt(s[i+1]), id );
				totalGifts += bag.capacity;
				if(bag.type.contains("a")) {
					if(bag.type.contains("b")) {
						//abd
						if(bag.type.contains("d")) {
							for(Train t : greenTrainList) {
								t.neighbours.add(bag);
								t.edgeCapacity.put(bag.id,1);
							}
						}
						//abe
						else if(bag.type.contains("e")) {
							for(Deer d : greenDeerList) {
								d.neighbours.add(bag);
								d.edgeCapacity.put(bag.id,1);
							}
						}
						//ab
						else {
							for(Train t : greenTrainList) {
								t.neighbours.add(bag);
								t.edgeCapacity.put(bag.id,1);
							}
							for(Deer d : greenDeerList) {
								d.neighbours.add(bag);
								d.edgeCapacity.put(bag.id,1);
							}
							
						}
					}
					else if(bag.type.contains("c")) {
						//acd
						if(bag.type.contains("d")) {
							for(Train t : redTrainList) {
								t.neighbours.add(bag);
								t.edgeCapacity.put(bag.id,1);
							}
						}
						//ace
						else if(bag.type.contains("e")) {
							for(Deer d : redDeerList) {
								d.neighbours.add(bag);
								d.edgeCapacity.put(bag.id,1);
							}
						}
						//ac
						else {
							for(Train t : redTrainList) {
								t.neighbours.add(bag);
								t.edgeCapacity.put(bag.id,1);
							}
							for(Deer d : redDeerList) {
								d.neighbours.add(bag);
								d.edgeCapacity.put(bag.id,1);
							}
						}
					}
					//ad
					else if(bag.type.contains("d")) {
						for(Train t : redTrainList) {
							t.neighbours.add(bag);
							t.edgeCapacity.put(bag.id,1);
						}
						for(Train t : greenTrainList) {
							t.neighbours.add(bag);
							t.edgeCapacity.put(bag.id,1);
						}
					}
					//ae
					else if(bag.type.contains("e")) {
						for(Deer d : redDeerList) {
							d.neighbours.add(bag);
							d.edgeCapacity.put(bag.id,1);
						}
						for(Deer d : greenDeerList) {
							d.neighbours.add(bag);
							d.edgeCapacity.put(bag.id,1);
						}
					}
					//a
					else {
						for(Deer d : redDeerList) {
							d.neighbours.add(bag);
							d.edgeCapacity.put(bag.id,1);
						}
						for(Deer d : greenDeerList) {
							d.neighbours.add(bag);
							d.edgeCapacity.put(bag.id,1);
						}
						for(Train t : redTrainList) {
							t.neighbours.add(bag);
							t.edgeCapacity.put(bag.id,1);
						}
						for(Train t : greenTrainList) {
							t.neighbours.add(bag);
							t.edgeCapacity.put(bag.id,1);
						}
					}
				}
				else if(bag.type.contains("b")){
					//bd
					if(bag.type.contains("d")) {
						
						for(Train t : greenTrainList) {
							t.neighbours.add(bag);
							t.edgeCapacity.put(bag.id,bag.capacity);
						}
					}
					//be
					else if(bag.type.contains("e")) {
						
						for(Deer d : greenDeerList) {
							d.neighbours.add(bag);
							d.edgeCapacity.put(bag.id,bag.capacity);
						}
					}
					//b
					else {
						
						for(Deer d : greenDeerList) {
							d.neighbours.add(bag);
							d.edgeCapacity.put(bag.id,bag.capacity);
						}
						for(Train t : greenTrainList) {
							t.neighbours.add(bag);
							t.edgeCapacity.put(bag.id,bag.capacity);
						}
					}
				}
				else if(bag.type.contains("c")){
					//cd
					
					if(bag.type.contains("d")) {
						
						for(Train t : redTrainList) {
							t.neighbours.add(bag);
							t.edgeCapacity.put(bag.id,bag.capacity);
						}
					}
					
					//ce
					else if(bag.type.contains("e")) {
						
						for(Deer d : redDeerList) {
							d.neighbours.add(bag);
							d.edgeCapacity.put(bag.id,bag.capacity);
						}
						
					}
					//c
					else {
						
						for(Train t : redTrainList) {
							t.neighbours.add(bag);
							t.edgeCapacity.put(bag.id,bag.capacity);
						}
						for(Deer d : redDeerList) {
							d.neighbours.add(bag);
							d.edgeCapacity.put(bag.id,bag.capacity);
						}
						
					}
				}
				//d
				else if(bag.type.contains("d")){
					
					for(Train t : redTrainList) {
						t.neighbours.add(bag);
						t.edgeCapacity.put(bag.id,bag.capacity);
					}
					for(Train t : greenTrainList) {
						t.neighbours.add(bag);
						t.edgeCapacity.put(bag.id,bag.capacity);
					}
				}
				//e
				else if(bag.type.contains("e")){
					
					for(Deer d : redDeerList) {
						d.neighbours.add(bag);
						d.edgeCapacity.put(bag.id,bag.capacity);
					}
					for(Deer d : greenDeerList) {
						d.neighbours.add(bag);
						d.edgeCapacity.put(bag.id,bag.capacity);
					}
				}
				bag.neighbours.add(sink);
				bag.edgeCapacity.put(sink.id, bag.capacity);
				bagList.add(bag);
				vertexMap.put(id,bag);
				vertexList.add(bag);
				id += 1;
				
			}
		}
		
		
		int flow = 0;
		vertexList.add(sink);
		boolean check = true;
		while(check) {
			levelQueue.add(source);
			source.level = 0;
			source.isLeveled = true;
			//give level start
			while(!levelQueue.isEmpty()) {
				Vertex currentV = levelQueue.poll();
				if(!currentV.neighbours.isEmpty()) {
					Iterator<Vertex> itr = currentV.neighbours.iterator();
					while(itr.hasNext()) {
						
						Vertex vertex = itr.next();
						if(!vertex.isLeveled && currentV.edgeCapacity.get(vertex.id)>0) {
							vertex.level = currentV.level +1 ;
							vertex.isLeveled = true;
							levelQueue.add(vertex);
						}
					}
				}
			}
			
			if(!sink.isLeveled) {
				check = false;
				break;
			}
			//give level end
			
			
			while(true) {
				
				
				
				int minEdge =findPath (source,sink, Integer.MAX_VALUE);
				if(!sink.isVisited) {
					break;
				}
				if(minEdge == -1) {
					break;
				}
				
				flow += minEdge;
				
				source.isVisited = false;
				sink.isVisited = false;
				
			}	
			
			for(Vertex v : vertexList) {
				v.isLeveled = false;
				v.isVisited = false;
			}
		}
		
		out.print(totalGifts- flow);
	}

	
	static int findPath (Vertex currentV, Vertex sink, int flow) {
		if (currentV.id == sink.id) {
			return flow;
		}
		currentV.isVisited = true;
		if(!currentV.neighbours.isEmpty()) {
			Iterator<Vertex> itr = currentV.neighbours.iterator();
			while(itr.hasNext()) {
				
				Vertex childV = itr.next();
				if(!childV.isVisited && childV.level == currentV.level +1 && currentV.edgeCapacity.get(childV.id) > 0) {
					childV.isVisited = true;
					int minEdge = flow;
					int capacity = currentV.edgeCapacity.get(childV.id);
					if(capacity < flow) {
						minEdge = currentV.edgeCapacity.get(childV.id);
					}
					minEdge = findPath(childV, sink, minEdge);
					if(minEdge > 0) {
						if(capacity == minEdge) {
							currentV.neighbours.remove(childV);
							currentV.edgeCapacity.put(childV.id, 0);
							if(!childV.neighbours.contains(currentV)) {
								childV.neighbours.add(currentV);
								childV.edgeCapacity.put(currentV.id, minEdge);
							}else {
								childV.edgeCapacity.put(currentV.id, childV.edgeCapacity.get(currentV.id)+minEdge);
							}
						}else {
							currentV.edgeCapacity.put(childV.id, capacity-minEdge);
							if(!childV.neighbours.contains(currentV)) {
								childV.neighbours.add(currentV);
								childV.edgeCapacity.put(currentV.id, minEdge);
							}else {
								childV.edgeCapacity.put(currentV.id, childV.edgeCapacity.get(currentV.id)+minEdge);
							}
						}
						return minEdge;
					}
				}
				
			}
		}
		return -1;
	}
	
	
}
