package service;

import java.util.List;

import model.Node;
import model.Node.Type;
import model.Street;

public interface BranchLocal {
	
	public Node getIntersectionById(Integer id);
	public Node getNearestIntersection(double lat, double lon);
	public List<Node> getShortestPaths(int source, int dest);
	public Street getStreet(int start, int dest);
	public Street getStreetById(String sid);
	public Node getNodeById(String id);
	public Node getNodeByLatLon(double lat, double lon);
	public List<Street> getShortestStreet(int source,int dest);
	public List<Node> getNodePickupPoint ();
	public List<Node> getNodeStandingPoint ();
	public List<Node> getNearestPickupPoint (double lat, double lon);
	
}
