import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

public class MyGraph<T> {
	
	private Map<T, NodeCity<T>> adjList;
	List<T> path = new ArrayList<>();
	static String firstCity, lastCity;
	static int count = 0;
	static GetInputFromFile<Object> getFile = new GetInputFromFile<Object>();
	
	@SuppressWarnings("hiding")
	public class Edge<T> {

        private NodeCity<T> startCity;
        private NodeCity<T> endCity;
        
        public Edge(NodeCity<T> from, NodeCity<T> to, int dis) {
            startCity = from;
            endCity = to;
        }

        public NodeCity<T> getStartCity() {   return startCity;   }
        public NodeCity<T> getEndCity() {     return endCity;   }
        public boolean hasEdge(NodeCity<T> from, NodeCity<T> to){return (this.startCity == from && this.endCity == to);}
    }
	
	@SuppressWarnings("hiding")
	public class NodeCity<T> {
		
		public int distance;
        private T city;
        private List< Edge<T> > listEdge;
        private NodeCity<T> parent;
        private boolean visited;
        
        public NodeCity(T city) {  this.city = city;    this.listEdge = new ArrayList<>();     }
        public NodeCity(T city,int distance){   this.city = city;     this.distance = distance;     }
        
        public String toString() { return (String) city; }
        
        public T getCity() { return city; }
        public void setCity(T city) { this.city = city; }
        
        public int getDistance(){ return distance; }
        public void setDistance(int distance) { this.distance = distance; }
        
        public List<Edge<T>> getListEdge() { return listEdge; }
        public void setListEdge(List<Edge<T>> listEdge) { this.listEdge = listEdge; }
		public int getEdgeNumber() { return listEdge.size(); }
		
        public boolean getVisited() { return visited; }
        public void setVisited(boolean isVisited) { this.visited = isVisited; }
        
        public NodeCity<T> getParent() { return parent; }
        public void setParent(NodeCity<T> parent) { this.parent = parent; }
        public boolean hasEdge(NodeCity<T> node) { return findEdge(node).isPresent(); }
        
        public boolean addEdge(NodeCity<T> city, int distance) {
        	if (hasEdge(city)) return false;
        	setDistance(distance);
            Edge<T> edge = new Edge<>(this, city, getDistance());
            return listEdge.add(edge);
        }
        
        public boolean removeEdge(NodeCity<T> city) {
            Optional<Edge<T>> optional = findEdge(city);
            if (optional.isPresent()){ 
            	return listEdge.remove(optional.get()); 
            }
            return false;
        }
        
        private Optional<Edge<T>> findEdge(NodeCity<T> city) {
            return listEdge.stream().filter(edge -> edge.hasEdge(this, city)).findFirst();
        }
    }
	
    MyGraph() {   adjList = new HashMap<>();   }
    
    boolean addEdge(T from, T to) {  return addEdge(from, to, 0); }
    int cityCount() {   return adjList.keySet().size();   }
    NodeCity<T> getNode(T city) {  return adjList.get(city); }
    boolean containsCity(T city) {  return adjList.containsKey(city);  }
    
    boolean addCity(T city) {
        if (adjList.containsKey(city)) return false;
        adjList.put(city, new NodeCity<>(city));
        return true;
    }
    
    boolean addEdge(T from, T to, int dist) {
    	
    	if ( !containsCity(from) )      addCity(from);
        if ( !containsCity(to) )        addCity(to);
        
        NodeCity<T> city1 = getNode(from);
        NodeCity<T> city2 = getNode(to);
        count++;
        System.out.println( count + ". Edge : " + from + " --> " + to );
        return city1.addEdge(city2, dist);
    }
    
    boolean removeCity(T city) {
    	
        if (!adjList.containsKey(city))  return false;
        
        final NodeCity<T> toRemove = getNode(city);
        adjList.values().forEach(node -> node.removeEdge(toRemove));
        adjList.remove(city);
        return true;
    }
   
    boolean removeEdge(T from, T to) {
        if (!containsCity(from) || !containsCity(to)) return false;
        return getNode(from).removeEdge(getNode(to));
    }
    
