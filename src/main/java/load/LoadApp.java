package load;

import java.io.FileReader;
import java.util.ArrayList;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import model.Booking;
import model.Node;
import model.Vehicle;


public class LoadApp {
	
	public void loadFiles() throws Exception{
		String bok = "bookings.json";
		String veh = "vehicles.json";
		String pckp = "pckp.json";
		String stdp = "stdp.json";
		
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
	
	ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
	ArrayList<Node> pickups = new ArrayList<Node>();
	ArrayList<Node> standings = new ArrayList<Node>();
	ArrayList<Booking> bookings = new ArrayList<Booking>();

}
