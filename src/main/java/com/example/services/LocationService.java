package com.example.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.dao.LocationDAO;
import com.example.models.Location;

@Path("/location")
@Produces(MediaType.APPLICATION_JSON)
public class LocationService {
	
	LocationDAO locationDAO = new LocationDAO();
	
	@GET
	@Path("/getLocation/{id}")
    public Location getLocationById(@PathParam("id") String id) {
		
		Location location = locationDAO.getLocationById(Long.parseLong(id));
		
		return location;
		
		//URL = http://localhost:8080/services/product/001
		//return Response.status(200).entity("product" + location).build();
    }
	
	@GET
	@Path("/all")
	public List<Location> getAllLocations() {
		
		List<Location> locations = locationDAO.getAllLocations();
		
		//URL = http://localhost:8080/services/product/all
		//return Response.status(200).entity("get all products is called" + locations).build();
		return locations;
    }
	
	@GET
	@Path("/saveLocation/{city}/{county}/{country}")
	public Response saveNewProduct(@PathParam("city") String city, @PathParam("county") String county,
			@PathParam("country") String country) {
		
		Location location = new Location();
		location.setCity(city);
		location.setCounty(county);
		location.setCountry(country);
		
		//add code to persist new Location
		if (locationDAO.saveLocation(location)) {
			//return location;
			return Response.status(200).entity("FAILED to insert new location").build();
		}
		
		//return location;
		return Response.status(200).entity("Location created successfully").build();
    }

}
