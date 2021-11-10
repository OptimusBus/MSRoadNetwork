package innerconnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

public class HttpConnector {
	
	public HttpConnector() {}
	
	
	
	private static Response makeRequest(String url, Method m, Map<String,String> queryParam, String params) {
		
		WebClient client = WebClient.create(baseAddress);
		client.accept("application/json");
		client.type("application/json");
		client.path(url);
		switch(m) {
			case GET:
				if(queryParam != null) {
					for (Entry<String, String> entry : queryParam.entrySet()) {
				        client.query(entry.getKey(), entry.getValue());
				    }
				}
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
		return makeRequest("intersections/"+id, Method.GET, null, null);
	}
	
	public static Response getNearestIntersection(Double lat, Double lon) {
		Map<String, String> param = new HashMap<>();
		param.put("latitude", lat.toString());
		param.put("longitude", lon.toString());
		return makeRequest("intersections/nearest", Method.GET, param, null);
	}
	
	
	public static Response getShortestPaths(Integer source, Integer dest) {
		Map<String, String> param = new HashMap<>();
		param.put("source", source.toString());
		param.put("destination", dest.toString());
		param.put("type","Intersection");
		return makeRequest("shortestPaths", Method.GET, param, null);
	}
	
	
	public static Response getStreets(Integer start,Integer dest) {
		Map<String, String> param = new HashMap<>();
		param.put("osmidStart", start.toString());
		param.put("osmidDest", dest.toString());
		return makeRequest("streets", Method.GET, param, null);
	}
	
	public static Response getStreetById(String sid) {
		return makeRequest("streets/"+sid, Method.GET, null, null);
	}
	
	private static final String baseAddress="http://assd-traffic-service-gruppo2.router.default.svc.cluster.local/assdTrafficService/rest/";
	public static enum Method {GET, POST, PUT, DELETE}

}