    void printMyGraph() { 
        for (int i=0 ; i<path.size()-1 ; i++ ) { 
            System.out.println("from: " + path.get(i).toString() + "  ---->  to: " + path.get(i+1).toString());
        }
    }
    
    int edgeCount() { 
    	return adjList.values()
    		.stream()
    		.mapToInt(NodeCity::getEdgeNumber)
    		.sum();  
    }
    
    boolean contains(T from, T to) {
        if (!containsCity(from) || !containsCity(to)) return false;
        return getNode(from).hasEdge(getNode(to));
    }
    
    void shortestPath(String outputName, T from, T to){
        
    	lastCity = GetInputFromFile.tmpThirdListToGetInput.get( GetInputFromFile.tmpThirdListToGetInput.size()-1 );
    	
    	if (!containsCity(from) || !containsCity(to)) return ;
    	run_BF_traversal(from);
    	
    	NodeCity<T> start = getNode(from);
        NodeCity<T> end = getNode(to);
        
        while (end != null && end != getNode(from)) {
            path.add(end.getCity());
            end = end.getParent();
        }
        
        path.add(start.getCity());
        
        if (end == null) return ;
        else {
        	Collections.reverse(path);
        }
        
        writeOutputToFile(outputName, from, path);
    }
    
    void initializeMyGraph() {
        adjList.keySet().forEach(key -> {
            NodeCity<T> node = getNode(key);
            node.setParent(null);
            node.setVisited(false);
        });
    }
    
    void run_BF_traversal(T city) {
    	
        if (!containsCity(city)) throw new RuntimeException();
        initializeMyGraph();
        Queue<NodeCity<T>> myQueue = new LinkedList<>();
        NodeCity<T> from = getNode(city);
        myQueue.add(from);
        
        while (!myQueue.isEmpty()) {
            NodeCity<T> removed = myQueue.remove();
            removed.setVisited(true);
            removed.getListEdge().forEach(edge -> {
                NodeCity<T> connectedCity = edge.getEndCity();
                if (!connectedCity.getVisited()) {
                	myQueue.add(connectedCity);
                    connectedCity.setParent(removed);
                }
            });
        }
    }
    
    void writeOutputToFile(String outputName, T from, List<?> path){
    	
		String line = "", tmp1 = "", tmp2 = "";
	    int total = 0;
	    
		try{
			
			firstCity = GetInputFromFile.tmpThirdListToGetInput.get(0);
			ArrayList<String> arr1 = new ArrayList<String>();
	    	ArrayList<String> arr2= new ArrayList<String>();
	    	
	    	File f2 = new File( outputName );
			if(f2.exists())  f2.delete();
			
			File file = new File ( outputName );
			FileWriter writer = new FileWriter(file);
	        BufferedWriter output = new BufferedWriter(writer);
			
			try{
				System.out.println( "\n*******" );
				output.write(firstCity + "  0\n");
				System.out.println(firstCity + " " + total);
				for( int i=0 ; i<path.size()-1 ; i++ ){
	    			tmp1 = (String) path.get(i);
	    			tmp2 = (String) path.get(i+1);
	    			for( int l=0 ; l<GetInputFromFile.secondListToGetInput.size() ; l++ ){
	    				line = GetInputFromFile.secondListToGetInput.get(l).toString();
	    				line.trim();
	    				String[] arr = line.split(" ",3);
	    				if( !arr2.contains(tmp2) && !arr1.contains(tmp1) && arr[0].equals(tmp1) && !arr[1].equals(firstCity) && arr[1].equals(tmp2) ){ 
	    					arr1.add(tmp1); arr2.add(tmp2);
	    					total += Integer.parseInt( arr[2] );
	    					System.out.println( tmp2 + " " + total );
	    					output.write( tmp2 + " " + total + "\n"  );
	    				}
	    			}
	    		}
	    		arr1.clear(); arr2.clear();
	    		output.close();
	    		System.out.println( "*******\n" );
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				output.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
    
    String toString(List<?> list, int index) {
		if(index == list.size())
			return "";
		else 
			return list.get(index).toString() + "\n" + toString(list, index+1);
	}
}
