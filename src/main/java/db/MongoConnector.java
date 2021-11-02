package db;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

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
	
	private static final MongoClient m= new MongoClient("132.121.170.248",31183);

	

}
