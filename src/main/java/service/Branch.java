package service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.Response;

import org.bson.BsonArray;
import org.bson.BsonValue;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import db.MongoConnector;
import model.Location;
import model.Node;
import model.Node.Type;
import model.Street;
import innerconnector.HttpConnector;

public class Branch implements BranchLocal {

	public Branch() {}
	
	private MongoConnector mdb = new MongoConnector();

	@Override
	// Usiamo il  nostro db come cache
	public Node getIntersectionById(Integer id) {
		
	
		Document d=mdb.getNodeById(id.toString());
		
		if(d!=null) {
			return Node.decodeNode(d);
		}
		
		
		Response res= HttpConnector.getIntersection(id);
		if(res.getStatus()==200) {
			String s=res.readEntity(String.class);
			Document d1=Document.parse(s);
			return Node.decodeIntersection(d1);
		}else return null;
	}

	@Override
	public Node getNearestIntersection(double lat, double lon) {
		
		Node n=getNodeByLatLon(lat, lon);
		
		if(n!=null) {
			return n;
		}
		
		Response res=HttpConnector.getNearestIntersection(lat, lon);
		
		if(res.getStatus()==200) {
			String s=res.readEntity(String.class);
			Document d=Document.parse(s);
			return Node.decodeIntersection(d);
		}else return null;
	}

	@Override
	public List<Node> getShortestPaths(int source, int dest) throws ClassCastException{
		Response res=HttpConnector.getShortestPaths(source, dest);
		System.out.print("Road network shortestPath" + res.getStatus());
		if(res.getStatus()!= 200) return null;
		String s=res.readEntity(String.class);
		System.out.println(JSON.parse(s).toString());
		BsonArray a =  BsonArray.parse(s);
		Iterator<BsonValue> i = a.iterator();
		List<Node> path=new ArrayList<Node>();
		while(i.hasNext()) {
			Document d = Document.parse(i.next().toString());
			Node n=getNodeById(d.getInteger("osmid").toString());
			if(n!=null) {
				path.add(n);
			}else {
				path.add(Node.decodeIntersection(d));
			}
			
		}
		if(path.size()<1) {
			return null;
		}
		return path;
	}
	
	@Override
	public List<Street> getShortestStreet(int source, int dest) throws ClassCastException{
		Response res=HttpConnector.getShortestStreet(source, dest);
		System.out.print("Road network shortestStreet" + res.getStatus());
		if(res.getStatus()!= 200) return null;
		String s=res.readEntity(String.class);
		System.out.println(JSON.parse(s).toString());
		BsonArray a =  BsonArray.parse(s);
		Iterator<BsonValue> i = a.iterator();
		List<Street> streets=new ArrayList<Street>();
		while(i.hasNext()) {
			Document d = Document.parse(i.next().toString());
			streets.add(Street.decodeStreet(d));
			
			
		}
		if(streets.size()<1) {
			return null;
		}
		return streets;
	}

	@Override
	public Street getStreet(int start, int dest) {
		Response res= HttpConnector.getStreets(start, dest);
		System.out.print("Road network street" + res.getStatus());
		if(res.getStatus()==200) {
			String s=res.readEntity(String.class);
			Document d=Document.parse(s);
			return Street.decodeStreet(d);
		}else return null;
	}
		
		
	

	@Override
	public Street getStreetById(String sid) {
		Response res= HttpConnector.getStreetById(sid);
		if(res.getStatus()==200) {
			String s=res.readEntity(String.class);
			Document d=Document.parse(s);
			return Street.decodeStreet(d);
		}else return null;
	}
		
	

	@Override
	public Node getNodeById(String id) {
		return Node.decodeNode(mdb.getNodeById(id));
		
	}
	
	@Override
	public Node getNodeByLatLon(double lat, double lon) {
		return Node.decodeNode(mdb.getNodeByLocation(lat,lon));
		
	}

	@Override
	public List<Node> getNodePickupPoint() {
		List<Document> pck = mdb.getPickupPoint();
		List<Node> l = new ArrayList<Node>();
		for (Document d : pck) {
			l.add(Node.decodeNode(d));
			
		}
		
		return l;
		
	}
	
	

	@Override
	public List<Node> getNodeStandingPoint() {
		List<Document> psk = mdb.getStandingPoint();
		System.out.println(psk.size());
		List<Node> ls = new ArrayList<Node>();
		for (Document d : psk) {
			System.out.println(d.toJson());
			ls.add(Node.decodeNode(d));
			
			
		}
		
		return ls;
	}

	@Override
	public List<Node> getNearestPickupPoint(double lat, double lon) {
		// TODO Auto-generated method stub
		return null;
	}
}
