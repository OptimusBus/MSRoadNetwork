package db;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import model.Node;
import model.Node.Type;

public class MongoConnector {
	
	public static void close() {
		m.close();
	}
	
	public MongoConnector() {}
	
	public Document getNodeById(int id) {
		MongoDatabase db=m.getDatabase("NodesDB");
		MongoCollection<Document> coll=db.getCollection("nodes");
		return coll.find(Filters.eq("nodeId", id)).first();
	}
	
	// da testare 
	public Document getNodeByLocation(double lat, double lon) {
		MongoDatabase db=m.getDatabase("NodesDB");
		MongoCollection<Document> coll=db.getCollection("nodes");
		BasicDBObject criteria=new BasicDBObject();
		criteria.append("location.latitude", lat);
		criteria.append("location.longitude", lon);
		return coll.find(criteria).first();
	}
	
	public List<Document> getPickupPoint() {
		MongoDatabase db = m.getDatabase("NodesDB");
		MongoCollection<Document> coll = db.getCollection("nodes");
		return coll.find(Filters.eq("type","PICKUPPOINT")).into(new ArrayList<Document>());
	}
	
	public List<Document> getStandingPoint() {
		MongoDatabase db = m.getDatabase("NodesDB");
		MongoCollection<Document> coll = db.getCollection("nodes");
		return coll.find(Filters.eq("type","STANDINGPOINT")).into(new ArrayList<Document>());
	}
	
	
	private static final MongoClient m= new MongoClient("137.121.170.248",31186);

	

}
