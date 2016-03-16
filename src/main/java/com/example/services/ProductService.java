package com.example.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.dao.CategoryDAO;
import com.example.dao.LocationDAO;
import com.example.dao.ProductDAO;
import com.example.models.Category;
import com.example.models.Location;
import com.example.models.Product;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductService {
	
	ProductDAO productDAO = new ProductDAO();
	CategoryDAO categoryDAO = new CategoryDAO();
	LocationDAO locationDAO = new LocationDAO();
	
	@GET
	@Path("/getProduct/{id}")
    public Product getProductById(@PathParam("id") String id) {
		
		Product product = productDAO.getProductById(Long.parseLong(id));
		
		return product;
		
		//URL = http://localhost:8080/services/product/001
		//return Response.status(200).entity("product" + product).build();
    }
	
	@GET
	@Path("/all")
	public List<Product> getAllProducts() {
		
		List<Product> products = productDAO.getAllProducts();
		
		return products;
		
		//URL = http://localhost:8080/services/product/all
		//return Response.status(200).entity("get all products is called" + products).build();
    }
	
	@GET
	@Path("/saveProduct/{name}/{category}/{price}/{location}/{description}")
	public Response saveNewProduct(@PathParam("name") String name, @PathParam("price") String price,
			@PathParam("location") String locationId, @PathParam("description") String description, 
			@PathParam("category") String categoryId) {
		
		Product product = new Product();
		product.setName(name);
		Category category = categoryDAO.getCategoryById(Long.parseLong(categoryId));
		if (category == null) {
			return Response.status(200).entity("Invalid category").build();
		}
		product.setCategory(category);
		product.setDescription(description);
		Location location = locationDAO.getLocationById(Long.parseLong(locationId));
		if (location == null) {
			return Response.status(200).entity("Invalid location").build();
		}
		product.setLocation(location);
		product.setPrice(Double.parseDouble(price));
		
		//add code to persist new Product
		if (productDAO.saveProduct(product)) {
			return Response.status(200).entity("FAILED to insert new product").build();
		}
		
		return Response.status(200).entity("product created successfully").build();
    }

}
