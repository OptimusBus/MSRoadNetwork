package innerconnector;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

public class HttpConnector {
	
	public HttpConnector() {}
	
	
	
	private static Response makeRequest(String url, Method m, String params) {
		WebClient client = WebClient.create(baseAddress);
		client.accept("application/json");
		client.type("application/json");
		client.path(url);
		switch(m) {
			case GET:
				return client.get();
			case POST:
				if(params != null) return client.post(params);
			case PUT:
				if(params != null) return client.put(params);
			case DELETE:
				if(params != null) return client.delete();
			default:
				return null;
		}
	}
	
	
	
	public static Response getIntersection(int id) {
		return makeRequest("intersections/"+id, Method.GET, null);
	}
	
	public static Response getNearestIntersection(double lat, double lon) {
		return makeRequest("intersections/nearest?latitude="+lat+"&longitude="+lon, Method.GET, null);
	}
	
	
	public static Response getShortestPaths(int source, int dest) {
		return makeRequest("shortestPaths?source="+source+"&destination="+dest+"&type=Intersection", Method.GET, null);
	}
	
	
	public static Response getStreets(int start,int dest) {
		return makeRequest("streets?osmidStart="+start+"&osmidDest="+dest, Method.GET, null);
	}
	
	public static Response getStreetById(String sid) {
		return makeRequest("street/"+sid, Method.GET, null);
	}
	
	private static final String baseAddress="http://assd-traffic-service-gruppo2.router.default.svc.cluster.local/assdTrafficService/rest/";
	public static enum Method {GET, POST, PUT, DELETE}

}
