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
import model.Street;
import innerconnector.HttpConnector;

public class Branch implements BranchLocal {

	public Branch() {}
	
	//private MongoConnector mdb = new MongoConnector();

	@Override
	// Usiamo il  nostro db come cache
	public Node getIntersectionById(int id) {
		
		Node n=getNodeById(id);
		
		if(n!=null) {
			return n;
		}
		
		
		Response res= HttpConnector.getIntersection(id);
		if(res.getStatus()==200) {
			String s=res.readEntity(String.class);
			Document d=Document.parse(s);
			return Node.decodeIntersection(d);
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
			Node n=getNodeById(d.getInteger("osmid"));
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
	public Node getNodeById(int id) {
		//return Node.decodeNode(mdb.getNodeById(id));
		return null;
	}
	
	@Override
	public Node getNodeByLatLon(double lat, double lon) {
		//return Node.decodeNode(mdb.getNodeByLocation(lat,lon));
		return null;
	}
}
