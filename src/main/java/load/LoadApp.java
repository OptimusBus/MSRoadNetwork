package load;

import java.io.FileReader;
import java.util.ArrayList;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.mongodb.MongoClient;

import model.Booking;
import model.Node;
import model.Passenger;
import model.PassengerReg;
import model.Vehicle;
import model.VehicleReg;


public class LoadApp {
	
	public static void main(String[] args) throws Exception {
		
		LoadApp loader = new LoadApp();
		loader.loadFiles();
		loader.SaveDatabase();
	}
	
	
	public void loadFiles() throws Exception{
		String bok = "bookings.json";
		String veh = "vehicles.json";
		String pckp = "pckp.json";
		String stdp = "stdp.json";
		String vehreg= "vehicleReg.json";
		String passenger = "passenger.json";
		String passengerReg = "passReg.json";
		
		JSONParser jp = new JSONParser();
		FileReader r = new FileReader(bok);
		JSONArray jab = (JSONArray)jp.parse(r);
		r.close();
		
		r = new FileReader(veh);
		JSONArray jav = (JSONArray)jp.parse(r);
		r.close();
		
		r = new FileReader(pckp);
		JSONArray jap = (JSONArray)jp.parse(r);
		r.close();
		
		r = new FileReader(stdp);
		JSONArray jas = (JSONArray)jp.parse(r);
		r.close();
		
		r = new FileReader(vehreg);
		JSONArray jeh = (JSONArray)jp.parse(r);
		
		r = new FileReader(passenger);
		JSONArray jpp = (JSONArray)jp.parse(r);
		
		r = new FileReader(passengerReg);
		JSONArray jppr = (JSONArray)jp.parse(r);
		
		for (Object j : jap) {
			String s = j.toString();
			Document d = Document.parse(s);
			Node n = Node.decodeIntersection(d, Node.Type.PICKUPPOINT);
			pickups.add(n)
;
		}
		for (Object j : jas) {
			String s = j.toString();
			Document d = Document.parse(s);
			Node n = Node.decodeIntersection(d, Node.Type.STANDINGPOINT);
			standings.add(n)
;
		}
		for (Object j : jav) {
			String s = j.toString();
			Document d = Document.parse(s);
			Vehicle v = Vehicle.decodeVehicle(d);
			vehicles.add(v);
		}
		
		for (Object j : jeh) {
			String s = j.toString();
			Document d = Document.parse(s);
			VehicleReg v= VehicleReg.decodeVehicleReg(d);
			vehiclesReg.add(v);
		}
		
		for (Object j : jpp) {
			String s = j.toString();
			Document d = Document.parse(s);
			Passenger p = Passenger.decodePassenger(d);
			passengers.add(p);
		}
		
		for (Object j : jppr) {
			String s = j.toString();
			Document d = Document.parse(s);
			PassengerReg p = PassengerReg.decodePassengerReg(d);
			passRegs.add(p);
		}
		
		
		for (Object j : jab) {
			String s = j.toString();
			Document d = Document.parse(s);
			String dep = d.get("departure").toString();
			String dest = d.get("destination").toString();
			Node depn = new Node();
			Node destn = new Node();
			for(Node n : pickups) {
				if(n.getNodeId().equals(dep))depn = n;
				if(n.getNodeId().equals(dest))destn = n;
			}
			Booking b = Booking.decodeBooking(d, depn, destn);
			bookings.add(b);
		}
	}
	
	
	
	
	public void SaveDatabase() {
		
		MongoLoader m = new MongoLoader();
		m.createCollection();
		
		for (Vehicle v : vehicles) {
			m.saveVehicle(v);
		}
		
		for (Passenger v : passengers) {
			m.savePassenger(v);
		}
		
		for (PassengerReg v : passRegs) {
			m.savePassengerReg(v);
		}
		
		for (Node v : pickups) {
			m.saveNode(v);
		}
		
		for (Node v : standings) {
			m.saveNode(v);
		}
		
		for (VehicleReg v : vehiclesReg) {
			m.saveVehicleReg(v);
		}
		
		for (Booking v : bookings) {
			m.saveBooking(v);
		
		}
	}
	
	ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	ArrayList<Node> pickups = new ArrayList<Node>();
	ArrayList<Node> standings = new ArrayList<Node>();
	ArrayList<Booking> bookings = new ArrayList<Booking>();
	ArrayList<VehicleReg> vehiclesReg = new ArrayList<VehicleReg>();
	ArrayList<Passenger> passengers = new ArrayList<Passenger>();
	ArrayList<PassengerReg> passRegs = new ArrayList<PassengerReg>(); 

}
