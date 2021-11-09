package controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import model.Node;
import model.Street;
import service.Branch;
import service.BranchLocal;



@Consumes({"application/json"})
@Produces("application/json")
@Path("/roadNetwork")

public class RoadNetworkController {
	
	public RoadNetworkController() {
		super();
	}
	
	
	@GET
	@Path("/{nodeId}")
	public Response getNodeId(@PathParam("nodeId") String nodeID) {
		System.out.println(nodeID);
		try {
			int id=Integer.parseInt(nodeID);
			Node n= branch.getIntersectionById(id);
			if(n == null) return Response.status(404).entity("No Node Found").build();
			return Response.ok(n).build();
		}catch(NumberFormatException e) {
			return Response.status(404).entity("No Node Found").build();
		}
		
	}
	
	@GET
	@Path("/nearestNode")
	public Response getNearestNode(@QueryParam("latitude")double latitude,@QueryParam("longitude")double longitude) {
		System.out.println(latitude);
		System.out.println(longitude);
		
		Node n=branch.getNearestIntersection(latitude, longitude);
		if(n==null) return Response.status(404).entity("No Node Found").build();
		return Response.ok(n).build();
	
		
	}
	
	@GET
	@Path("/shortestPath")
	public Response getShortestPath(@QueryParam("source")int source,@QueryParam("dest")int dest) {
		List<Node> n=branch.getShortestPaths(source, dest);
		if(n==null) return Response.status(404).entity("No Path Found").build();
		return Response.ok(n).build();
	}
	
	@GET
	@Path("/street")
	public Response getStreet(@QueryParam("start")int start,@QueryParam("dest")int dest) {
		Street s=branch.getStreet(start, dest);
		if(s==null) return Response.status(404).entity("No Street Found").build();
		return Response.ok(s).build();
	}
	
	@GET
	@Path("/street/{streetId}")
	public Response getStreetByID(@PathParam("streetID") String streetID) {
		Street s=branch.getStreetById(streetID);
		if(s==null) return Response.status(404).entity("No Street Found").build();
		return Response.ok(s).build();
	}
	
	
	private BranchLocal branch=new Branch();

}
