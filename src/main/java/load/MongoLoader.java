package load;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import model.Booking;
import model.Node;
import model.Passenger;
import model.PassengerReg;
import model.Vehicle;
import model.VehicleReg;

public class MongoLoader {
	
	public static void close() {
		m.close();
	}
	
	public MongoLoader() {}
	
	public void saveNode(Node n) {
		MongoDatabase db=m.getDatabase("NodesDB");
		MongoCollection<Document> coll=db.getCollection("nodes");
		coll.insertOne(Node.encodeNode(n));
	}
	
	public void savePassenger(Passenger p) {
		MongoDatabase db = m.getDatabase("PassengersDB");
		MongoCollection<Document> coll = db.getCollection("passengers");
		Document pass = Passenger.encodePassenger(p);
		coll.insertOne(pass);
	}
	
	public void saveVehicle(Vehicle v) {
		Document vehicle = Vehicle.encodeVehicle(v);
		MongoDatabase db = m.getDatabase("VehiclesDB");
		MongoCollection<Document> coll = db.getCollection("vehicles");
		coll.insertOne(vehicle);
	}
	
	public void savePassengerReg(PassengerReg p) {
		MongoDatabase db = m.getDatabase("SecurityDB");
		MongoCollection<Document> coll = db.getCollection("passengersReg");
		Document pass = PassengerReg.encodePassengerReg(p);
		coll.insertOne(pass);
	}
	
	public void saveVehicleReg(VehicleReg v) {
		MongoDatabase db = m.getDatabase("SecurityDB");
		MongoCollection<Document> coll = db.getCollection("vehiclesReg");
		Document pass = VehicleReg.encodeVehicleReg(v);
		coll.insertOne(pass);
	}
	
	public void saveBooking (Booking v) {
		Document booking=Booking.encodeBooking(v);
		MongoDatabase db= m.getDatabase("BookingsDB");
		MongoCollection<Document> collection = db.getCollection("bookings");
		collection.insertOne(booking);
		
                		
                
	}
	
	public void createCollection() {
		MongoDatabase db= m.getDatabase("BookingsDB");
		db.createCollection("bookings");
		db = m.getDatabase("SecurityDB");
		db.createCollection("vehiclesReg");
		db.createCollection("passengersReg");
		db = m.getDatabase("VehiclesDB");
		db.createCollection("vehicles");
		db = m.getDatabase("PassengersDB");
		db.createCollection("passengers");
		db = m.getDatabase("NodesDB");
		db.createCollection("nodes");
		
		
		
	}
	
	
	
	private static final MongoClient m= new MongoClient("137.121.170.248",31186);

	

}
