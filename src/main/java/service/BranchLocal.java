package service;

import java.util.List;

import model.Node;
import model.Street;

public interface BranchLocal {
	
	public Node getIntersectionById(int id);
	public Node getNearestIntersection(double lat, double lon);
	public List<Node> getShortestPaths(int source, int dest);
	public Street getStreet(int start, int dest);
	public Street getStreetById(String sid);
	public Node getNodeById(int id);
	public Node getNodeByLatLon(double lat, double lon);
	public List<Street> getShortestStreet(int source,int dest);
	
}
